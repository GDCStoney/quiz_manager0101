package com.makers.quizmanager.repositories;

import com.makers.quizmanager.domain.Quiz;
import com.makers.quizmanager.exceptions.QmBadRequestException;
import com.makers.quizmanager.exceptions.QmResourceNotFoundException;

import java.util.List;

public interface QuizRepository {

    List<Quiz> findAll() throws QmResourceNotFoundException;

    Quiz findById(Integer quizId) throws QmBadRequestException;

    Integer create(String title, String description) throws QmBadRequestException;

    void update(Integer quizId, Quiz quiz) throws QmBadRequestException;

    void removeById(Integer quizId) throws QmResourceNotFoundException;
}
