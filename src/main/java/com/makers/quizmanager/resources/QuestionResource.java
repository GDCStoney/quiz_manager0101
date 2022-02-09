package com.makers.quizmanager.resources;

import com.makers.quizmanager.domain.Question;
import com.makers.quizmanager.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/quizzes/{quizId}/questions")
public class QuestionResource {

    @Autowired
    QuestionService questionService;

    @PostMapping("")
    public ResponseEntity<Question> addQuestion(HttpServletRequest request,
                                                @PathVariable("quizId") Integer quizId,
                                                @RequestBody Map<String, Object> questionMap) {
        String questionText = (String) questionMap.get("questionText");
        Question question = questionService.addQuestion(quizId, questionText);
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }
}
