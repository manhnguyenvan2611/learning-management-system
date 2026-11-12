package com.learningmanagementsystem.learning_management_system.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.learningmanagementsystem.learning_management_system.model.Course;
import com.learningmanagementsystem.learning_management_system.model.UserToLesson;
import com.learningmanagementsystem.learning_management_system.service.AuthService;
import com.learningmanagementsystem.learning_management_system.service.CourseService;
import com.learningmanagementsystem.learning_management_system.service.UserService;

@Controller
public class LmsController {
	@Autowired
	private CourseService courseService;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;

	@GetMapping("/")
	public String redirectHome(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		int userId = 0;

		if (userDetails != null) {
			userId = authService.getLoggedInUserId(userDetails);
		}

		List<Course> courseList = courseService.getAllCourse();
		List<UserToLesson> userLessons = userService.findUserLessonByUserId(userId); // check if user is logged in

		model.addAttribute("courses", courseList);
		model.addAttribute("userLessons", userLessons);
		model.addAttribute("userId", userId);

		return "index";
	}

	@GetMapping("/lesson")
	public String redirectLesson() {
		return "lessson";
	}

	@GetMapping("/signup")
	public String redirectSignup() {
		return "signup";
	}

	@GetMapping("/login")
	public String redirectLogin() {
		return "login";
	}
	
	@GetMapping("/test")
	public String redirectTest() {
		return "test";
	}

	@GetMapping("/account")
	public String redirectAccount(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		userService.getUserInfor(userDetails, authService, courseService, model);
		return "account";
	}
}


