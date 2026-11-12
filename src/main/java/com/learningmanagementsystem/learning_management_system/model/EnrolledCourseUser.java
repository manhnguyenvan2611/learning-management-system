package com.learningmanagementsystem.learning_management_system.model;

public class EnrolledCourseUser {
    private String username;
    private String avatar;
    private String email;
    private String courseStatus;

    public EnrolledCourseUser(String username, String avatar, String email, String courseStatus) {
        this.username = username;
        this.avatar = avatar;
        this.email = email;
        this.courseStatus = courseStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }
}
