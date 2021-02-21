package com.liu.framethree.config;

/**
 * @ClassName Test
 * @Description TODO
 * @Author 刘培振
 * @Date 2020-06-14 15:10
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {
        DBContextHolder.setDataSource("one");
        DBContextHolder.setDataSource("two");
        System.out.println(DBContextHolder.getDataSource());
        System.out.println(DBContextHolder.getDataSource());
    }
}
