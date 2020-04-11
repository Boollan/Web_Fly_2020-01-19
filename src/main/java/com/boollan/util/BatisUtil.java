package com.boollan.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Boollan
 */
public class BatisUtil {

    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "Spring-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSession getsSession() throws IOException {
        return getSqlSessionFactory().openSession();
    }

}
