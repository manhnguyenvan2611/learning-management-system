package com.learningmanagementsystem.learning_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learningmanagementsystem.learning_management_system.model.Question;
import java.util.List;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByTestId(int testId);
} 
