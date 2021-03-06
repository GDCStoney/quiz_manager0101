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
    public List<QResponse> fetchAllQResponses(Integer questionId, Integer quizId) {
        return qResponseRepository.findAll(questionId, quizId);
    }

    @Override
    public QResponse fetchQResponseById(Integer qResponseId, Integer questionId, Integer quizId ) throws QmResourceNotFoundException {
        return qResponseRepository.findById(qResponseId, questionId, quizId);
    }

    @Override
    public QResponse addQResponse(Integer quizId, Integer questionId, String qResponseText, Boolean correctAnswer) throws QmBadRequestException {
        int qResponseId = qResponseRepository.create(quizId, questionId, qResponseText, correctAnswer);
        return qResponseRepository.findById(qResponseId, questionId, quizId);
    }

    @Override
    public void updateQResponse(Integer quizId, Integer questionId, Integer qResponseId, QResponse qResponse) throws QmBadRequestException {
        qResponseRepository.update(quizId, questionId, qResponseId, qResponse);
    }

    @Override
    public void removeQResponse(Integer quizId, Integer questionId, Integer qResponseId) throws QmResourceNotFoundException {
        qResponseRepository.removeById(quizId, questionId, qResponseId);
    }
}
