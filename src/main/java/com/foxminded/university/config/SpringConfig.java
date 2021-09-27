package com.foxminded.university.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final Logger logger = LoggerFactory.getLogger(SpringConfig.class.getName());
    private final static String URL = "url";
    private final static String USER = "dbuser";
    private final static String DRIVER = "driver";
    private final static String PASSWORD = "dbpassword";
    private final static String SCHEMA = "schema";
    private final static String LOG_MESSEGE_DATASOURSE = "DataSource configuration has been loaded";
    @Autowired
    private Environment environment;

    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
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
        logger.info(LOG_MESSEGE_DATASOURSE);
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
