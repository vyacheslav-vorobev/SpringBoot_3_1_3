package com.slavik.SpringBoot.dao;

import com.slavik.SpringBoot.model.User;
import java.util.List;

public interface UserDao {
    void add(User user);
    List<User> listUsers();
    User getUser(Long id);
    void remove(Long id);
    void upDate(Long id, User user);
    User getUserByLogin(String login);
}
