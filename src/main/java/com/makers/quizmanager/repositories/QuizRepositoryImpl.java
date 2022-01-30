package com.makers.quizmanager.repositories;

import com.makers.quizmanager.domain.Quiz;
import com.makers.quizmanager.exceptions.QmBadRequestException;
import com.makers.quizmanager.exceptions.QmResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizRepositoryImpl implements QuizRepository{
    @Override
    public List<Quiz> findAll() throws QmResourceNotFoundException {
        return null;
    }

    @Override
    public Quiz findById(Integer quizId) throws QmBadRequestException {
        return null;
    }

    @Override
    public Integer create(String title, String description) throws QmBadRequestException {
        return null;
    }

    @Override
    public void update(Integer quizId, Quiz quiz) throws QmBadRequestException {

    }

    @Override
    public void removeById(Integer quizId) throws QmResourceNotFoundException {

    }
}
