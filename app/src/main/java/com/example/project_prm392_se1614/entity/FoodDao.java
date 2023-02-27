package com.example.project_prm392_se1614.entity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FoodDao {
    @Insert
    void insertFood(Food food);

    @Query("SELECT * FROM Food")
    List<Food> getAllFood();
}
