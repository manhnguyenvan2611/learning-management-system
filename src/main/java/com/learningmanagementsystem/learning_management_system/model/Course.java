package com.learningmanagementsystem.learning_management_system.model;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="course_name")
    private String course_name;

    @Column(name="description")
    private String description;

    @Column(name="thumbnail")
    private String thumbnail;

    @Column(name="level")
    private int level;

    @Column(name="max_score")
    private int maxScore;

    @Column(name="min_score")
    private int minScore;

    @Column(name="status")
    private String status;

    @Column(name="threshold")
    private int threshold;

    @Column(name="timestamp")
    private String timestamp;

    @Column(name="rate")
    private String rate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="course_id", referencedColumnName = "id")
    private List<Lesson> lessons;

    @OneToOne(mappedBy = "course")
    private Test test;

    public Course(int id, String courseName, String description,
        String thumbnail, int level, int max_score, int min_score, int threshold, String status,
        String rate) {
            super();

            this.id = id;
            this.course_name = courseName;
            this.description = description;
            this.thumbnail = thumbnail;
            this.level = level;
            this.minScore = min_score;
            this.maxScore = max_score;
            this.status = status;
            this.threshold = threshold;
            this.rate = rate;
        }

        public Course() {
            super();
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String courseName) {
            this.course_name = courseName;
        }

        public String getDescription() {
            return description;
        }

        public int getMaxScore() {
            return maxScore;
        }

        public void setMaxScore(int maxScore) {
            this.maxScore = maxScore;
        }

        public int getMinScore() {
            return minScore;
        }

        public void setMinScore(int minScore) {
            this.minScore = minScore;
        }

        public int getThreshold() {
            return threshold;
        }

        public void setThreshold(int threshold) {
            this.threshold = threshold;
        }

        public Test getTest() {
            return test;
        }

        public void setTest(Test test) {
            this.test = test;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }
}

