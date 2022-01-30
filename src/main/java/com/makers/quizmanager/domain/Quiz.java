package com.makers.quizmanager.domain;

public class Quiz {

    private Integer quizId;
    private String Name;
    private String description;
    private Integer numberOfQuestions;

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public Quiz(Integer quizId, String name, String description) {
        this.quizId = quizId;
        Name = name;
        this.description = description;
    }
}
