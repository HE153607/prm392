package com.example.project_prm392_se1614.entity;

import androidx.room.Dao;

import java.util.List;

@Dao
public interface UserDao {

        //@Query("SELECT * FROM accounts WHERE id = :id")
        public User getUserById(int id);

        //@Query("SELECT * FROM items WHERE accountId = :accountId")
        public List<Food> getItemsForAccount(int accountId);

        //@Insert
        public long insert(User user);

        //@Update
        public void update(User user);

        //@Delete
        public void delete(User user);

}
