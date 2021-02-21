package com.liu.framethree.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName DataSourceRouter
 * @Description TODO
 * @Author 刘培振
 * @Date 2020-05-22 11:26
 * @Version 1.0
 */
public class DataSourceRouter extends AbstractRoutingDataSource {

    private Logger logger = LoggerFactory.getLogger(DataSourceRouter.class);

    @Override
    protected Object determineCurrentLookupKey() {
        logger.info("now dataSource is {}", DBContextHolder.getDataSource());
        return DBContextHolder.getDataSource();
    }

}
