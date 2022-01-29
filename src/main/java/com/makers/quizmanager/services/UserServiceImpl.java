package com.makers.quizmanager.services;

import com.makers.quizmanager.domain.User;
import com.makers.quizmanager.exceptions.QmAuthException;

public class UserServiceImpl implements UserService {

    @Override
    public User registerUser(String firstName, String lastName, Integer roleId, String email, String password) throws QmAuthException {
        return null;
    }
}
