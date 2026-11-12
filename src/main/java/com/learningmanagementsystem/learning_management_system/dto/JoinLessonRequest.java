package com.learningmanagementsystem.learning_management_system.dto;

public class JoinLessonRequest {
    /**
     * UserController.java
     * /joinlesson
     */
    private int courseId;
    private int lessonId;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }    
}
