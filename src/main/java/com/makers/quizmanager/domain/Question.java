package com.makers.quizmanager.domain;

public class Question {

    private Integer questionId;
    private Integer quizId;
    private String question;

    public Question(Integer questionId, Integer quizId, String question) {
        this.questionId = questionId;
        this.quizId = quizId;
        this.question = question;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public String getDescription() {
        return question;
    }

    public void setDescription(String description) {
        this.question = description;
    }
}
