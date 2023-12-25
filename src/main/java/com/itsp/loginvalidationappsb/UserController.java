// UserController.java
package com.itsp.loginvalidationappsb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @RequestMapping(value = "/user",method = RequestMethod.POST,consumes = "application/json")
    public String createUser(@RequestBody User user) {
        // Basic validation - check if username is unique
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "Username already exists. Please choose a different username.";
        }

        // Encode the password before saving
        user.setPassword(encodePassword(user.getPassword()));
        userRepository.save(user);
        return "User created successfully!";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        // Basic validation - check if the username and password match
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && verifyPassword(user.getPassword(), existingUser.getPassword())) {

            return "Login successful!";
        } else {
            return "Invalid username or password.";
        }
    }

    // Simulate password encoding (in a real scenario, use a proper encryption algorithm)
    private String encodePassword(String password) {
        // This is a simple example; use a secure password hashing algorithm in production
        return password;
    }

    // Simulate password verification (in a real scenario, use a proper encryption algorithm)
    private boolean verifyPassword(String inputPassword, String storedPassword) {
        // This is a simple example; in production, use a secure password hashing algorithm
        return inputPassword.equals(storedPassword);
    }
}
