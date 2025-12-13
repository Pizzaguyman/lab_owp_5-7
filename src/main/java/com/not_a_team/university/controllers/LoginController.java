package com.not_a_team.university.controllers;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.not_a_team.university.services.UserService;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class LoginController {

    // Show login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // corresponds to src/main/resources/templates/login.html
    }

    @GetMapping("/register")
    public String registerPage() {
        return "registration";
    }
    
    @PostMapping("/register")
    public String postMethodName(@RequestParam String username, @RequestParam String password, @RequestParam(defaultValue = "User") String role, Model model) {
        
        
        return "login";
    }
    

    // // Process login form
    // @PostMapping("/login")
    // public String login(@RequestParam(name = "username") String login, @RequestParam String password) {
    //     boolean isValid = UserService.validateLoginAndPassword(login, password);
    //     if (isValid) {
    //         return "index";
    //     }
    // }
}
