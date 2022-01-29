package com.makers.quizmanager.repositories;

import com.makers.quizmanager.domain.User;
import com.makers.quizmanager.exceptions.QmAuthException;

public class UserRepostioryImpl implements UserRepository {
    @Override
    public Integer create(String firstName, String lastName, Integer roleId, String email, String password) throws QmAuthException {
        return null;
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
