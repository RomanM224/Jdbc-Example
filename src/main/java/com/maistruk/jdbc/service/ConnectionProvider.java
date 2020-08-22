package com.maistruk.jdbc.service;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {
    
    private String user;
    private String password;
    private String url;

    public ConnectionProvider(String configFileName) {
        try {
            URL urlConfig = getClass().getClassLoader().getResource(configFileName);
            Properties properties = new Properties();
            properties.load(urlConfig.openStream());
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
