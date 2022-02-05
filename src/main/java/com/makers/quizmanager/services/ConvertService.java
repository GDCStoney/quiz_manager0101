package com.makers.quizmanager.services;

import com.makers.quizmanager.domain.Quiz;

import java.util.List;

public interface ConvertService {

    String convertQuizListToJSON(List<Quiz> quiz);
}
