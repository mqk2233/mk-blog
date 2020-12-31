package com.mk.blog.aspect;

import cn.hutool.log.StaticLog;
import com.mk.blog.utils.IpUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 日志切面
 * @author MK
 **/
@Aspect
@Component
public class LogAspect {

    private final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    @Pointcut("execution(public * com.mk.blog.controller.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void init() {
        threadLocal.set(System.currentTimeMillis());
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = Objects.requireNonNull((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Operation apiOperation = method.getAnnotation(Operation.class);
        String methodOperation = null == apiOperation ? "" : apiOperation.description();
        StaticLog.info("\n\nip=========>{}\n" + "请求路径===>{}\n" + "方法描述===>{}\n" + "方法名=====>{}\n" + "参数=======>{}\n",
                IpUtil.getIpAddress(request), request.getRequestURI(), methodOperation, method.getName(), pjp.getArgs());
        return pjp.proceed();
    }

    @After(value = "pointcut()")
    public void destroy() {
        StaticLog.info("==========>方法用时{}ms<==========\n", System.currentTimeMillis() - threadLocal.get());
        threadLocal.remove();
    }

}
