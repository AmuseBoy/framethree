package com.liu.framethree.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @ClassName DruidConfig
 * @Description 单数据源配置
 * @Author 刘培振
 * @Date 2019/3/4 11:10
 * @Version 1.0
 */
@Configuration
public class DruidConfig {

//    private Logger logger = LoggerFactory.getLogger(DruidConfig.class);
//
//    @Value("${cao.url}")
//    private String dbUrl;
//
//    @Value("${cao.username}")
//    private String username;
//
//    @Value("${cao.password}")
//    private String password;
//
//    @Value("${cao.driver-class-name}")
//    private String driverClassName;
//
//    //@Primary
//    @ConfigurationProperties(prefix = "cao") //通过注解方便
//    @Bean(initMethod = "init",destroyMethod = "close")
//    public DruidDataSource druidDataSource(){
//        logger.info("开始配置druidDataSource");
//        DruidDataSource dataSource = new DruidDataSource();
////        dataSource.setUrl(this.dbUrl);
////        dataSource.setUsername(username);
////        dataSource.setPassword(password);
////        dataSource.setDriverClassName(driverClassName);
//        logger.info("druidDataSource配置成功");
//        return dataSource;
//    }

}
