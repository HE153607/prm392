package com.example.project_prm392_se1614.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "User_Food")
public class User_Food {
    @ColumnInfo(name="UserId")
    private int UserId;
    @ColumnInfo(name="Reaction")
    private String Reaction;
    @ColumnInfo(name="Comment")
    private String Comment;
    @ColumnInfo(name="FoodId")
    private int FoodId;
}
