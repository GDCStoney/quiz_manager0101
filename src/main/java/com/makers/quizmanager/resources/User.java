package com.makers.quizmanager.resources;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class User {

    @PostMapping("/register")
    public String registerUser(@RequestBody Map<String, Object> userMap) {
        String roleId = (String) userMap.get('roleId');
        String firstName = (String) userMap.get('firstName');
        String lastName = (String) userMap.get('lastName');
        String email = (String) userMap.get('email');
        String password = (String) userMap.get('password');

        return roleId + ", " + firstName + ", " + lastName + ", " + email + ", " + password;
    }
}
