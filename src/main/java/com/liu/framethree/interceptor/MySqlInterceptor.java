package com.liu.framethree.interceptor;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName MySqlInterceptor
 * @Description TODO
 * @Author 刘培振
 * @Date 2022-04-15 16:51
 * @Version 1.0
 */
@Component
@ConditionalOnProperty(name = "ext.data.type", havingValue = "self")
@Intercepts({
        //@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}),
        //@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
public class MySqlInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println(invocation.getTarget().getClass());

        if (invocation.getTarget() instanceof StatementHandler) {
            StatementHandler handler = (StatementHandler) invocation.getTarget();
            BoundSql boundSql = handler.getBoundSql();
            MetaObject metaObject = MetaObject.forObject(handler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
            //先拦截到RoutingStatementHandler，里面有个StatementHandler类型的delegate变量，其实现类是BaseStatementHandler，然后就到BaseStatementHandler的成员变量mappedStatement
            MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
            System.out.println("xxxxxxxxxxxxxxxx");
//            MetaObject metaObject = SystemMetaObject.forObject(handler);
//            MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("mappedStatement");
            String sql = boundSql.getSql();
            System.out.println("打印:" + boundSql.getSql());
            sql = sql.replace("from", " ,job from");
            System.out.println("打印sql:" + sql);
            Field sqlField = boundSql.getClass().getDeclaredField("sql");
            sqlField.setAccessible(true);
            sqlField.set(boundSql, sql);
            return invocation.proceed();
        } else if (invocation.getTarget() instanceof ResultSetHandler) {
            System.out.println("进来了");
            DefaultResultSetHandler defaultResultSetHandler = (DefaultResultSetHandler) invocation.getTarget();
            Statement statement = (Statement) invocation.getArgs()[0];
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.println("resultSet:" + resultSet.getString("job"));
            }
            resultSet.beforeFirst();
            List<Object> resultList = defaultResultSetHandler.handleResultSets(statement);
            //把扩展字段加入结果集
            for (int i = 0; i < resultList.size(); i++) {
                Object result = resultList.get(i);
                BeanUtils.setProperty(result, "job", "IT");
            }
            return resultList;
        } else if (invocation.getTarget() instanceof Executor) {
            Executor executor = (Executor) invocation.getTarget();
            System.out.println(invocation.getMethod().getName());
            Object[] args = invocation.getArgs();
            MappedStatement ms = (MappedStatement) args[0];
            Object parameter = args[1];
            SqlCommandType sqlCommandType = ms.getSqlCommandType();
            System.out.println(sqlCommandType.equals(SqlCommandType.SELECT));
            BoundSql boundSql = ms.getBoundSql(parameter);
            String sql = boundSql.getSql();
            System.out.println("Executor:" + boundSql.getSql());
            sql = sql.replace("from", " ,job from");
            System.out.println("Executor打印sql:" + sql);
            return invocation.proceed();
        } else {
            System.out.println("是谁");
            return invocation.proceed();
        }
    }

    @Override
    public Object plugin(Object o) {
        if (o instanceof StatementHandler || o instanceof ResultSetHandler
                || o instanceof Executor) {
            return Plugin.wrap(o, this);
        } else {
            return o;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }


    @SuppressWarnings("unchecked")
    public static <T> T realTarget(Object target) {
        if (Proxy.isProxyClass(target.getClass())) {
            MetaObject metaObject = SystemMetaObject.forObject(target);
            return realTarget(metaObject.getValue("mappedStatement"));
        }
        return (T) target;
    }
}
