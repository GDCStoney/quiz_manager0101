package com.makers.quizmanager.resources;

import com.makers.quizmanager.domain.Question;
import com.makers.quizmanager.domain.Quiz;
import com.makers.quizmanager.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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

    @GetMapping("")
    public ResponseEntity<List<Question>> getAllQuestions(HttpServletRequest request,
                                                           @PathVariable("quizId") Integer quizId) {
        List<Question> questions = questionService.fetchAllQuestions(quizId);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestionById(HttpServletRequest request,
                                                    @PathVariable("quizId") Integer quizId,
                                                    @PathVariable("questionId") Integer questionId) {
        Question question = questionService.fetchQuestionById(quizId, questionId);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<Map<String, Boolean>> updateQuestion(HttpServletRequest request,
                                                               @PathVariable("quizId") Integer quizId,
                                                               @PathVariable("questionId") Integer questionId,
                                                               @RequestBody Question question) {
        questionService.updateQuestion(quizId, questionId, question);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Map<String, Boolean>> deleteQuestion(HttpServletRequest request,
                                                               @PathVariable("quizId") Integer quizId,
                                                               @PathVariable("questionId") Integer questionId) {
        questionService.removeQuestionWithAllResponses(quizId, questionId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
