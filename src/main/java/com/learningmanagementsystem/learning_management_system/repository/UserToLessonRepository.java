package com.learningmanagementsystem.learning_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learningmanagementsystem.learning_management_system.model.UserToLesson;
import java.util.List;

@Repository
public interface UserToLessonRepository extends JpaRepository<UserToLesson, Integer> {
    @Query("SELECT u FROM UserToLesson u WHERE userId = :userId AND courseId = :courseId AND lessonId = :lessonId")
    UserToLesson findLessonByUserIdCourseIdLessonId(@Param("userId") int userId,
        @Param("courseId") int courseId, @Param("lessonId") int lessonId);

    List<UserToLesson> findByUserId(int userId);
} 