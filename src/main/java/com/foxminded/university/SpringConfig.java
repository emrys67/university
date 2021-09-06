package com.foxminded.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:database.properties")
@ComponentScan("com.foxminded.university")
public class SpringConfig {
    @Autowired
    Environment environment;
    private final static String URL = "url";
    private final static String USER = "dbuser";
    private final static String DRIVER = "driver";
    private final static String PASSWORD = "dbpassword";

    @Bean
    public DataSource postgresDataSource() {
//        try (var inputStream = SpringConfig.class.getClassLoader().getResourceAsStream("database.properties")) {
//            properties.load(inputStream);
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(environment.getProperty(DRIVER));
            dataSource.setUrl(environment.getProperty(URL));
            dataSource.setUsername(environment.getProperty(USER));
            dataSource.setPassword(environment.getProperty(PASSWORD));
            return dataSource;
//        }
//        catch (IOException e){
//            e.printStackTrace();
//            throw new RuntimeException();
//        }
    }
}
