package com.learningmanagementsystem.learning_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningmanagementsystem.learning_management_system.model.Role;
import com.learningmanagementsystem.learning_management_system.model.UserToRole;
import com.learningmanagementsystem.learning_management_system.repository.RoleRepository;
import com.learningmanagementsystem.learning_management_system.repository.UserToRoleRepository;

@Service
public class RoleService {
    @Autowired
    private UserToRoleRepository userToRoleRepo;

    @Autowired
    private RoleRepository roleRepo;

    public UserToRole saveUserRole(UserToRole userToRole) {
        return userToRoleRepo.save(userToRole);
    }

    public UserToRole findUserRoleByUserId(int userId) {
        return userToRoleRepo.findByUserId(userId);
    }

    public Role findRoleByRoleName(String roleName) {
        return roleRepo.findByRoleName(roleName);
    }

    public Role findRoleByRoleId(int roleId) {
        return roleRepo.findById(roleId);
    }
}
