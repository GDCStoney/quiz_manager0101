package com.makers.quizmanager.repositories;

import com.makers.quizmanager.domain.User;
import com.makers.quizmanager.exceptions.QmAuthException;

public interface UserRepository {
    
    Integer create(String firstName, String lastName, Integer roleId, String email, String password) throws QmAuthException;

    Integer getCountByEmail(String email);
    
    User findById(Integer userId);
}
