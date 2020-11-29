package com.slavik.SpringBoot.service;

import com.slavik.SpringBoot.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void addUser(User user);
    List<User> listUsers();
    User getUser(Long series);
    void remove(Long id);
    void upDate(Long id, User user);
    User findByUserName(String login);
    Long getIdByLogin(String login);
}
