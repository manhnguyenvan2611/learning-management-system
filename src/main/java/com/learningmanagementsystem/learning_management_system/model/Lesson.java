package com.learningmanagementsystem.learning_management_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="lesson_name")
    private String lessonName;

    @Column(name="file")
    private String file;

    @Column(name="course_id")
    private int courseId;

    @Column(name="progress")
    private int progress;

    public Lesson(int id, String lessonName, 
        String file, int courseId, int progress) {
        super();
        this.id = id;
        this.lessonName = lessonName;
        this.file = file;
        this.courseId = courseId;
        this.progress = progress;
    }

    public Lesson() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
