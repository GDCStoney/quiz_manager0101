package com.makers.quizmanager.repositories;

import com.makers.quizmanager.domain.QResponse;
import com.makers.quizmanager.exceptions.QmBadRequestException;
import com.makers.quizmanager.exceptions.QmResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class QResponseRepositoryImpl implements QResponseRepository{

    private static final String SQL_FIND_ALL = "SELECT QRESPONSE_ID, QUESTION_ID, QUIZ_ID, RESPONSE_TEXT, CORRECT_ANSWER FROM QM_QRESPONSES " +
            "WHERE QUESTION_ID = ? AND QUIZ_ID = ?";

    private static final String SQL_FIND_BY_ID = "SELECT QRESPONSE_ID, QUESTION_ID, QUIZ_ID, RESPONSE_TEXT, CORRECT_ANSWER FROM QM_QRESPONSES " +
            "WHERE QRESPONSE_ID = ? AND QUESTION_ID = ? AND QUIZ_ID = ?";

    private static final String SQL_CREATE = "INSERT INTO QM_QRESPONSES (QRESPONSE_ID, QUESTION_ID, QUIZ_ID, RESPONSE_TEXT, CORRECT_ANSWER) " +
            "VALUES (NEXTVAL('QM_QRESPONSES_SEQ'), ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE QM_QRESPONSES SET RESPONSE_TEXT = ?, CORRECT_ANSWER = ? " +
            "WHERE QRESPONSE_ID = ? AND QUESTION_ID = ? AND QUIZ_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<QResponse> findAll(Integer questionId, Integer quizId) {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{questionId, quizId}, qResponseRowMapper);
    }

    @Override
    public QResponse findById(Integer qResponseId, Integer questionId, Integer quizId) throws QmResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{qResponseId, questionId, quizId}, qResponseRowMapper);
        }catch (Exception e) {
            throw new QmResourceNotFoundException("QResponse not found");
        }
    }

    @Override
    public Integer create(Integer quizId, Integer questionId, String responseText, Boolean correctAnswer) throws QmBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, questionId);
                ps.setInt(2, quizId);
                ps.setString(3, responseText);
                ps.setBoolean(4, correctAnswer);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("QRESPONSE_ID");
        }catch (Exception e) {
            throw new QmBadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer quizId, Integer questionId, Integer qResponseId, QResponse qResponse) throws QmBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{qResponse.getResponseText(), qResponse.getCorrectAnswer(), qResponseId, questionId, quizId});
        }catch (Exception e) {
            throw new QmResourceNotFoundException("QResponse not found");
        }
    }

    @Override
    public void removeById(Integer quizId, Integer questionId, Integer qResponseId) throws QmResourceNotFoundException {

    }

    private RowMapper<QResponse> qResponseRowMapper = ((rs, rowNum) -> {
       return (new QResponse(
               rs.getInt("QRESPONSE_ID"),
               rs.getInt("QUESTION_ID"),
               rs.getInt("QUIZ_ID"),
               rs.getString("RESPONSE_TEXT"),
               rs.getBoolean("CORRECT_ANSWER")
       ));
    });
}
