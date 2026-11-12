package com.learningmanagementsystem.learning_management_system.model;

import java.util.List;

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
@Table(name="Test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="test_name")
    private String testName;

    @Column(name="time")
    private String time;

    @Column(name="question_number")
    private int questionNumber;

    @Column(name="threshold")
    private int threshold;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="test_id", referencedColumnName = "id")
    private List<Question> questions;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="course_id", referencedColumnName = "id")
    private Course course;

    public Test(int id, String testName, String time,
        int questionNumber, int threshold) {
        super();

        this.id = id;
        this.testName = testName;
        this.time = time;
        this.questionNumber = questionNumber;
        this.threshold = threshold;
    }

    public Test() {
        super();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
