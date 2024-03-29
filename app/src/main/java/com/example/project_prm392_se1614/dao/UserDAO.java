package com.example.project_prm392_se1614.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project_prm392_se1614.entity.User;

@Dao
public interface UserDAO {

    @Query("select * from user where password = :pass and email = :email and active = 1 limit 1")
    User getUserByPassAndEmail(String pass, String email);

    @Insert
    void insertUser(User... user);

    @Query("select * from user where id = :id")
    User getUserById(int id);

    @Query("select * from user where email = :email limit 1")
    User getUserByEmail(String email);

    @Update
    void updateUser(User user);
}
