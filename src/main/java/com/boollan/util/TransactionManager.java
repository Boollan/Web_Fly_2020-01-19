package com.boollan.util;

/**
 * 事务相关的工具类，包含了，开启事物，提交事物，回滚事物和释放连接
 */

public class TransactionManager {


    private ConnectionUtils connectionUtils;

    /**
     * 开启事务
     */
    public void beginTransaction() {
        try {
            connectionUtils.getthreadConnection().setAutoCommit(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 提交事务
     */
    public void commit() {
        try {
            connectionUtils.getthreadConnection().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 回滚事务
     */
    public void rollback() {
        try {
            connectionUtils.getthreadConnection().rollback();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭连接
     */
    public void release() {
        try {
            //将连接返还给连接池|线程返还给线程池
            connectionUtils.getthreadConnection().close();
            connectionUtils.removeConnection();//解绑线程和Connection对象
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }
}
