package com.makers.quizmanager.repositories;

import com.makers.quizmanager.domain.User;
import com.makers.quizmanager.exceptions.QmAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepostioryImpl implements UserRepository {
    
    private static final String SQL_CREATE = "INSERT INTO QM_USERS(USER_ID, ROLE_ID , FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) " +
            "VALUES(NEXTVAL('QM_USERS_SEQ'), ?, ?, ?, ?, ?)";

    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM QM_USERS WHERE EMAIL = ?";

    private static final String SQL_FIND_BY_ID = "SELECT USER_ID, ROLE_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD FROM QM_USERS " +
            "WHERE USER_ID = ?";

    private static final String SQL_FIND_BY_EMAIL = "SELECT USER_ID, ROLE_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD FROM QM_USERS " +
            "WHERE EMAIL = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String firstName, String lastName, Integer roleId, String email, String password) throws QmAuthException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, roleId);
                ps.setString(2, firstName);
                ps.setString(3, lastName);
                ps.setString(4, email);
                ps.setString(5, password);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("USER_ID");
        }catch (Exception e) {
            throw new QmAuthException("Invalid details. Failed to create account.");
        }
    }

    @Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[]{email}, Integer.class);
    }

    @Override
    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId}, userRowMapper);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws QmAuthException {
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email}, userRowMapper);
            if (!password.equals(user.getPassword()))
                throw new QmAuthException("Invalid Email or Password");
        }catch (EmptyResultDataAccessException e) {
            throw new QmAuthException("Invalid Email or Password");
        }
        return jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email}, userRowMapper);
    }


    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User(
                rs.getInt("USER_ID"),
                rs.getInt("ROLE_ID"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD")
        );
    });
}
