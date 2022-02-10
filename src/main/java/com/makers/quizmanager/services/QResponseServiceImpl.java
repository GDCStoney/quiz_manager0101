package com.makers.quizmanager.services;

import com.makers.quizmanager.domain.QResponse;
import com.makers.quizmanager.exceptions.QmBadRequestException;
import com.makers.quizmanager.exceptions.QmResourceNotFoundException;
import com.makers.quizmanager.repositories.QResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QResponseServiceImpl implements QResponseService{

    @Autowired
    QResponseRepository qResponseRepository;


    @Override
    public List<QResponse> fetchAllQResponses(Integer quizId, Integer questionId) {
        return null;
    }

    @Override
    public QResponse fetchQResponseById(Integer quizId, Integer questionId, Integer qResponseId) throws QmResourceNotFoundException {
        return null;
    }

    @Override
    public QResponse addQResponse(Integer quizId, Integer questionId, String qResponseText, Boolean correctAnswer) throws QmBadRequestException {
        return null;
    }

    @Override
    public void updateQResponse(Integer quizId, Integer questionId, Integer qResponseId, QResponse qResponse) throws QmBadRequestException {

    }

    @Override
    public void removeQResponse(Integer quizId, Integer questionId, Integer qResponseId) throws QmResourceNotFoundException {

    }
}
