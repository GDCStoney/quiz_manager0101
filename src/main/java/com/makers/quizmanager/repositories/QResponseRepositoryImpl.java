package com.makers.quizmanager.repositories;

import com.makers.quizmanager.domain.QResponse;
import com.makers.quizmanager.exceptions.QmBadRequestException;
import com.makers.quizmanager.exceptions.QmResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QResponseRepositoryImpl implements QResponseRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<QResponse> findAll(Integer quizId, Integer questionId) {
        return null;
    }

    @Override
    public QResponse findById(Integer quizId, Integer questionId, Integer qResponseId) throws QmResourceNotFoundException {
        return null;
    }

    @Override
    public Integer create(Integer quizId, Integer questionId, String responseText, Boolean correctAnswer) throws QmBadRequestException {
        return null;
    }

    @Override
    public void update(Integer quizId, Integer questionId, Integer qResponseId, QResponse qResponse) throws QmBadRequestException {

    }

    @Override
    public void removeById(Integer quizId, Integer questionId, Integer qResponseId) throws QmResourceNotFoundException {

    }
}
