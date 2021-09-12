package com.foxminded.university.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:database.properties")
@ComponentScan("com.foxminded.university")
public class SpringConfig {
    @Autowired
    private Environment environment;
    private final static String URL = "url";
    private final static String USER = "dbuser";
    private final static String DRIVER = "driver";
    private final static String PASSWORD = "dbpassword";
    private final static String SCHEMA = "schema";
    private final static String POSTGRES_DATASOURCE = "postgresDataSource";

    @Bean
    public JdbcTemplate getJdbcTemplate(@Qualifier(POSTGRES_DATASOURCE) DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Profile("!test")
    @Bean
    public DataSource postgresDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty(DRIVER));
        dataSource.setUrl(environment.getProperty(URL));
        dataSource.setUsername(environment.getProperty(USER));
        dataSource.setPassword(environment.getProperty(PASSWORD));
        prepareDatabase(dataSource);
        return dataSource;
    }

    public void prepareDatabase(DataSource dataSource) {
        Resource resource = new ClassPathResource(environment.getProperty(SCHEMA));
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
        try {
            databasePopulator.populate(dataSource.getConnection());
        } catch (ScriptException | SQLException e) {
            e.printStackTrace();
        }
    }
}
