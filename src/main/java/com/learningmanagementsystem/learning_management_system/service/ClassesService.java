package com.learningmanagementsystem.learning_management_system.service;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.learningmanagementsystem.learning_management_system.model.Course;
import com.learningmanagementsystem.learning_management_system.model.EnrolledCourseUser;
import com.learningmanagementsystem.learning_management_system.model.Enrollment;
import com.learningmanagementsystem.learning_management_system.model.User;

@Service
public class ClassesService {
    public void getEnrolledUserList(Model model, List<Course> courses, List<Enrollment> enrollments, UserService userService) {
        List<List> enrolledUsers = new ArrayList<List>();

        for (Course course: courses) {
            int courseId = course.getId();
            List<EnrolledCourseUser> enrolledUserInfor = new ArrayList<EnrolledCourseUser>();

            for (Enrollment enrollment: enrollments) {
                int enrollmentCourseId = enrollment.getCourseId();

                if (courseId == enrollmentCourseId) {
                    User currentUser = (userService.findUserById(enrollment.getUserId())).get(0);
                    String username = currentUser.getUserName();
                    String avatar = currentUser.getAvatar();
                    String email = currentUser.getEmail();
                    String courseStatus = enrollment.getStatus();
                    EnrolledCourseUser enrolledUserObj = new EnrolledCourseUser(username, avatar, email, courseStatus);
                    enrolledUserInfor.add(enrolledUserObj);
                }
            }

            enrolledUsers.add(enrolledUserInfor);
        }

        model.addAttribute("enrolledUsers", enrolledUsers);
        model.addAttribute("courses", courses);
    }
}
