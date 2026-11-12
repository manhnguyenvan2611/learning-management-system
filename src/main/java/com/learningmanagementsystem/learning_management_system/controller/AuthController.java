package com.learningmanagementsystem.learning_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.learningmanagementsystem.learning_management_system.dto.RegisterUserRequest;
import com.learningmanagementsystem.learning_management_system.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {
    @Autowired
    private AuthService authService;

    /**
     * register form for validating and registering new user 
     * @param obj
     * @return
     */
	@PostMapping(path = "/registeruser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registerUser(@RequestBody RegisterUserRequest obj) {
        String username = obj.getUsername();
        String email = obj.getEmail();
        String password = obj.getPassword();
        String success = "false";

        return authService.validateRegister(username, email, password, success);
	}

    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        
        return "redirect:/login?logout";
    }

}
