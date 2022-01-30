package com.makers.quizmanager.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/quizzes")
public class QuizResource {

    @GetMapping("")
    public String getAllQuizzes(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");
        int roleId = (Integer) request.getAttribute("roleId");
        return "Authenticated with userid: " + userId + " and roleId: " + roleId;
    }
}
