package com.makers.quizmanager.services;

import com.makers.quizmanager.domain.Quiz;
import com.makers.quizmanager.exceptions.QmBadRequestException;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.List;

@Service
public class ConvertServiceImpl implements ConvertService {

    @Override
    public String convertQuizListToJSON(List<Quiz> quiz) {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return objectWriter.writeValueAsString(quiz);
        }catch (Exception e) {
            throw new QmBadRequestException("JSON object not right");
        }
    }
}
