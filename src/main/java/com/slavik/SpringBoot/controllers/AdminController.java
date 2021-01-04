package com.slavik.SpringBoot.controllers;

import com.slavik.SpringBoot.service.RoleService;
import com.slavik.SpringBoot.model.Role;
import com.slavik.SpringBoot.model.User;
import com.slavik.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@Controller
@RequestMapping()
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String viewsUsers(Authentication authentication, Model model){
        List<User> userList = userService.listUsers();
        User user = userService.findByUserName(authentication.getName());
        userList.remove(user);
        model.addAttribute("users", userList);
        model.addAttribute("user1", user);
        model.addAttribute("user", new User());
        for (User user1: userList){
            model.addAttribute(("password"+user1.getId()), user1.getPassword());
        }
        return "allUsers";
    }
}
