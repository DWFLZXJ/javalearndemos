package com.daiwf.javalearndemos.spring.services;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect @Component public class LogAspect
{
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    //切面拦截这个包下面的所有方法
    @Pointcut("execution(* com.daiwf.javalearndemos.spring.controller..*.*(..))") public void Logwhere() {
    }
    // 配置织入点根据注解
   /* @Pointcut("@annotation(com.ruoyi.framework.aspectj.lang.annotation.Log)")
    public void logPointCut()
    {
    }*/
    @Before("Logwhere()") public void getBeforelog() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("记录请求地址" + request.getRequestURI());
    }


    /*@Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable
    {
        DataSource dataSource = getDataSource(point);

        if (StringUtils.isNotNull(dataSource))
        {
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
        }

        try
        {
            return point.proceed();
        }
        finally
        {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }*/

}
