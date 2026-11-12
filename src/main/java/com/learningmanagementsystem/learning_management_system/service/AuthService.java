package com.learningmanagementsystem.learning_management_system.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.learningmanagementsystem.learning_management_system.model.EmailDetails;
import com.learningmanagementsystem.learning_management_system.model.Role;
import com.learningmanagementsystem.learning_management_system.model.User;
import com.learningmanagementsystem.learning_management_system.model.UserToRole;
import com.learningmanagementsystem.learning_management_system.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private UserService userService;

    @Autowired
    private EmailServiceImpl emailServiceImpl;

	@Autowired
	private RoleService roleService;
    
	/**
	 * function for registering a new user
	 * @param user
	 * @return
	 */
    public User registerUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

	/**
	 * function for getting userid when a user log in
	 * @param userDetails
	 * @return
	 */
	public int getLoggedInUserId(UserDetails userDetails) {
		User loggedInUser = null;

		if (userDetails != null) {
			String userPassword = userDetails.getPassword();
			loggedInUser = userService.findUserByPassword(userPassword);
		}

		return loggedInUser.getId();
	}

	/**
	 * function for checking if there's a user has logged in
	 * @param userDetails
	 * @return
	 */
	public int checkAuthentication(UserDetails userDetails) {
		int userId = 0;

		if (userDetails != null) {
			userId = this.getLoggedInUserId(userDetails);
		}

		return userId;
	}

	/**
	 * function for form validating when a user sign up 
	 * @param username
	 * @param email
	 * @param password
	 * @param success
	 * @return
	 */
    public ResponseEntity<Object> validateRegister(String username, String email, 
        String password, String success) {
        List<String> errors = new ArrayList<>();
        User userEmailCheck = (userRepo.findByEmail(email));

        if (userEmailCheck != null) {
            errors.add("This email has already existed");
        }

        if (password.length() < 6) {
            errors.add("Password must be at least 6 characters");
        }

        if (errors.size() == 0) {
            User newUser = new User(username, email, password, "", 0);
            User newRegisterUser = this.registerUser(newUser);

            if (newRegisterUser != null) {
                success = "true";
				EmailDetails emailDetails = new EmailDetails(email);
				emailServiceImpl.sendSimpleMail(emailDetails);

				Role currentRole = roleService.findRoleByRoleName("USER");
				UserToRole newUserRole = new UserToRole(newRegisterUser.getId(), currentRole.getId());
				roleService.saveUserRole(newUserRole);
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("errors", errors);

		return ResponseEntity.ok(response);
    }
}
