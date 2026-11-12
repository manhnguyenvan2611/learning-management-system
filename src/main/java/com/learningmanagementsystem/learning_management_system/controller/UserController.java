package com.learningmanagementsystem.learning_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.learningmanagementsystem.learning_management_system.dto.JoinLessonRequest;
import com.learningmanagementsystem.learning_management_system.dto.RegisterCourseRequest;
import com.learningmanagementsystem.learning_management_system.dto.SaveUserLessonRequest;
import com.learningmanagementsystem.learning_management_system.service.AuthService;
import com.learningmanagementsystem.learning_management_system.service.CourseService;
import com.learningmanagementsystem.learning_management_system.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {
	@Autowired
	private CourseService courseService;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;

	/**
	 * events happen when the user click on a lesson
	 * @param userDetails
	 * @param lessonObj
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/joinlesson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> joinLesson(@AuthenticationPrincipal UserDetails userDetails, @RequestBody JoinLessonRequest lessonObj, Model model) { 
		int userId = authService.checkAuthentication(userDetails);
		int courseId = lessonObj.getCourseId();
		int lessonId = lessonObj.getLessonId();

		return userService.joinLesson(userId, courseId, lessonId, userService, courseService);
	}

	/**
	 * events happen when the user click register to register a course
	 * @param userDetails
	 * @param obj
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registerCourse(@AuthenticationPrincipal UserDetails userDetails, @RequestBody RegisterCourseRequest obj, Model model) { 
		int userId = authService.checkAuthentication(userDetails);
		int courseId = obj.getCourseId();
		int lessonId = 0;
	
		return userService.registerCourse(userId, courseId, lessonId, userService, courseService);
	}

	/**
	 * events happen when the user finish a lesson
	 * @param userDetails
	 * @param obj
	 * @return
	 */
	@PostMapping(path = "/save/userlesson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveUserLesson(@AuthenticationPrincipal UserDetails userDetails, @RequestBody SaveUserLessonRequest obj) {
		int userId = authService.checkAuthentication(userDetails);
		int currentLessonId = obj.getCurrentLessonId();
		int currentLessonProgress = obj.getCurrentLessonProgress();
		int nextLessonId = obj.getNextLessonId();
		int courseId = obj.getCourseId();
		
		return userService.saveUserLesson(userId, currentLessonId, 
			currentLessonProgress, nextLessonId, courseId, userService, courseService);
	}
	
}



