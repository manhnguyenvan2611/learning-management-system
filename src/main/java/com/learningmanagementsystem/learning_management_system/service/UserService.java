package com.learningmanagementsystem.learning_management_system.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.google.gson.Gson;
import com.learningmanagementsystem.learning_management_system.model.Course;
import com.learningmanagementsystem.learning_management_system.model.Enrollment;
import com.learningmanagementsystem.learning_management_system.model.Lesson;
import com.learningmanagementsystem.learning_management_system.model.User;
import com.learningmanagementsystem.learning_management_system.model.UserToLesson;
import com.learningmanagementsystem.learning_management_system.repository.EnrollmentRepository;
import com.learningmanagementsystem.learning_management_system.repository.UserRepository;
import com.learningmanagementsystem.learning_management_system.repository.UserToLessonRepository;

@Service
public class UserService {
    @Autowired
    private EnrollmentRepository enrollmentRepo;

    @Autowired
    private UserToLessonRepository userToLessonRepo;

    @Autowired
    private UserRepository userRepo;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepo.findAll();
    }

    public Enrollment findRegisteredCourse(int userId, int CourseId) {
        Enrollment enrollment = enrollmentRepo.findByUserIdAndCourseId(CourseId, userId);
        return enrollment;
    }

    public void registerCourse(Enrollment enrollment) {
        enrollmentRepo.save(enrollment);
    }

    public void registerLesson(UserToLesson userLesson) {
        userToLessonRepo.save(userLesson);
    }

    public UserToLesson findLessonByUserIdCourseIdLessonId(int userId, 
        int courseId, int lessonId) {
        return userToLessonRepo.findLessonByUserIdCourseIdLessonId(userId, courseId, lessonId);
    }

    public List<User> findUserById(int userId) {
        return userRepo.findById(userId);
    }

    public List<UserToLesson> findUserLessonByUserId(int userId) {
        return userToLessonRepo.findByUserId(userId);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

	public User findUserByPassword(String password) {
		return userRepo.findByPassword(password);
	}

	public List<Enrollment> findRegisterdCourseByUserId(int userId) {
		return enrollmentRepo.findByUserId(userId);
	}

	/**
	 * function for checking user's condition whether they are qualified for entering the lesson
	 * @param userId
	 * @param courseId
	 * @param lessonId
	 * @param userService
	 * @param courseService
	 * @return
	 */
	public ResponseEntity<Object> joinLesson(int userId, int courseId, int lessonId, UserService userService, CourseService courseService) {
		Map<String, String> response = new HashMap<String, String>();
		UserToLesson userToLessonCheck = userService.findLessonByUserIdCourseIdLessonId(userId, courseId, lessonId);
		String lessonRegisterMessage = "";

		if (userToLessonCheck == null) {
			lessonRegisterMessage = "You cannot enter this lesson";
		} else {
			Gson gson = new Gson();
			List<Lesson> lesson = courseService.findLessonById(lessonId);

			return ResponseEntity.ok(gson.toJson(lesson));
		}

		response.put("lessonRegisterMessage", lessonRegisterMessage);
		return ResponseEntity.ok(response);
	}

	/**
	 * function lets the user register a new course
	 * @param userId
	 * @param courseId
	 * @param lessonId
	 * @param userService
	 * @param courseService
	 * @return
	 */
	public ResponseEntity<Object> registerCourse(int userId, int courseId, int lessonId, UserService userService, CourseService courseService) {
		List<Course> course = courseService.findCourseById(courseId);
		List<User> user = userService.findUserById(userId);
		Enrollment enrollmentCheck = userService.findRegisteredCourse(userId, courseId);
        
		int threshold = course.get(0).getThreshold();
		int userScore = user.get(0).getScore();
		String registerMessage = "";
		String success = "false";
		String registerStatus = "";
		String registerState = "Register";
		Map<String, String> response = new HashMap<String, String>();

        if (enrollmentCheck != null) {
            registerMessage = "Already registered";
			registerState = "Registered";
			registerStatus = enrollmentCheck.getStatus();

			response.put("lessonId", Integer.toString(lessonId));
			response.put("courseId", Integer.toString(courseId));
        } else if (userScore < threshold) {
			registerMessage = "You are not qualified to register this course!";
		} else {
			String status = "in progress";
			Enrollment newEnrollment = new Enrollment(userId, courseId, status);

			userService.registerCourse(newEnrollment);

			success = "true";
			registerMessage = "Register successfully!";
			registerStatus = status;
			registerState = "Registered";
			List<Lesson> lesson = courseService.findLessonByCourseId(courseId);
			lessonId = lesson.get(0).getId();
			UserToLesson userLesson = new UserToLesson(userId, courseId, lessonId, 0);
			
			userService.registerLesson(userLesson);
			response.put("lessonId", Integer.toString(lessonId));
			response.put("courseId", Integer.toString(courseId));
        }

		response.put("registerMessage", registerMessage);
		response.put("success", success);
		response.put("registerStatus", registerStatus);
		response.put("registerState", registerState);

   		return ResponseEntity.ok(response);	
	}

	/**
	 * function for saving lesson's information with the user when moving to the next lesson
	 * @param userId
	 * @param currentLessonId
	 * @param currentLessonProgress
	 * @param nextLessonId
	 * @param courseId
	 * @param userService
	 * @param courseService
	 * @return 
	 */
	public ResponseEntity<Object> saveUserLesson(int userId, int currentLessonId, 
		int currentLessonProgress, int nextLessonId, int courseId, UserService userService, CourseService courseService) {
		String success = "false";
		List<Lesson> lessons = courseService.findLessonByCourseId(courseId);
		int lessonUnits = lessons.size();
		int lastLessonId = lessons.get(lessonUnits - 1).getId();

		UserToLesson nextUserToLesson = userService.findLessonByUserIdCourseIdLessonId(userId, courseId, nextLessonId);
		UserToLesson currentUserToLesson = userService.findLessonByUserIdCourseIdLessonId(userId, courseId, currentLessonId);

		if (currentUserToLesson == null) { // for the last lesson of the course
            UserToLesson newLesson = new UserToLesson(userId, courseId, currentLessonId, 0);
            userService.registerLesson(newLesson);
		}

		currentUserToLesson = userService.findLessonByUserIdCourseIdLessonId(userId, courseId, currentLessonId);

		if (currentUserToLesson.getProgress() == 0) {			
			if ((currentLessonId != lastLessonId) && (nextUserToLesson == null)) {
				UserToLesson userLesson = new UserToLesson(userId, courseId, nextLessonId, 0);
				userService.registerLesson(userLesson);
			} 

			currentUserToLesson.setProgress(currentLessonProgress);
			userService.registerLesson(currentUserToLesson);
			success = "true";
		}

		return ResponseEntity.ok(success);
	}

	/**
	 * function for getting user's information to display in account page when the user logging in
	 * @param userDetails
	 * @param authService
	 * @param courseService
	 * @param model
	 */
	public void getUserInfor(UserDetails userDetails, AuthService authService, CourseService courseService, Model model) {
		int userId = 0;
		int userScore = 0;
		int currentLevel = 0;
		String userName = "";
		List<Enrollment> enrollments = null;
		List<Course> courses = courseService.getAllCourse();
		List<Course> registeredCourse = new ArrayList<>();
		List<UserToLesson> userLessons = null; 

		if (userDetails != null) {
			userId = authService.getLoggedInUserId(userDetails);
			User loggedInUser = (this.findUserById(userId)).get(0);
			userScore = loggedInUser.getScore();
			enrollments = enrollmentRepo.findByUserId(userId);
			userLessons = this.findUserLessonByUserId(userId);
			userName = loggedInUser.getUserName();

			for (Course course: courses) {
				for (Enrollment enrollment: enrollments) {
					if (enrollment.getCourseId() == course.getId()) {
						registeredCourse.add(course);
					}
				}
			}

			for (Course course: registeredCourse) {
				int courseLevel = course.getLevel();

				if (currentLevel < courseLevel) {
					currentLevel = courseLevel;
				}
			}
		}

		model.addAttribute("userLessons", userLessons);
		model.addAttribute("userScore", userScore);
		model.addAttribute("currentLevel", currentLevel);
		model.addAttribute("userName", userName);
		model.addAttribute("enrollments", enrollments);
		model.addAttribute("courses", registeredCourse);
	}

}



