package com.learningmanagementsystem.learning_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learningmanagementsystem.learning_management_system.model.Course;
import java.util.List;


@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {    
    List<Course> findById(int id);
} 
