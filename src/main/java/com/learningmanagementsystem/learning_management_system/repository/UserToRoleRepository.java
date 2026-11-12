package com.learningmanagementsystem.learning_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learningmanagementsystem.learning_management_system.model.UserToRole;

@Repository
public interface UserToRoleRepository extends JpaRepository<UserToRole, Integer> {
    UserToRole findByUserId(int userId);
}
