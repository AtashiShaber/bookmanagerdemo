package com.shaber.bookmanager.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
    //创建一个SqlSessionFactory对象
    private static final SqlSessionFactory sqlSessionFactory;

    //构建SqlSessionBuilder
    static {
        try {
            //将配置文件导入
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            //通过配置文件创建SqlSessionFactoryBuilder
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //获取SqlSession
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
