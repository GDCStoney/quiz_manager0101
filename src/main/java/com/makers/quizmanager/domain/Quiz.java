package com.makers.quizmanager.domain;

public class Quiz {

    private Integer quizId;
    private String name;
    private String description;
    private Integer numberOfQuestions;

    public Quiz(Integer quizId, String name, String description, Integer numberOfQuestions) {
        this.quizId = quizId;
        this.name = name;
        this.description = description;
        this.numberOfQuestions = numberOfQuestions;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(Integer numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }
}
