package com.makers.quizmanager.repositories;

import com.makers.quizmanager.domain.Quiz;
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
public class QuizRepositoryImpl implements QuizRepository{
    
    private static final String SQL_FIND_ALL = "SELECT QZ.QUIZ_ID, QZ.NAME, QZ.DESCRIPTION, COUNT(QS.QUIZ_ID) NUMBER_OF_QUESTIONS " +
            "FROM QM_QUESTIONS QS RIGHT OUTER JOIN QM_QUIZZES QZ ON QZ.QUIZ_ID = QS.QUIZ_ID " +
            "GROUP BY QZ.QUIZ_ID ORDER BY QZ.NAME";

    private static final String SQL_FIND_BY_ID = "SELECT QZ.QUIZ_ID, QZ.NAME, QZ.DESCRIPTION, COUNT(QS.QUIZ_ID) NUMBER_OF_QUESTIONS " +
            "FROM QM_QUESTIONS QS RIGHT OUTER JOIN QM_QUIZZES QZ ON QZ.QUIZ_ID = QS.QUIZ_ID " +
            "WHERE QZ.QUIZ_ID = ? GROUP BY QZ.QUIZ_ID";

    private static final String SQL_CREATE = "INSERT INTO QM_QUIZZES (QUIZ_ID, NAME, DESCRIPTION) " +
            "VALUES (NEXTVAL('QM_QUIZZES_SEQ'), ?, ?)";

    public static final String SQL_UPDATE = "UPDATE QM_QUIZZES SET NAME = ?, DESCRIPTION = ? " +
            "WHERE QUIZ_ID = ?";

    private static final String SQL_DELETE_QUIZ = "DELETE FROM QM_QUIZZES WHERE QUIZ_ID = ?";

    private static final String SQL_DELETE_ALL_QRESPONSES = "DELETE FROM QM_QRESPONSES WHERE QUIZ_ID = ?";

    private static final String SQL_DELETE_ALL_QUESTIONS = "DELETE FROM QM_QUESTIONS WHERE QUIZ_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Quiz> findAll() throws QmResourceNotFoundException {
        try {
            return jdbcTemplate.query(SQL_FIND_ALL, quizRowMapper);
        }catch (Exception e) {
            throw new QmResourceNotFoundException("Quiz not found");
        }
    }

    @Override
    public Quiz findById(Integer quizId) throws QmBadRequestException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{quizId}, quizRowMapper);
        }catch (Exception e) {
            throw new QmResourceNotFoundException("Quiz not found");
        }
    }

    @Override
    public Integer create(String name, String description) throws QmBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                ps.setString(2, description);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("QUIZ_ID");
        }catch (Exception e) {
            throw new QmBadRequestException("Invalid request - create");
        }
    }

    @Override
    public void update(Integer quizId, Quiz quiz) throws QmBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{quiz.getName(), quiz.getDescription(), quizId});
        } catch (Exception e) {
            throw new QmBadRequestException("Invalid request");
        }
    }

    @Override
    public void removeById(Integer quizId) throws QmResourceNotFoundException {
        this.removeAllQuizQResponses(quizId);
        this.removeAllQuizQuestions(quizId);
        int count = jdbcTemplate.update(SQL_DELETE_QUIZ, new Object[]{quizId});
        if (count == 0) {
            throw new QmResourceNotFoundException("Quiz not found");
        }
    }

    private void removeAllQuizQResponses(Integer quizId) {
        jdbcTemplate.update(SQL_DELETE_ALL_QRESPONSES, new Object[]{quizId});
    }

    private void removeAllQuizQuestions(Integer quizId) {
        jdbcTemplate.update(SQL_DELETE_ALL_QUESTIONS, new Object[]{quizId});
    }

    private RowMapper<Quiz> quizRowMapper = ((rs, rowNum) -> {
        return new Quiz(rs.getInt("QUIZ_ID"),
                rs.getString("NAME"),
                rs.getString("DESCRIPTION"),
                rs.getInt("NUMBER_OF_QUESTIONS")
                );
    });
}
