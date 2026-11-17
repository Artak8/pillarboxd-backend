package com.artak.pillarboxd.template;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    // Return the login page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //Return the reset password page
    @GetMapping("/reset-password")
    public String resetPassword() {
        return "reset_password";
    }
}