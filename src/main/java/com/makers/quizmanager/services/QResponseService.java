package com.makers.quizmanager.services;

import com.makers.quizmanager.domain.QResponse;
import com.makers.quizmanager.exceptions.QmBadRequestException;
import com.makers.quizmanager.exceptions.QmResourceNotFoundException;

import java.util.List;

public interface QResponseService {

    List<QResponse> fetchAllQResponses(Integer questionId, Integer quizId);

    QResponse fetchQResponseById(Integer quizId, Integer questionId, Integer qResponseId) throws QmResourceNotFoundException;

    QResponse addQResponse(Integer quizId, Integer questionId, String qResponseText, Boolean correctAnswer) throws QmBadRequestException;

    void updateQResponse(Integer quizId, Integer questionId, Integer qResponseId, QResponse qResponse) throws QmBadRequestException;

    void removeQResponse(Integer quizId, Integer questionId, Integer qResponseId) throws QmResourceNotFoundException;

}
