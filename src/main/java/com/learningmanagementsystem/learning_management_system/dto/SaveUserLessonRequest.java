package com.learningmanagementsystem.learning_management_system.dto;

public class SaveUserLessonRequest {
    /**
     * UserController.java
     * /save/userlesson 
     */
    private int currentLessonId;
    private int currentLessonProgress;
    private int nextLessonId;
    private int courseId;

    public int getCurrentLessonId() {
        return currentLessonId;
    }

    public void setCurrentLessonId(int currentLessonId) {
        this.currentLessonId = currentLessonId;
    }

    public int getCurrentLessonProgress() {
        return currentLessonProgress;
    }

    public void setCurrentLessonProgress(int currentLessonProgress) {
        this.currentLessonProgress = currentLessonProgress;
    }

    public int getNextLessonId() {
        return nextLessonId;
    }

    public void setNextLessonId(int nextLessonId) {
        this.nextLessonId = nextLessonId;
    }

    public int getCourseId() {
        return courseId;
    }
    
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
