package com.makers.quizmanager.resources;

import com.makers.quizmanager.domain.Quiz;
import com.makers.quizmanager.services.QuizService;
import com.makers.quizmanager.services.ConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quizzes")
public class QuizResource {

    @Autowired
    QuizService quizService;

    @Autowired
    ConvertService convertService;

    @GetMapping("")
    public ResponseEntity<String> getAllQuizzes(HttpServletRequest request) {
        List<Quiz> quizzes = quizService.fetchAllQuizzes();
        String jsonQuizzes = convertService.convertQuizListToJSON(quizzes);
        return new ResponseEntity<>(jsonQuizzes, HttpStatus.OK);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<Quiz> getQuizById(HttpServletRequest request,
                                            @PathVariable("quizId") Integer quizId) {
        Quiz quiz = quizService.fetchQuizById(quizId);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Quiz> addQuiz(HttpServletRequest request,
                                        @RequestBody Map<String, Object> quizMap) {
        String name = (String) quizMap.get("name");
        String description = (String) quizMap.get("description");
        Quiz quiz = quizService.addQuiz(name, description);
        return new ResponseEntity<>(quiz, HttpStatus.CREATED);
    }

    @PutMapping("/{quizId}")
    public ResponseEntity<Map<String, Boolean>> updateQuiz(HttpServletRequest request,
                                                     @PathVariable("quizId") Integer quizId,
                                                     @RequestBody Quiz quiz) {
        quizService.updateQuiz(quizId, quiz);
        Map <String, Boolean> map = new HashMap<>();
        map.put("Success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
