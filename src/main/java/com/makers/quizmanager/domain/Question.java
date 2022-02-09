package com.makers.quizmanager.domain;

public class Question {

    private Integer questionId;
    private Integer quizId;
    private String questionText;

    public Question(Integer questionId, Integer quizId, String questionText) {
        this.questionId = questionId;
        this.quizId = quizId;
        this.questionText = questionText;
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

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
