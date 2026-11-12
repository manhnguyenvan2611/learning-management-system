package com.learningmanagementsystem.learning_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learningmanagementsystem.learning_management_system.service.AuthService;
import com.learningmanagementsystem.learning_management_system.service.CourseService;
import com.learningmanagementsystem.learning_management_system.service.UserService;

@Controller
public class CourseController {
	@Autowired
	private CourseService courseService;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;

	@GetMapping("/lessons/{id}")
	public String redirectLessons(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") int courseId, Model model) {
		int userId = 0;

		if (userDetails != null) {
			userId = authService.getLoggedInUserId(userDetails);
		}

		courseService.getCourseInforDetails(userId, courseId, userService, model);
		
		return "lessons";
	}
}



