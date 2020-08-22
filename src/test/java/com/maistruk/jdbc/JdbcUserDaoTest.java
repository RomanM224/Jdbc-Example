package com.maistruk.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.maistruk.jdbc.dao.JdbcUserDao;
import com.maistruk.jdbc.model.User;

public class JdbcUserDaoTest extends DaoTest {
    
    private JdbcUserDao userDao;

    @BeforeEach
    public void setUp() {
        super.setUp();
        userDao = new JdbcUserDao(connectionProvider);
    }
    
    @Test
    public void createNewUser_whenCreated_thenGetGeneratedId() {
        User expectedUser = new User();
        expectedUser.setId(4);
        expectedUser.setName("Roma");
        expectedUser.setDate(LocalDate.parse("2020-05-05"));
        expectedUser.setTime(LocalTime.parse("19:30"));
        expectedUser.setDateTime(LocalDateTime.parse("2020-05-05T19:30"));
        User actualUser = new User();
        actualUser.setName("Roma");
        actualUser.setDate(LocalDate.parse("2020-05-05"));
        actualUser.setTime(LocalTime.parse("19:30"));
        actualUser.setDateTime(LocalDateTime.parse("2020-05-05T19:30"));
        userDao.create(actualUser);
        assertEquals(expectedUser, actualUser);
    }
    
    @Test
    public void givenUserId_whenGetUser_thenGetUser() {
        User expectedUser = new User();
        expectedUser.setId(2);
        expectedUser.setName("Vika");
        expectedUser.setDate(LocalDate.parse("2020-06-06"));
        expectedUser.setTime(LocalTime.parse("04:30"));
        expectedUser.setDateTime(LocalDateTime.parse("2020-05-05T19:30"));
        User actualUser = userDao.getUser(2);
        assertEquals(expectedUser, actualUser);
    }
    
    
}
