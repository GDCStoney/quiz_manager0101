package com.makers.quizmanager.resources;

import com.makers.quizmanager.Constants;
import com.makers.quizmanager.domain.User;
import com.makers.quizmanager.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    
    @Autowired
    UserService userService;

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.validateUser(email, password);
        Map<String, String> map = generateJWTToken(user);
        map.put("userId", user.getUserId().toString());
        map.put("firstName", user.getFirstName());
        map.put("lastName", user.getLastName());
        map.put("email", user.getEmail());
        map.put("roleId", user.getRoleId().toString());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap) {
        Integer roleId = (Integer) userMap.get("roleId");
        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user= userService.registerUser(firstName, lastName, roleId, email, password);
        Map<String, String> map = generateJWTToken(user);
        map.put("message", "User " + firstName + " " + lastName + " created successfully. Please login to access Quiz Manager.");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    private Map<String, String> generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("roleId", user.getRoleId())
                .claim("firstName", user.getFirstName())
                .claim("lastname", user.getLastName())
                .claim("email", user.getEmail())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}
