package com.makers.quizmanager.resources;

import com.makers.quizmanager.domain.QResponse;
import com.makers.quizmanager.services.QResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quizzes/{quizId}/questions/{questionId}/qresponses")
public class QResponseResource {

    @Autowired
    QResponseService qResponseService;


    @PostMapping("")
    public ResponseEntity<QResponse> addQResponse(HttpServletRequest request,
                                                  @PathVariable("quizId") Integer quizId,
                                                  @PathVariable("questionId") Integer questionId,
                                                  @RequestBody Map<String, Object> qResponseMap) {
        String responseText = (String) qResponseMap.get("responseText");
        Boolean correctAnswer = (Boolean) qResponseMap.get("correctAnswer");
        QResponse qResponse = qResponseService.addQResponse(quizId, questionId, responseText, correctAnswer);
        return new ResponseEntity<>(qResponse, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<QResponse>> findAllQResponses(HttpServletRequest request,
                                                             @PathVariable("quizId") Integer quizId,
                                                             @PathVariable("questionId") Integer questionId) {
        List<QResponse> qResponses = qResponseService.fetchAllQResponses(questionId, quizId);
        return new ResponseEntity<>(qResponses, HttpStatus.OK);
    }
}
