package com.makers.quizmanager.resources;

import com.makers.quizmanager.domain.Quiz;
import com.makers.quizmanager.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/quizzes")
public class QuizResource {

    @Autowired
    QuizService quizService;

    @GetMapping("")
    public String getAllQuizzes(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");
        int roleId = (Integer) request.getAttribute("roleId");
        return "Authenticated with userid: " + userId + " and roleId: " + roleId;
    }

    @GetMapping("/{quizId")
    public ResponseEntity<Quiz> getQuizById(HttpServletRequest request,
                                            @PathVariable("quizId") Integer quizId) {
        Quiz quiz = quizService.fetchQuizById(quizId);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Quiz> addQuiz(HttpServletRequest request,
                                        @RequestBody Map<String, Object> categoryMap) {
        String title = (String) categoryMap.get("title");
        String description = (String) categoryMap.get("description");
        Quiz quiz = quizService.addQuiz(title, description);
        return new ResponseEntity<>(quiz, HttpStatus.CREATED);
    }
}
