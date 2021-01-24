package com.leo;

import com.leo.entity.Animal;
import com.leo.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ComplexBeanTest {
    @Test
    public void test1() throws SQLException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Connection connection = (Connection) ctx.getBean("conn");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            System.out.println("userId:" + id);
        }
    }

    @Test
    public void test2() throws SQLException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Connection connection = (Connection) ctx.getBean("connection2");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            System.out.println("userId:" + id);
        }
    }

    @Test
    public void test3() throws SQLException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        Connection connection = (Connection) ctx.getBean("connection3");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            System.out.println("userId:" + id);
        }
    }
}
