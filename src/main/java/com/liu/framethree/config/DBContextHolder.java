package com.liu.framethree.config;

/**
 * @ClassName DBContextHolder
 * @Description TODO
 * @Author 刘培振
 * @Date 2020-05-22 13:47
 * @Version 1.0
 */
public class DBContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSource(String dataSource){
        contextHolder.set(dataSource);
    }

    public static String getDataSource(){
        return contextHolder.get();
    }

    public static void clearDataSource(){
        contextHolder.remove();
    }
}
