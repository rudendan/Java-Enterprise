package com.example.homework6.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class ConnectionConfig {

    @Value("${spring.datasource.username}")
    private String LOGIN;
    @Value("${spring.datasource.password}")
    private String PASSWORD;
    @Value("${spring.datasource.url}")
    private String PATH_DB;

    @Bean
    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(PATH_DB, LOGIN, PASSWORD);
    }
}
