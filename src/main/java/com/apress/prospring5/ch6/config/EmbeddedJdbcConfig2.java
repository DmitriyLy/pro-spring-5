package com.apress.prospring5.ch6.config;

import com.apress.prospring5.ch6.dao.JdbcSingerDao;
import com.apress.prospring5.ch6.dao.JdbcSingerDaoAnnotated;
import com.apress.prospring5.ch6.dao.SingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.apress.prospring5.ch6")
public class EmbeddedJdbcConfig2 {

    private static Logger logger = LoggerFactory.getLogger(EmbeddedJdbcConfig2.class);

    @Bean
    public DataSource dataSource() {
        try {
            EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
            return databaseBuilder.setType(EmbeddedDatabaseType.H2)
                    .addScripts("classpath:ch6/db/schema.sql", "classpath:ch6/db/test-data.sql")
                    .build();
        } catch (Exception e) {
            logger.error("Embedded DB initialization error", e);
            return null;
        }
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public SingerDao singerDao() {
        JdbcSingerDaoAnnotated dao = new JdbcSingerDaoAnnotated();
        dao.setDataSource(dataSource());
        return dao;
    }

}
