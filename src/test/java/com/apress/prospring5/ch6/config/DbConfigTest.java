package com.apress.prospring5.ch6.config;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DbConfigTest {

    private static final Logger logger = LoggerFactory.getLogger(DbConfigTest.class);

    @Test
    public void testOne() throws SQLException {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:ch6/drivermanager-cfg-01.xml");
        context.refresh();
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        testDataSource(dataSource);

        context.close();
    }

    @Test
    public void testTwo() throws SQLException {
        GenericApplicationContext context = new AnnotationConfigApplicationContext(DbConfig.class);
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        testDataSource(dataSource);

        context.close();
    }

    private void testDataSource(DataSource dataSource) throws SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT 1 as col");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int mockVal = resultSet.getInt("col");
                assertTrue(mockVal == 1);
            }
            statement.close();
        } catch (Exception e) {
            logger.error("Data source test error", e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

}