package com.makers.quizmanager.services;

import com.makers.quizmanager.domain.User;
import com.makers.quizmanager.exceptions.QmAuthException;
import com.makers.quizmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(String firstName, String lastName, Integer roleId, String email, String password) throws QmAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null) email = email.toLowerCase();
        if(!pattern.matcher(email).matches())
            throw new QmAuthException("Invalid email format");
        Integer count = userRepository.getCountByEmail(email);
        if (count > 0)
            throw new QmAuthException("Email already in use");
        Integer userId = userRepository.create(firstName, lastName, roleId, email, password);
        return userRepository.findById(userId);
    }

    @Override
    public User validateUser(String email, String password) throws QmAuthException {
        if (email != null) email = email.toLowerCase();
        return userRepository.findByEmailAndPassword(email, password);
    }
}
