package com.maistruk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

import com.maistruk.jdbc.dao.JdbcUserDao;
import com.maistruk.jdbc.model.User;
import com.maistruk.jdbc.service.ConnectionProvider;

public class Main {

    public static void main(String[] args) throws Exception {
        String configName = "config.properties";
        ConnectionProvider connectionProvider = new ConnectionProvider(configName);
        JdbcUserDao userDao = new JdbcUserDao(connectionProvider);
        Scanner scanner = new Scanner(System.in);
        int select;
        do {
            System.out.println("1. Create User");
            System.out.println("2. Update User");
            System.out.println("3. Delete User by id");
            System.out.println("4. Get User by id");
            System.out.println("5. Show meta data");
            System.out.print("Choice menu: ");
            select = scanner.nextInt();
            scanner.nextLine();
            selectOption(select, scanner, userDao);
        } while (select != 0);

    }

    private static void selectOption(int select, Scanner scanner, JdbcUserDao userDao) {
        if (select == 1) {
            createUser(scanner, userDao);
        } else if (select == 2) {
            updateUser(scanner, userDao);
        } else if (select == 3) {
            deleteUser(scanner, userDao);
        } else if (select == 4) {
            getUser(scanner, userDao);
        } else if (select == 5) {
            showMetaData(userDao);
        }

    }

 

    private static void createUser(Scanner scanner, JdbcUserDao userDao) {
        System.out.println("Insert user name: ");
        User user = new User();
        user.setName(scanner.nextLine());
        System.out.println("Insert date (2020-05-05): ");
        user.setDate(LocalDate.parse(scanner.nextLine()));
        System.out.println("Insert time (19:30): ");
        user.setTime(LocalTime.parse(scanner.nextLine()));
        System.out.println("Insert date and time (2020-05-05T19:30): ");
        user.setDateTime(LocalDateTime.parse(scanner.nextLine()));
        userDao.create(user);
    }

    private static void updateUser(Scanner scanner, JdbcUserDao userDao) {
        User user = new User();
        System.out.println("Insert user id: ");
        user.setId(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Insert user name: ");
        user.setName(scanner.nextLine());
        System.out.println("Insert date (2020-05-05): ");
        user.setDate(LocalDate.parse(scanner.nextLine()));
        System.out.println("Insert time (19:30): ");
        user.setTime(LocalTime.parse(scanner.nextLine()));
        System.out.println("Insert date and time (2020-05-05T19:30): ");
        user.setDateTime(LocalDateTime.parse(scanner.nextLine()));
        userDao.update(user);
    }

    private static void deleteUser(Scanner scanner, JdbcUserDao userDao) {
        System.out.println("Insert user id: ");
        userDao.delete(scanner.nextInt());
        scanner.nextLine();

    }

    private static void getUser(Scanner scanner, JdbcUserDao userDao) {
        System.out.println("Insert user id: ");
        System.out.println(userDao.getUser(scanner.nextInt()));
        scanner.nextLine();
    }
    
    private static void showMetaData(JdbcUserDao userDao) {
        userDao.showMetaData();
    }

}
