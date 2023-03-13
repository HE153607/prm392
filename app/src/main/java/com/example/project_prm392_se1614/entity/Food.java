package com.example.project_prm392_se1614.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import kotlin.jvm.Transient;

@Entity(tableName = "Food",
        foreignKeys = @ForeignKey(
                entity = User.class,
                parentColumns = "id",
                childColumns = "user_id"))
public class Food implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name="food_name")
    private String foodName;

    @ColumnInfo(name="ration")
    private String ration;

    @ColumnInfo(name="time")
    private String time;

    @ColumnInfo(name="ingredient")
    private String ingredient;

    @ColumnInfo(name="step")
    private String step;

    @ColumnInfo(name="image")
    private String image;

    @ColumnInfo(name="user_id")
    private int userId;

    @ColumnInfo(name="active")
    private Boolean active;

    @Ignore
    private User user;

    public Food() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Food(String foodName, String ration, String time, String ingredient, String step, String image, int userId, Boolean active) {

        this.foodName = foodName;
        this.ration = ration;
        this.time = time;
        this.ingredient = ingredient;
        this.step = step;
        this.image = image;
        this.userId = userId;
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getRation() {
        return ration;
    }

    public void setRation(String ration) {
        this.ration = ration;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getFoodImage() {
        return null;
    }
}
