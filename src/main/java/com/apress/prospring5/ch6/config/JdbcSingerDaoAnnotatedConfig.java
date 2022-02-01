package com.apress.prospring5.ch6.config;

import com.apress.prospring5.ch6.dao.JdbcSingerDaoAnnotated;
import com.apress.prospring5.ch6.dao.SingerDao;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:ch6/db/jdbc2.properties")
@ComponentScan(basePackages = "om.apress.prospring5.ch6")
public class JdbcSingerDaoAnnotatedConfig {

    private static final Logger logger = LoggerFactory.getLogger(JdbcSingerDaoAnnotatedConfig.class);

    @Value("${driverClassName}")
    private String driverClassName;

    @Value("${url}")
    private String url;

    @Value("${db_username}")
    private String username;

    @Value("${password}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            return dataSource;
        } catch (Exception e) {
            logger.error("DBCP Datasource creation error", e);
            return null;
        }
    }

    @Bean
    public SingerDao singerDao() {
        JdbcSingerDaoAnnotated dao = new JdbcSingerDaoAnnotated();
        dao.setDataSource(dataSource());
        return dao;
    }
}
