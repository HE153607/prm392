package com.example.project_prm392_se1614.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Food")
public class Food {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name="FoodId")
    private int FoodId;
    @ColumnInfo(name="FoodId")
    private String FoodName;
    @ColumnInfo(name="FoodId")
    private String Ration;
    @ColumnInfo(name="FoodId")
    private String Time;
    @ColumnInfo(name="FoodId")
    private String Ingredient;
    @ColumnInfo(name="FoodId")
    private String Steps;
    @ColumnInfo(name="FoodId")
    private String Image;
    @ColumnInfo(name="FoodId")

    private User UserId;

    public Food(int foodId, String foodName, String ration, String time, String ingredient, String steps, String image, User userId) {
        FoodId = foodId;
        FoodName = foodName;
        Ration = ration;
        Time = time;
        Ingredient = ingredient;
        Steps = steps;
        Image = image;
        UserId = userId;
    }

    public int getFoodId() {
        return FoodId;
    }

    public void setFoodId(int foodId) {
        FoodId = foodId;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getRation() {
        return Ration;
    }

    public void setRation(String ration) {
        Ration = ration;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getIngredient() {
        return Ingredient;
    }

    public void setIngredient(String ingredient) {
        Ingredient = ingredient;
    }

    public String getSteps() {
        return Steps;
    }

    public void setSteps(String steps) {
        Steps = steps;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public User getUserId() {
        return UserId;
    }

    public void setUserId(User userId) {
        UserId = userId;
    }
}
