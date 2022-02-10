package com.makers.quizmanager.repositories;

import com.makers.quizmanager.domain.Question;
import com.makers.quizmanager.exceptions.QmBadRequestException;
import com.makers.quizmanager.exceptions.QmResourceNotFoundException;

import java.util.List;

public interface QuestionRepository {

    List<Question> findAll(Integer quizId);

    Question findById(Integer quizId, Integer questionId) throws QmResourceNotFoundException;

    Integer create(Integer quizId, String questionText) throws QmBadRequestException;

    void update(Integer quizId, Integer questionId, Question question) throws QmBadRequestException;

    void removeById(Integer quizId, Integer questionId) throws QmResourceNotFoundException;
}
