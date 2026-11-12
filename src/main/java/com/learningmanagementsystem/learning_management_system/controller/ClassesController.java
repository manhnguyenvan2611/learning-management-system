package com.learningmanagementsystem.learning_management_system.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.learningmanagementsystem.learning_management_system.model.Course;
import com.learningmanagementsystem.learning_management_system.model.Enrollment;
import com.learningmanagementsystem.learning_management_system.service.ClassesService;
import com.learningmanagementsystem.learning_management_system.service.CourseService;
import com.learningmanagementsystem.learning_management_system.service.UserService;


@Controller
public class ClassesController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClassesService classesService;

    @GetMapping("/classes")
    public String getMethodName(Model model) {
        List<Course> courses = courseService.getAllCourse();
        List<Enrollment> enrollments = userService.getAllEnrollments();

        classesService.getEnrolledUserList(model, courses, enrollments, userService);

        return "classes";
    }
    
}
