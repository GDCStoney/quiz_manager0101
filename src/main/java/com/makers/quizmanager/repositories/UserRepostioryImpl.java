package com.makers.quizmanager.repositories;

import com.makers.quizmanager.domain.User;
import com.makers.quizmanager.exceptions.QmAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepostioryImpl implements UserRepository {
    
    private static final String SQL_CREATE = "INSERT INTO QM_USERS(USER_ID, ROLE_ID , FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) " +
            "VALUES(NEXTVAL('QM_USERS_SEQ'), ?, ?, ?, ?, ?);";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String firstName, String lastName, Integer roleId, String email, String password) throws QmAuthException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.PreparedStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, roleId);
                ps.setString(2, firstName);
                ps.setString(3, lastName);
                ps.setString(4, email);
                ps.setString(5, password);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("USER_ID");
        }catch (Exception e) {
            throw new QmAuthException("Invalid details. Failed to create account.")
        };
    }

    @Override
    public Integer getCountByEmail(String email) {
        return null;
    }

    @Override
    public User findById(Integer userId) {
        return null;
    }
}
