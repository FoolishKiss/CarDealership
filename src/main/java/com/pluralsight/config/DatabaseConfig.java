package com.pluralsight.config;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DatabaseConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/millerauto";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "yearup";

    public static DataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(URL);
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
        return ds;
    }
}
