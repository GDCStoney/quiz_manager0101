package com.makers.quizmanager.services;

public interface UserService {

    User registerUser(String firstName, String lastName, Integer roleId, String email, String password) throws EtAuthException;

}
