package com.learningmanagementsystem.learning_management_system.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.learningmanagementsystem.learning_management_system.model.Course;
import com.learningmanagementsystem.learning_management_system.model.Enrollment;
import com.learningmanagementsystem.learning_management_system.model.Lesson;
import com.learningmanagementsystem.learning_management_system.model.UserToLesson;
import com.learningmanagementsystem.learning_management_system.repository.CourseRepository;
import com.learningmanagementsystem.learning_management_system.repository.LessonRepository;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private LessonRepository lessonRepo;
    
    public List<Course> getAllCourse() {
        return courseRepo.findAll();
    }

    public List<Lesson> getAllLesson() {
        return lessonRepo.findAll();
    }

    public List<Course> findCourseById(int courseId) {
        return courseRepo.findById(courseId);
    }

    public List<Lesson> findLessonByCourseId(int courseId) {
        return lessonRepo.findByCourseId(courseId);
    }

    public List<Lesson> findLessonById(int lessonId) {
        return lessonRepo.findById(lessonId);
    }

	public void getCourseInforDetails(int userId, int courseId, UserService userService, Model model) {
		List<UserToLesson> userLessons = userService.findUserLessonByUserId(userId); 
		Enrollment enrollmentCheck = userService.findRegisteredCourse(userId, courseId); 
		String registerState = "Register";
		String registerStatus = "";
		List<Course> course = this.findCourseById(courseId);
		List<Lesson> courseLessons = this.findLessonByCourseId(courseId);

		if (enrollmentCheck != null) {
			registerState = "Registered";
			registerStatus = enrollmentCheck.getStatus();
		}

		model.addAttribute("course", course);
		model.addAttribute("courseLessons", courseLessons);
		model.addAttribute("userLessons", userLessons);
		model.addAttribute("registerState", registerState);
		model.addAttribute("registerStatus", registerStatus);
	}
}
