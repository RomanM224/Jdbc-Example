package com.maistruk.jdbc.dao;

import com.maistruk.jdbc.model.User;

public interface UserDao {
    
    void create(User user);
    
    void update(User user);
    
    void delete(Integer id);
    
    User getUser(Integer id);
}
