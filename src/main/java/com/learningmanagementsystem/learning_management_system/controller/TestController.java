package com.learningmanagementsystem.learning_management_system.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learningmanagementsystem.learning_management_system.dto.SubmitTestRequest;
import com.learningmanagementsystem.learning_management_system.model.Answer;
import com.learningmanagementsystem.learning_management_system.service.AuthService;
import com.learningmanagementsystem.learning_management_system.service.CourseService;
import com.learningmanagementsystem.learning_management_system.service.ServiceMiddleware;
import com.learningmanagementsystem.learning_management_system.service.TestService;
import com.learningmanagementsystem.learning_management_system.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TestController {
	@Autowired
	private CourseService courseService;

	@Autowired
	private UserService userService;

	@Autowired
	private TestService testService;

	@Autowired
	private AuthService authService;

	@Autowired
	private ServiceMiddleware serviceMiddleware;

	/**
	 * taking the test
	 * @param courseId
	 * @param userProgress
	 * @param model
	 * @return
	 */
	@GetMapping(path = "/test/{courseId}/{userProgress}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> takingTest(@PathVariable("courseId") int courseId, @PathVariable("userProgress") int userProgress, Model model) {
		return testService.takingTest(courseId, userProgress, testService);
	}

	/**
	 * submitting the test
	 * @param userDetails
	 * @param obj
	 * @return
	 */
	@PostMapping(path = "/submittest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> submitTest(@AuthenticationPrincipal UserDetails userDetails, @RequestBody SubmitTestRequest obj) {
		int userId = authService.checkAuthentication(userDetails);
		int userScore = obj.getUserScore();
		int courseId = obj.getCourseId();

		return testService.submitTest(userId, userScore, courseId, userService, courseService);
	}

	@PostMapping(path = "/computescore", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> computeTestScore(@RequestBody String obj) {
		Map<String, List<Map<String, String>>> objMap = serviceMiddleware.convertStringToObjectMapList(obj);
		List<Answer> answers = testService.getAllAnswer();
		List<Map<String, String>> chosenAnswers = objMap.get("chosenAnswers");
		int score = 0;

		for (Map<String, String> chosenAnwserObj: chosenAnswers) {
			int chosenAnswerId = Integer.parseInt(chosenAnwserObj.get("answerId"));
			String chosenAnswerValue = chosenAnwserObj.get("answerValue");

			for (Answer answer: answers) {
				if (answer.getId() == chosenAnswerId) {
					if (answer.getAnswer().equalsIgnoreCase(chosenAnswerValue)) {
						score += 1;
					}
				}
			}
		}

		return ResponseEntity.ok(score);
	}
	
}



