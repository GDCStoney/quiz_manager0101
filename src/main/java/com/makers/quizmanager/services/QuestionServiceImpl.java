package com.makers.quizmanager.services;

import com.makers.quizmanager.domain.Question;
import com.makers.quizmanager.exceptions.QmBadRequestException;
import com.makers.quizmanager.exceptions.QmResourceNotFoundException;

import java.util.List;

public class QuestionServiceImpl implements QuestionService{
    @Override
    public List<Question> fetchAllQuestions(Integer quizId) {
        return null;
    }

    @Override
    public Question fetchQuestionById(Integer quizId, Integer questionId) throws QmResourceNotFoundException {
        return null;
    }

    @Override
    public Question addQuestion(Integer quizId, String question) throws QmBadRequestException {
        return null;
    }

    @Override
    public void updateQuestion(Integer quizId, Integer questionId, Question question) throws QmBadRequestException {

    }

    @Override
    public void removeQuestionWithAllResponses(Integer quizId, Integer questionId) throws QmResourceNotFoundException {

    }
}
