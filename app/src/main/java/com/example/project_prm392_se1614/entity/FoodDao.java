package com.example.project_prm392_se1614.entity;

import androidx.room.Dao;
import java.util.List;
import androidx.room.Query;
@Dao
public interface FoodDao {
    @Query("SELECT * FROM Food WHERE id = :id")
    public Food getFoodById(int id);
}
