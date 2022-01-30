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

    public static final String SQL_FIND_BY_ID = "SELECT QZ.QUIZ_ID, QZ.TITLE, QZ.DESCRIPTION " +
            "FROM QM_QUESTIONS QS RIGHT OUTER JOIN QM_QUIZZES QZ ON QZ.QUIZ_ID = QS.QUIZ_ID " +
            "WHERE QZ.QUIZ_ID = ? GROUP BY QZ.QUIZ_ID";

    public static final String SQL_CREATE = "INSERT INTO QM_QUIZZES (QUIZ_ID, TITLE, DESCRIPTION) " +
            "VALUES (NEXTVAL('QM_QUIZZES_SEQ'), ?, ?)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Quiz> findAll() throws QmResourceNotFoundException {
        return null;
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
    public Integer create(String title, String description) throws QmBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, title);
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

    }

    @Override
    public void removeById(Integer quizId) throws QmResourceNotFoundException {

    }

    private RowMapper<Quiz> quizRowMapper = ((rs, rowNum) -> {
        return new Quiz(rs.getInt("QUIZ_ID"),
                rs.getString("TITLE"),
                rs.getString("DESCRIPTION")
                );
    });
}
