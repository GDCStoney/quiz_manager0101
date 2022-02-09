package com.makers.quizmanager.services;

import com.makers.quizmanager.domain.Question;
import com.makers.quizmanager.exceptions.QmBadRequestException;
import com.makers.quizmanager.exceptions.QmResourceNotFoundException;

import java.util.List;

public interface QuestionService {

    List<Question> fetchAllQuestions(Integer quizId);

    Question fetchQuestionById(Integer quizId, Integer questionId) throws QmResourceNotFoundException;

    Question addQuestion(Integer quizId, String question) throws QmBadRequestException;

    void updateQuestion(Integer quizId, Integer questionId, Question question) throws QmBadRequestException;

    void removeQuestionWithAllResponses(Integer quizId, Integer questionId) throws QmResourceNotFoundException;

}
