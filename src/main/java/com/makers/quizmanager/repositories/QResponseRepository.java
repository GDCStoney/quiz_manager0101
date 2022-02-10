package com.makers.quizmanager.repositories;

import com.makers.quizmanager.domain.QResponse;
import com.makers.quizmanager.exceptions.QmBadRequestException;
import com.makers.quizmanager.exceptions.QmResourceNotFoundException;

import java.util.List;

public interface QResponseRepository {

    List<QResponse> findAll(Integer questionId, Integer quizId);

    QResponse findById(Integer qResponseId, Integer questionId, Integer quizId) throws QmResourceNotFoundException;

    Integer create(Integer quizId, Integer questionId, String responseText, Boolean correctAnswer) throws QmBadRequestException;

    void update(Integer quizId, Integer questionId, Integer qResponseId, QResponse qResponse) throws QmBadRequestException;

    void removeById(Integer quizId, Integer questionId, Integer qResponseId) throws QmResourceNotFoundException;
}
