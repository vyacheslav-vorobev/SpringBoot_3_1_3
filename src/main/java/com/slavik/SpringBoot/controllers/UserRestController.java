package com.slavik.SpringBoot.controllers;


import com.slavik.SpringBoot.model.User;
import com.slavik.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public User getUser(Authentication authentication) {
//        model.addAttribute("user", user);
//        model.addAttribute("userLogin", user.getLogin());
//        String role;
//        if (user.getRoles().size() > 1) {
//            role = "ROLE ADMIN";
//        } else {
//            role = "ROLE USER";
//        }
//        model.addAttribute("role", role);
        return userService.findByUserName(authentication.getName());
    }
    @GetMapping("/admin/{id}")
    public User getOneUser(@PathVariable("id")Long id, Model model){
        return  userService.getUser(id);
    }
}
