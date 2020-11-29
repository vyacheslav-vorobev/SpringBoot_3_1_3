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
import java.util.HashSet;
import java.util.Set;

@RequestMapping()
@Controller
public class Controller1 {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private RoleService roleService;

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public String getUser(Authentication authentication, Model model){
        User user = userService.findByUserName(authentication.getName());
        model.addAttribute("user",user);
        return "user";
    }
    @GetMapping("/admin")
    public String viewsUsers(Model model){
        model.addAttribute("users", userService.listUsers());
        return "allUsers";
    }
    @GetMapping("/admin/{id}")
    public String getOneUser(@PathVariable("id")Long id, Model model){
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }
    @GetMapping("/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }
    @PostMapping("/admin/new")
    public String create(@ModelAttribute("login") String login, @ModelAttribute("password") String password,
                         @ModelAttribute("firstName") String firstName, @ModelAttribute("lastName") String lastName,
                         @ModelAttribute("age") int age, @ModelAttribute("growth") int growth){
        User user = new User();
        user.setLogin(login);
        user.setPassword(encoder.encode(password));
        user.setPassword(password);
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getOne(2L));
        user.setRoles(roles);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setGrowth(growth);
        userService.addUser(user);
        return "redirect:/admin";
    }
    @GetMapping("admin/{id}/edit")
    public String edit(Model model, @PathVariable("id")Long id){
        System.out.println("зашёл в edit");
        model.addAttribute("user",userService.getUser(id));
        return "edit";
    }
    @PostMapping("admin/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id){
        System.out.println("Зашёл в UpDate");
        userService.upDate(id,user);
        return "redirect:/admin";
    }
    @GetMapping("admin/{id}/delete")
    public String delete(@PathVariable("id")Long id){
        System.out.println("Зашёл в DELETE");
        userService.remove(id);
        return "redirect:/admin";
    }
}
