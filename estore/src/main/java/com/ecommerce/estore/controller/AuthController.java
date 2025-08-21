package com.ecommerce.estore.controller;

import com.ecommerce.estore.model.User;
import com.ecommerce.estore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Show registration page
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Handle registration
    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        
         // Check for existing username
    if (userService.findByUsername(user.getUsername()) != null) {
        model.addAttribute("error", "Username already taken");
        return "register";
    }

    // Check for existing email
    if (userService.findByEmail(user.getEmail()) != null) {
        model.addAttribute("error", "Email already registered");
        return "register";
    }

    // Set default role
    user.setRole("ROLE_USER"); // Change manually in DB for admin accounts
    
    userService.register(user);
        return "redirect:/login"; // redirect instead of returning login page directly
    }

    // Show login page
      @GetMapping("/")
    public String showLogin() {
        return "login";
    }
    
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
