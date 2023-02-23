package com.example.project_prm392_se1614.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "user_food",
        foreignKeys = {@ForeignKey(
                entity = User.class,
                parentColumns = "id",
                childColumns = "user_id"),
                @ForeignKey(
                        entity = Food.class,
                        parentColumns = "id",
                        childColumns = "food_id")
                }
        )
public class Evaluate {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name="user_id")
    private int userId;

    @ColumnInfo(name="reaction")
    private String reaction;

    @ColumnInfo(name="comment")
    private String comment;

    @ColumnInfo(name="food_id")
    private int foodId;

    @ColumnInfo(name="active")
    private Boolean active;

    public Evaluate(int id, int userId, String reaction, String comment, int foodId, Boolean active) {
        this.id = id;
        this.userId = userId;
        this.reaction = reaction;
        this.comment = comment;
        this.foodId = foodId;
        this.active = active;
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

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
