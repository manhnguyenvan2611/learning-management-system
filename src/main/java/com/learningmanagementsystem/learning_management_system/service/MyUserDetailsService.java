package com.learningmanagementsystem.learning_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learningmanagementsystem.learning_management_system.model.Role;
import com.learningmanagementsystem.learning_management_system.model.User;
import com.learningmanagementsystem.learning_management_system.model.UserPrincipal;
import com.learningmanagementsystem.learning_management_system.model.UserToRole;
import com.learningmanagementsystem.learning_management_system.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = (userRepo.findByEmail(email));

        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        } else {
            UserToRole currentUserRole = roleService.findUserRoleByUserId(user.getId());
            Role currentRole = roleService.findRoleByRoleId(currentUserRole.getRoleId());
            String currentRoleName = currentRole.getRoleName();
            user.setRole(currentRoleName);
        }

        return new UserPrincipal(user);
    }
}
