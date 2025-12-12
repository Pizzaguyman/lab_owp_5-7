package com.not_a_team.university;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(UserRepository userRepository, UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPath(HttpSession session, Model model, @RequestParam(required = false) boolean error) {
        if (userService.getUserBySession(session).isPresent()) {
            return "redirect:/profile";
        }

        return "login";
    }

    @PostMapping("/login")
    public String loginForm(HttpSession session, Model model, @RequestParam String username, @RequestParam String password) {
        if (!userService.checkLoginInfo(session, username, password)) {
            model.addAttribute("errorMessage", "Неправильный логин или пароль");
            return "login";
        }

        User user = userService.getUserByUsername(username).get();
        user.addSesseion(session);

        return "profile";
    }
}
