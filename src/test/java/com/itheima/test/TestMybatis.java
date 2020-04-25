package com.itheima.test;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    @Test
    public void testFindAll(){
        //1.创建SqlSessionFactoryBuilder对象
        // 该对象用于解析 核心配置文件
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //2.通过SqlSessionFactoryBuilder对象创建 SqlSessionFactory工厂对象
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //3.通过工厂对象,创建SqlSession对象
        // 创建userDao接口的代理对象.
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // UserDao dao = new UserDaoImp();
        UserDao dao = sqlSession.getMapper(UserDao.class);
        List<User> list = dao.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }
}
