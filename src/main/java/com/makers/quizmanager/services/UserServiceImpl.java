package com.makers.quizmanager.services;

import com.makers.quizmanager.domain.User;
import com.makers.quizmanager.exceptions.QmAuthException;
import com.makers.quizmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(String firstName, String lastName, Integer roleId, String email, String password) throws QmAuthException {
        return null;
    }
}
