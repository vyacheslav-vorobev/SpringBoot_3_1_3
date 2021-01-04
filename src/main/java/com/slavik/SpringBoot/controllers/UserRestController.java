package com.slavik.SpringBoot.controllers;


import com.slavik.SpringBoot.model.User;
import com.slavik.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/loadingUser")
    public User getUser(Authentication authentication) {
        return userService.findByUserName(authentication.getName());
    }
}
