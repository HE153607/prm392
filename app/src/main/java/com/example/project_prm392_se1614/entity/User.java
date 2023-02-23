package com.example.project_prm392_se1614.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "User")
public class User {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name="UserId")
    private int UserId;
    @ColumnInfo(name="Password")
    private String Password;
    @ColumnInfo(name="UserName")
    private String UserName;
    @ColumnInfo(name="Email")
    private String Email;
    @ColumnInfo(name="IsActive")
    private Boolean IsActive;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Boolean getActive() {
        return IsActive;
    }

    public void setActive(Boolean active) {
        IsActive = active;
    }

    public User(int userId, String password, String userName, String email, Boolean isActive) {
        UserId = userId;
        Password = password;
        UserName = userName;
        Email = email;
        IsActive = isActive;
    }
}
