package com.learningmanagementsystem.learning_management_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name="UserToRole")
public class UserToRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="user_id")
    private int userId;

    @Column(name="role_id")
    private int roleId;

    public UserToRole() {
        super();
    }

    public UserToRole(int userId, int roleId) {
        super();
        
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserToRole(int id, int userId, int roleId) {
        super();
        
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
