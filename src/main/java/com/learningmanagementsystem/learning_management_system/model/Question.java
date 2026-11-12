package com.learningmanagementsystem.learning_management_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="question_content")
    private String questionContent;

    @Column(name="answer_a")
    private String answerA;

    @Column(name="answer_b")
    private String answerB;

    @Column(name="answer_c")
    private String answerC;

    @Column(name="answer_d")
    private String answerD;

    @Column(name="test_id")
    private int testId;

    @OneToOne(mappedBy = "question")
    private Answer answer;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public Question(int id, String questionContent,
        String answerA, String answerB, String answerC, 
        String answerD, int testId) {
            super();

            this.id = id;
            this.questionContent = questionContent;
            this.answerA = answerA;
            this.answerB = answerB;
            this.answerC = answerC;
            this.answerD = answerD;
            this.testId = testId;
        }

        public Question() {
            super();
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQuestionContent() {
            return questionContent;
        }

        public void setQuestionContent(String questionContent) {
            this.questionContent = questionContent;
        }

        public String getAnswerA() {
            return answerA;
        }

        public void setAnswerA(String answerA) {
            this.answerA = answerA;
        }

        public String getAnswerB() {
            return answerB;
        }

        public void setAnswerB(String answerB) {
            this.answerB = answerB;
        }

        public String getAnswerC() {
            return answerC;
        }

        public void setAnswerC(String answerC) {
            this.answerC = answerC;
        }

        public String getAnswerD() {
            return answerD;
        }

        public void setAnswerD(String answerD) {
            this.answerD = answerD;
        }

        
}
