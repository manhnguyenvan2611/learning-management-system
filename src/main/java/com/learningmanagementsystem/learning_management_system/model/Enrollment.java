package com.learningmanagementsystem.learning_management_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="user_id")
    private int userId;

    @Column(name="course_id")
    private int courseId;

    @Column(name="status")
    private String status;

    public Enrollment(int id, int userId, int courseId, String status) {
        super();

        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.status = status;
    }

    public Enrollment(int userId, int courseId, String status) {
        super();

        this.userId = userId;
        this.courseId = courseId;
        this.status = status;
    }

    public Enrollment() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
