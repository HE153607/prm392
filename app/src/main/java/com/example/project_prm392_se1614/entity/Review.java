package com.example.project_prm392_se1614.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "review",
        foreignKeys = {@ForeignKey(
                entity = User.class,
                parentColumns = "id",
                childColumns = "user_id"),
                @ForeignKey(
                        entity = Food.class,
                        parentColumns = "id",
                        childColumns = "food_id")})
public class Review {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    @NotNull
    private int id;

    @ColumnInfo(name = "user_id")
    private int userId;

    @ColumnInfo(name = "food_id")
    private int foodId;

    @ColumnInfo(name = "comment")
    private String comment;

    @ColumnInfo(name = "rate")
    private int rate;

    @ColumnInfo(name = "status")
    private boolean status;

    public Review(int userId, int foodId, String comment, int rate, boolean status) {
        this.userId = userId;
        this.foodId = foodId;
        this.comment = comment;
        this.rate = rate;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
