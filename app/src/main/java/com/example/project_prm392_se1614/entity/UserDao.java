package com.example.project_prm392_se1614.entity;

import androidx.room.Dao;

import java.util.List;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user  WHERE id = :id")
    public User getUserById(int id);

//    @Query("SELECT * FROM items WHERE accountId = :accountId")
//    public List<Food> getItemsForAccount(int accountId);

}