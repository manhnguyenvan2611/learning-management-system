package com.learningmanagementsystem.learning_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learningmanagementsystem.learning_management_system.model.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findById(int id);

    User findByUserName(String userName);

    User findByEmail(String email);

    User findByPassword(String password);
}