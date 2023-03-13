package com.example.project_prm392_se1614.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FoodDao {
    @Insert
    void insert(Food food);
    @Query("SELECT * FROM Food")
    List<Food> getFoods();
    @Update
    void updateFood(Food food);
    @Delete
    void deleteFood(Food food);

//    @Query("SELECT * FROM Food WHERE food_name LIKE '%'")
//     List<Food> searchFood(String food_name);
}
