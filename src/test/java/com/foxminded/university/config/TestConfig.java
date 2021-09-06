package com.foxminded.university.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.foxminded.university")
public class TestConfig {
    private final static String TEST_DATA_SOURCE = "testDataSource";
    private final static String SCRIPT_SCHEMA = "classpath:schema.sql";
    private final static String SCRIPT_DATA = "classpath:data.sql";
    @Bean
    public JdbcTemplate getJdbcTemplate(@Qualifier(TEST_DATA_SOURCE) DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Profile("test")
    DataSource testDataSource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript(SCRIPT_SCHEMA)
                .addScript(SCRIPT_DATA)
                .build();
        return dataSource;
    }
}
