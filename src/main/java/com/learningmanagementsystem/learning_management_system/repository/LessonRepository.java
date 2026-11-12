package com.learningmanagementsystem.learning_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learningmanagementsystem.learning_management_system.model.Lesson;
import java.util.List;


@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    List<Lesson> findByCourseId(int courseId);

    List<Lesson> findById(int id);
}
