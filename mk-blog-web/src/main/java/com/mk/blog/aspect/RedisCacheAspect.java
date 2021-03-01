package com.mk.blog.aspect;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.log.StaticLog;
import com.mk.blog.IRedisService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author MK
 * @describe Redis缓存切面
 * see https://juejin.cn/post/6844903529056829453
 */
@Component
@Aspect
public class RedisCacheAspect {

    @Resource
    private IRedisService redisService;

    @Pointcut("@annotation(com.mk.blog.annotions.RedisCache)")
    public void pointcutMethod() {
    }

    @Around("pointcutMethod()")
    public Object around(ProceedingJoinPoint joinPoint) {
        //前置：从Redis里获取缓存
        //先获取目标方法参数
        long startTime = System.currentTimeMillis();
        String appId = null;
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            appId = String.valueOf(args[0]);
        }

        //获取目标方法所在类
        String target = joinPoint.getTarget().toString();
        String className = target.split("@")[0];

        //获取目标方法的方法名称
        String methodName = joinPoint.getSignature().getName();

        //redis中key格式：    appId:方法名称
        String redisKey = appId + ":" + className + "." + methodName;

        Object obj = redisService.get(redisKey);
        if (obj != null) {
            StaticLog.info("**********从Redis中查到了数据**********");
            StaticLog.info("Redis的KEY值:" + redisKey);
            return obj;
        }
        long endTime = System.currentTimeMillis();
        StaticLog.info("Redis缓存AOP处理所用时间:" + (endTime - startTime));
        StaticLog.info("**********没有从Redis查到数据**********");
        try {
            obj = joinPoint.proceed();
        } catch (Throwable e) {
            StaticLog.error(ExceptionUtil.stacktraceToString(e));
        }
        StaticLog.info("**********开始从MySQL查询数据**********");
        //后置：将数据库查到的数据保存到Redis
        redisService.set(redisKey, obj, 604800L);
        StaticLog.info("**********数据成功保存到Redis缓存!!!**********");
        StaticLog.info("Redis的KEY值:" + redisKey);
        return obj;
    }
}
