package com.itheima.test;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserTest {
    private SqlSessionFactory factory;

    @Before
    public void init() throws Exception {
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactoryBuilder 对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //3.创建SqlSesionFactory 对象
        factory = new SqlSessionFactoryBuilder().build(is);
    }

    @Test
    public void testAddUser() {
        //获取SqlSession 对象
        SqlSession sqlSession = factory.openSession();
        //2.获取接口的实现类对象
        UserDao dao = sqlSession.getMapper(UserDao.class);
        //调用方法执行
        //创建user对象
        User user = new User();
        user.setUsername("老王");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("隔壁");
        dao.addUser(user);

        //提交事务
        sqlSession.commit();
        sqlSession.close();

        System.out.println("新插入的id是:" + user.getId());

    }

    @Test
    public void updateUser() {
        //获取SqlSession 对象
        SqlSession sqlSession = factory.openSession();
        //2.获取接口的实现类对象
        UserDao dao = sqlSession.getMapper(UserDao.class);
        //调用方法执行
        //创建user对象
        User user = new User();
        user.setSex("女");
        user.setId(52);
        dao.updateUser(user);

        sqlSession.commit();
        sqlSession.close();

    }

    //根据id删除用户
    @Test
    public void deleteUser() {
        //获取SqlSession 对象
        SqlSession sqlSession = factory.openSession();
        //2.获取接口的实现类对象
        UserDao dao = sqlSession.getMapper(UserDao.class);
        //调用方法执行
        //创建user对象
        dao.deleteUser(52);

        sqlSession.commit();
        sqlSession.close();
    }

    //查询用户

    @Test
    public void findAll() {
        //获取SqlSession 对象
        SqlSession sqlSession = factory.openSession();
        //2.获取接口的实现类对象
        UserDao dao = sqlSession.getMapper(UserDao.class);
        //调用方法执行
        //创建user对象
        List<User> list=dao.findAll();
        for (User user : list) {
            System.out.println(user);
        }


    }
}
