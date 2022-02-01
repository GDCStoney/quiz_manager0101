package com.makers.quizmanager.services;

import com.makers.quizmanager.domain.Quiz;
import com.makers.quizmanager.exceptions.QmBadRequestException;
import com.makers.quizmanager.exceptions.QmResourceNotFoundException;
import com.makers.quizmanager.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuizServiceImpl implements QuizService{

    @Autowired
    QuizRepository quizRepository;

    @Override
    public List<Quiz> fetchAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz fetchQuizById(Integer quizId) throws QmResourceNotFoundException {
        return quizRepository.findById(quizId);
    }

    @Override
    public Quiz addQuiz(String title, String description) throws QmBadRequestException {
        int quizId = quizRepository.create(title, description);
        return quizRepository.findById(quizId);
    }

    @Override
    public void updateQuiz(Integer quizId, Quiz quiz) throws QmBadRequestException {

    }

    @Override
    public void removeQuizWithAllQuestions(Integer quizId) throws QmResourceNotFoundException {

    }
}
