package com.apress.prospring5.ch6.config;

import com.apress.prospring5.ch6.dao.NamedJdbcSingerDao;
import com.apress.prospring5.ch6.dao.SingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class NamedJdbcConfig {
    private static Logger logger = LoggerFactory.getLogger(NamedJdbcConfig.class);

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
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public SingerDao singerDao() {
        NamedJdbcSingerDao singerDao = new NamedJdbcSingerDao();
        singerDao.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate());
        return singerDao;
    }

}
