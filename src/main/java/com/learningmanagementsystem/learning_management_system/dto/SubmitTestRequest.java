package com.learningmanagementsystem.learning_management_system.dto;

public class SubmitTestRequest {
    /**
     * TestController.java
     * /submittest
     */
    private int userScore;
    private int courseId;

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    } 
}
