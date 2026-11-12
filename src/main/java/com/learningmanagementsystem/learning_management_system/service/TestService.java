package com.learningmanagementsystem.learning_management_system.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learningmanagementsystem.learning_management_system.model.Answer;
import com.learningmanagementsystem.learning_management_system.model.Course;
import com.learningmanagementsystem.learning_management_system.model.Enrollment;
import com.learningmanagementsystem.learning_management_system.model.Question;
import com.learningmanagementsystem.learning_management_system.model.Test;
import com.learningmanagementsystem.learning_management_system.model.User;
import com.learningmanagementsystem.learning_management_system.repository.AnswerRepository;
import com.learningmanagementsystem.learning_management_system.repository.QuestionRepository;
import com.learningmanagementsystem.learning_management_system.repository.TestRepository;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepo;

    @Autowired
    private QuestionRepository questionRepo;

    @Autowired
    private AnswerRepository answerRepo;

    public List<Test> getAllTest() {
        return testRepo.findAll();
    }

    public List<Answer> getAllAnswer() {
        return answerRepo.findAll();
    }

    public List<Question> getAllQuestion() {
        return questionRepo.findAll();
    }

    public List<Test> findTestById(int courseId) {
        return testRepo.findById(courseId);
    }

    public List<Question> findQuestionByTestId(int testId) {
        return questionRepo.findByTestId(testId);
    }

	/**
	 * function for getting and taking the test
	 * @param courseId
	 * @param userProgress
	 * @param testService
	 * @return
	 */
	public ResponseEntity<Object> takingTest(int courseId, int userProgress, TestService testService) {
		String testMessage = "";
		Map<String, Object> response = new HashMap<>();

		if (userProgress < 100) {
			testMessage = "Please finish all the lessons first";
			response.put("testMessage", testMessage);

			return ResponseEntity.ok(response);
		}

		List<Test> test = testService.findTestById(courseId);
		List<Question> currentQuestions = testService.findQuestionByTestId(courseId);

		response.put("testMessage", testMessage);
		response.put("test", test);
		response.put("currentQuestions", currentQuestions);

		return ResponseEntity.ok(response);
	}

	/**
	 * function for submitting the test
	 * @param userId
	 * @param userScore
	 * @param courseId
	 * @param userService
	 * @param courseService
	 * @return
	 */
	public ResponseEntity<Object> submitTest(int userId, int userScore, int courseId, UserService userService, CourseService courseService) {
		String success = "false";
		User user = (userService.findUserById(userId)).get(0);
		int currentUserScore = user.getScore();
		Course course = (courseService.findCourseById(courseId)).get(0);
		int currentCourseThreshold = course.getThreshold();
		int minScore = course.getMinScore();
		Enrollment enrollment = userService.findRegisteredCourse(userId, courseId);

		if (currentUserScore >= (currentCourseThreshold + minScore)) {
			return ResponseEntity.ok(success);
		} else if (userScore >= minScore) {
			success = "true";
			user.setScore(userScore + currentUserScore);
			userService.saveUser(user);
			enrollment.setStatus("passed");
			userService.registerCourse(enrollment);
		}

		return ResponseEntity.ok(success);
	}
}
