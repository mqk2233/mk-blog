package com.mk.blog.utils;

import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * @author MK
 * @date 2020/12/21
 * https://blog.csdn.net/linchack/article/details/81066028
 */
public class IpUtil {

    private final static String SEPARATOR = ",";

    private final static String LOCALHOST_ONE = "127.0.0.1";

    private final static String LOCALHOST_TWO = "0:0:0:0:0:0:0:1";

    private final static String UN_KNOWN = "unknown";

    private final static int IP_LENGTH = 15;


    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 参考文章： http://developer.51cto.com/art/201111/305181.htm
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * <p>
     * 用户真实IP为： 192.168.1.110
     *
     * @param request {@link HttpServletRequest}
     * @return {@link String}
     */
    @SneakyThrows
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || UN_KNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || UN_KNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || UN_KNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals(LOCALHOST_ONE) || ipAddress.equals(LOCALHOST_TWO)) {
                //根据网卡取本机配置的IP
                InetAddress inet;
                inet = InetAddress.getLocalHost();
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > IP_LENGTH) {
            if (ipAddress.indexOf(SEPARATOR) > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(SEPARATOR));
            }
        }
        return ipAddress;
    }
}
