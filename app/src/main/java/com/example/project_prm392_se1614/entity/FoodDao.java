package com.example.project_prm392_se1614.entity;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface FoodDao {
    @Insert
    void insert(Food food);

}
