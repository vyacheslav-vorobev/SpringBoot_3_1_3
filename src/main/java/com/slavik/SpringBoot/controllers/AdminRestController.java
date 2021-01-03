package com.slavik.SpringBoot.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.slavik.SpringBoot.model.Role;
import com.slavik.SpringBoot.model.User;
import com.slavik.SpringBoot.service.RoleService;
import com.slavik.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping()
public class AdminRestController {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping("users")
    public List<User> viewsUsers(){
        List<User> list = userService.listUsers();
        System.out.println(list);
        return list;
    }
    @PutMapping("admin")
    public User update(@QuartzDataSource User user){
        Set<Role> roles = new HashSet<>();
        System.out.println(user.getRolesString().contains("ADMIN"));
        if(user.getRolesString().contains("ADMIN")) {
            roles.add(roleService.getOne(1L));
            roles.add(roleService.getOne(2L));
            user.setRoles(roles);
        } else {
            roles.add(roleService.getOne(2L));
        }
        user.setRoles(roles);
        userService.upDate(user.getId(),user);
        System.out.println("сработал");
        System.out.println(user.toString());
        return userService.getUser(user.getId());
    }
    @DeleteMapping("admin/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        userService.remove(id);
        return id.toString();
    }
    @GetMapping("/admin/{id}")
    public User getOneUser(@PathVariable("id")Long id, Model model){
        return  userService.getUser(id);
    }
    @PostMapping("/admin")
    public User create(@QuartzDataSource User user){
        System.out.println("Сработал NEW");
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getOne(2L));
        if(user.getRolesString().equals("ADMIN")){
            roles.add(roleService.getOne(1L));
        }
        user.setRoles(roles);
        userService.addUser(user);
        return user;
    }
}
