package com.learningmanagementsystem.learning_management_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="UserToLesson")
public class UserToLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="user_id")
    private int userId;

    @Column(name="course_id")
    private int courseId;

    @Column(name="lesson_id")
    private int lessonId;

    @Column(name="progress")
    private int progress;

    public UserToLesson(int id, int userId, int courseId, 
        int lessonId, int progress) {
        super();
        
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.lessonId = lessonId;
        this.progress = progress;
    }
    
    public UserToLesson(int userId, int courseId, 
        int lessonId, int progress) {
        super();

        this.userId = userId;
        this.courseId = courseId;
        this.lessonId = lessonId;
        this.progress = progress;
    }

    public UserToLesson() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
    
    
}
