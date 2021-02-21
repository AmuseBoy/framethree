package com.liu.framethree.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

/**
 * @ClassName DataSourceAspect
 * @Description TODO
 * @Author 刘培振
 * @Date 2020-05-22 13:55
 * @Version 1.0
 */
@Aspect
@Component
//@Order(-1)//设置@TargetDataSource注解在事物注解之前执行
public class DataSourceAspect {

    private Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    @Around("@annotation(targetDataSource)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, TargetDataSource targetDataSource) throws Throwable{
        String value = targetDataSource.value();
        logger.info("set database is {}", value);
        if(value.equals("one")){
            DBContextHolder.setDataSource("one");
        }else {
            DBContextHolder.setDataSource("two");
        }
        return proceedingJoinPoint.proceed();
    }

    /**
     * 用完删除
     * @param targetDataSource
     */
    @After("@annotation(targetDataSource)")
    public void clearDataSource(TargetDataSource targetDataSource){
        logger.info("delete dataSource");
        DBContextHolder.clearDataSource();
    }
}
