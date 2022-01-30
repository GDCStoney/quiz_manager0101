package com.makers.quizmanager.services;

import com.makers.quizmanager.domain.User;
import com.makers.quizmanager.exceptions.QmAuthException;

public interface UserService {

    User registerUser(String firstName, String lastName, Integer roleId, String email, String password) throws QmAuthException;

    User validateUser(String email, String password) throws QmAuthException;
}
