package com.apress.prospring5.ch6.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmbeddedJdbcConfigDemo {
    public static void main(String[] args) {
        GenericApplicationContext context = new AnnotationConfigApplicationContext(EmbeddedJdbcConfig.class);
        DataSource dataSource = context.getBean("dataSource", DataSource.class);

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from singer");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("last_name"));
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        context.close();
    }
}
