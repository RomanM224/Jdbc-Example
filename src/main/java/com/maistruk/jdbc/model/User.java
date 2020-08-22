package com.maistruk.jdbc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class User {

    private Integer id;
    private String name;
    private LocalDate date;
    private LocalTime time;
    private LocalDateTime dateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", date=" + date + ", time=" + time + ", dateTime=" + dateTime
                + "]";
    }

    @Override
    public boolean equals(Object comparedUser) {
        User user = (User) comparedUser;
        if (!user.id.equals(id)) {
            return false;
        } else if (!user.name.equals(name)) {
            return false;
        } else if (!user.date.equals(date)) {
            return false;
        } else if (!user.time.equals(time)) {
            return false;
        } else if (!user.dateTime.equals(dateTime)) {
            return false;
        }
        return true;

    }

}
