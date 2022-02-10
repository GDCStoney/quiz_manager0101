package com.makers.quizmanager.domain;

public class QResponse {

    private Integer responseId;
    private Integer questionId;
    private Integer quizId;
    private String responseText;
    private Boolean correctAnswer;

    public QResponse(Integer responseId, Integer questionId, Integer quizId, String responseText, Boolean correctAnswer) {
        this.responseId = responseId;
        this.questionId = questionId;
        this.quizId = quizId;
        this.responseText = responseText;
        this.correctAnswer = correctAnswer;
    }

    public Integer getResponseId() {
        return responseId;
    }

    public void setResponseId(Integer responseId) {
        this.responseId = responseId;
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

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public Boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
