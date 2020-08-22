package com.maistruk.jdbc;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.jdbc.ScriptRunner;

import com.maistruk.jdbc.service.ConnectionProvider;


public abstract class DaoTest {
    
    protected ConnectionProvider connectionProvider;

    public void setUp() {
        connectionProvider = new ConnectionProvider("config.properties");
        try (Connection connection = connectionProvider.getConnection()) {
            ScriptRunner runner = new ScriptRunner(connection);
            runner.runScript(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("schema.sql")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
