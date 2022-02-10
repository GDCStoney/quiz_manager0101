package com.makers.quizmanager.repositories;

import com.makers.quizmanager.domain.Question;
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
public class QuestionRepositoryImpl implements QuestionRepository {

    private static final String SQL_FIND_ALL = "SELECT QUESTION_ID, QUIZ_ID, QUESTION_TEXT FROM QM_QUESTIONS " +
            "WHERE QUIZ_ID = ? ORDER BY QUESTION_ID";

    private static final String SQL_FIND_BY_ID = "SELECT QUESTION_ID, QUIZ_ID, QUESTION_TEXT FROM QM_QUESTIONS " +
            "WHERE QUIZ_ID = ? AND QUESTION_ID = ?";

    private static final String SQL_CREATE = "INSERT INTO QM_QUESTIONS (QUESTION_ID, QUIZ_ID, QUESTION_TEXT) " +
            "VALUES (NEXTVAL('QM_QUESTIONS_SEQ'), ?, ?)";

    private static final String SQL_UPDATE = "UPDATE QM_QUESTIONS SET QUESTION_TEXT = ? " +
            "WHERE QUIZ_ID = ? AND QUESTION_ID = ?";

    private static final String SQL_DELETE_BY_ID = "DELETE FROM QM_QUESTIONS " +
            "WHERE QUIZ_ID = ? AND QUESTION_ID = ?";

    private  static final String SQL_DELETE_ALL_QRESPONSES = "DELETE FROM QM_QRESPONSES " +
            "WHERE QUIZ_ID = ? AND QUESION_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Question> findAll(Integer quizId) {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{quizId}, questionRowMapper);
    }

    @Override
    public Question findById(Integer quizId, Integer questionId) throws QmResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{quizId, questionId}, questionRowMapper);
        }catch (Exception e) {
            throw new QmResourceNotFoundException("Question not Found");
        }
    }

    @Override
    public Integer create(Integer quizId, String questionText) throws QmBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                        ps.setInt(1, quizId);
                        ps.setString(2, questionText);
                        return ps;
                    }, keyHolder);
            return (Integer) keyHolder.getKeys().get("QUESTION_ID");
        }catch (Exception e) {
            throw new QmBadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer quizId, Integer questionId, Question question) throws QmBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{question.getQuestionText(), quizId, questionId });
        }catch (Exception e) {
            throw new QmBadRequestException("Invalid request");
        }
    }

    @Override
    public void removeById(Integer quizId, Integer questionId) throws QmResourceNotFoundException {
        this.removeAllQuestionQResponses(quizId, questionId);
        int count = jdbcTemplate.update(SQL_DELETE_BY_ID, new Object[]{quizId, questionId});
        if (count == 0)
            throw new QmResourceNotFoundException("Question not found");
    }

    private void removeAllQuestionQResponses(Integer quizId, Integer questionId) {
        jdbcTemplate.update(SQL_DELETE_ALL_QRESPONSES, new Object[]{quizId, questionId});
    }

    private RowMapper<Question> questionRowMapper = ((rs, rowNum) -> {
        return (new Question(
                rs.getInt("QUESTION_ID"),
                rs.getInt("QUIZ_ID"),
                rs.getString("QUESTION_TEXT")
        ));
    });
}
