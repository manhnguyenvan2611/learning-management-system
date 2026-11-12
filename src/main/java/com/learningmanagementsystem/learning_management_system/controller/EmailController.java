package com.learningmanagementsystem.learning_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.learningmanagementsystem.learning_management_system.service.EmailServiceImpl;

@Controller
public class EmailController {
    @Autowired
    private EmailServiceImpl emailServiceImpl;
}
