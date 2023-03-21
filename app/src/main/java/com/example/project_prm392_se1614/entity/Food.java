package com.example.project_prm392_se1614.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import kotlin.jvm.Transient;

@Entity(tableName = "food",
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


    @ColumnInfo(name="ingredient")
    private String ingredient;


    @ColumnInfo(name="image")
    private byte[] image;
    @ColumnInfo(name="step")
    private String Step;
    @ColumnInfo(name="user_id")
    private int userId;

    @ColumnInfo(name="status")
    private int status;

    @Ignore
    private User user;

    public Food(String foodName, String ingredient, byte[] image, int userId, int status, User user,String Step) {
        this.foodName = foodName;
        this.ingredient = ingredient;
        this.image = image;
        this.userId = userId;
        this.status = status;
        this.user = user;
        this.Step= Step;

    }

    public Food() {
    }

    public Food(int id, String foodName, String ingredient, byte[] image, String step, int userId, int status) {
        this.id = id;
        this.foodName = foodName;
        this.ingredient = ingredient;
        this.image = image;
        Step = step;
        this.userId = userId;
        this.status = status;
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

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
    public String getStep() {
        return Step;
    }

    public void setStep(String step) {
        this.Step = step;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
