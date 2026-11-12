package com.learningmanagementsystem.learning_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learningmanagementsystem.learning_management_system.model.Test;
import java.util.List;


@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
    List<Test> findById(int id);
}
