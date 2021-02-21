package com.liu.framethree.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName DruidConfig
 * @Description TODO
 * @Author 刘培振
 * @Date 2019/3/4 11:10
 * @Version 1.0
 */
@Configuration
public class MultiDruidConfig {

    private Logger logger = LoggerFactory.getLogger(MultiDruidConfig.class);

    //@Primary
    @Bean
    @ConfigurationProperties("spring.datasource.druid.one")
    public DruidDataSource dataSourceOne(){
        return DruidDataSourceBuilder.create().build();
    }
    @Bean
    @ConfigurationProperties("spring.datasource.druid.two")
    public DruidDataSource dataSourceTwo(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(value = "dataSource")
    @Primary//首先加载的数据源
    public DataSource dataSourceRouter(@Qualifier("dataSourceOne") DruidDataSource dataSourceOne,
                                       @Qualifier("dataSourceTwo") DruidDataSource dataSourceTwo){
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("one", dataSourceOne);
        targetDataSources.put("two", dataSourceTwo);

        DataSourceRouter dataSourceRouter = new DataSourceRouter();
        dataSourceRouter.setTargetDataSources(targetDataSources);
        dataSourceRouter.setDefaultTargetDataSource(dataSourceOne);
        return dataSourceRouter;
    }

    /**
     * 数据源one的事物
     * @param dataSourceOne
     * @return
     */
    @Bean
    @Primary//默认数据源事物
    public PlatformTransactionManager oneTaxManager(@Qualifier("dataSourceOne") DruidDataSource dataSourceOne){
        return new DataSourceTransactionManager(dataSourceOne);
    }

    /**
     * 数据源two的事物
     * @param dataSourceTwo
     * @return
     */
    @Bean
    public PlatformTransactionManager twoTaxManager(@Qualifier("dataSourceTwo") DruidDataSource dataSourceTwo){
        return new DataSourceTransactionManager(dataSourceTwo);
    }

}
