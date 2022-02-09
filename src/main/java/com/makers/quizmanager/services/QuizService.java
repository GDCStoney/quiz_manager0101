package com.makers.quizmanager.services;

import com.makers.quizmanager.domain.Quiz;
import com.makers.quizmanager.exceptions.QmBadRequestException;
import com.makers.quizmanager.exceptions.QmResourceNotFoundException;

import java.util.List;

public interface QuizService {

    List<Quiz> fetchAllQuizzes();

    Quiz fetchQuizById(Integer quizId) throws QmResourceNotFoundException;

    Quiz addQuiz(String name, String description) throws QmBadRequestException;

    void updateQuiz(Integer quizId, Quiz quiz) throws QmBadRequestException;

    void removeQuizWithAllQuestions(Integer quizId) throws QmResourceNotFoundException;
}
