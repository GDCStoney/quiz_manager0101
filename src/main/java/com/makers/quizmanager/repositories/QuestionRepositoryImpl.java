package com.makers.quizmanager.repositories;

import com.makers.quizmanager.domain.Question;
import com.makers.quizmanager.exceptions.QmBadRequestException;
import com.makers.quizmanager.exceptions.QmResourceNotFoundException;

import java.util.List;

public class QuestionRepositoryImpl implements QuestionRepository {
    @Override
    public List<Question> findAll(Integer quizId) {
        return null;
    }

    @Override
    public Question findById(Integer quizId, Integer questionId) throws QmResourceNotFoundException {
        return null;
    }

    @Override
    public void create(Integer quizId, String question) throws QmBadRequestException {

    }

    @Override
    public void update(Integer quizId, Integer questionId, Question question) throws QmBadRequestException {

    }

    @Override
    public void removeById(Integer quizId, Integer questionId) throws QmResourceNotFoundException {

    }
}
