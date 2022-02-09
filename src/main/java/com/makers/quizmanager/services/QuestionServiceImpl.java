package com.makers.quizmanager.services;

import com.makers.quizmanager.domain.Question;
import com.makers.quizmanager.exceptions.QmBadRequestException;
import com.makers.quizmanager.exceptions.QmResourceNotFoundException;
import com.makers.quizmanager.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    QuestionRepository questionRepository;


    @Override
    public List<Question> fetchAllQuestions(Integer quizId) {
        return null;
    }

    @Override
    public Question fetchQuestionById(Integer quizId, Integer questionId) throws QmResourceNotFoundException {
        return null;
    }

    @Override
    public Question addQuestion(Integer quizId, String questionText) throws QmBadRequestException {
        int questionId = questionRepository.create(quizId, questionText);
        return questionRepository.findById(quizId, questionId);
    }

    @Override
    public void updateQuestion(Integer quizId, Integer questionId, Question question) throws QmBadRequestException {

    }

    @Override
    public void removeQuestionWithAllResponses(Integer quizId, Integer questionId) throws QmResourceNotFoundException {

    }
}
