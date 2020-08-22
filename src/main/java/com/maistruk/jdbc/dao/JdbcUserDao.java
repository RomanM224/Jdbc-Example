package com.maistruk.jdbc.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.maistruk.jdbc.model.User;
import com.maistruk.jdbc.service.ConnectionProvider;

public class JdbcUserDao implements UserDao {

    private static final String ADD_USER = "INSERT INTO users (id, name, date, time, timedate) VALUES (DEFAULT, ?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE users SET name = ?, date = ?, time = ?, timedate = ? WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    private static final String GET_USER = "SELECT * FROM users WHERE id = ?;";

    private ConnectionProvider connectionProvider;

    public JdbcUserDao(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public void create(User user) {
        try (Connection connection = connectionProvider.getConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setObject(2, user.getDate());
            statement.setObject(3, user.getTime());
            statement.setObject(4, user.getDateTime());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                System.out.println(user.getId());
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(User user) {
        try (Connection connection = connectionProvider.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, user.getName());
            statement.setObject(2, user.getDate());
            statement.setObject(3, user.getTime());
            statement.setObject(4, user.getDateTime());
            statement.setInt(5, user.getId());
            System.out.println(statement.execute());
            // statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        try (Connection connection = connectionProvider.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setInt(1, id);
            System.out.println(statement.execute());
            // statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(Integer id) {
        User user = new User();
        try (Connection connection = connectionProvider.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_USER)) {
            statement.setInt(1, id);
            // System.out.println(statement.execute());
            // statement.execute();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setDate(LocalDate.parse(resultSet.getString(3)));
                user.setTime(LocalTime.parse(resultSet.getString(4)));
                String dateTime = resultSet.getString(5);
                dateTime = dateTime.replace(" ", "T");
                user.setDateTime(LocalDateTime.parse(dateTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void showMetaData() {
        try (Connection connection = connectionProvider.getConnection()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            int majorVersion = databaseMetaData.getDatabaseMajorVersion();
            System.out.println("Show database major versjon: " + majorVersion);
            int minorVersion = databaseMetaData.getDatabaseMinorVersion();
            System.out.println("Show database minor versjon: " + minorVersion);
            String productName = databaseMetaData.getDatabaseProductName();
            System.out.println("Show database product name: " + productName);
            String productVersion = databaseMetaData.getDatabaseProductVersion();
            System.out.println("Show database product versjon: " + productVersion);
            int driverMajorVersion = databaseMetaData.getDriverMajorVersion();
            System.out.println("Show driver major versjon: " + driverMajorVersion);
            int driverMinorVersion = databaseMetaData.getDriverMinorVersion();
            System.out.println("Show driver minor versjon: " + driverMinorVersion);

            String catalog = null;
            String schemaPattern = null;
            String tableNamePattern = null;
            String[] types = null;

            // show tables
            ResultSet result = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types);
//            while (result.next()) {
//                System.out.println(result.getString(3));
//            }

            catalog = null;
            schemaPattern = null;
            tableNamePattern = "users";
            String columnNamePattern = null;

            // show columns
            result = databaseMetaData.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);
            while (result.next()) {
                System.out.println(result.getString(4) + " " + result.getInt(5));
            }

            catalog = null;
            String schema = null;
            String tableName = "users";

            //Show primary keys
            result = databaseMetaData.getPrimaryKeys(catalog, schema, tableName);
            while (result.next()) {
                System.out.println(result.getString(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
