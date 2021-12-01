package com.liu.framethree.test;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName TestTransaction
 * @Description TODO
 * @Author 刘培振
 * @Date 2021-12-01 18:09
 * @Version 1.0
 */
public class TestTransaction {

    public static void main(String[] args) throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        Connection connection = dataSource.getConnection();

        try {
            connection.setAutoCommit(false);
            connection.prepareStatement("update user set age = '77' where name = 'Jack'").executeUpdate();
            connection.prepareStatement("update user set age = '67' where name = 'Jack'").executeUpdate();
            //提交事务
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }


    }
}
