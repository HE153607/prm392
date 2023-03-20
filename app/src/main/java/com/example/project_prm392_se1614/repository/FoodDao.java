package com.example.project_prm392_se1614.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project_prm392_se1614.entity.Food;

import java.util.List;

@Dao
public interface FoodDao {
    @Insert
    void insertFood(Food food);

    @Query("SELECT * FROM Food")
    List<Food> getAllFood();

    @Query("SELECT * FROM Food WHERE food_name LIKE '%' || :name || '%'")
    List<Food> searchfood(String name);

    @Delete
    void deleteFood(Food food);


    @Insert
    void insert(Food food);
    @Query("SELECT * FROM Food ")
    List<Food> getFoods();
    @Update
    void updateFood(Food food);

    @Query("SELECT * FROM Food WHERE id =:userId")
    Food getFoodById(int userId);
    @Query("SELECT * FROM Food WHERE user_id =:uId ")
    List<Food> getListFoodsById(int uId);
}
