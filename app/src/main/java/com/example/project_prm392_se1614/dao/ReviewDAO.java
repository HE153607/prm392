package com.example.project_prm392_se1614.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.project_prm392_se1614.entity.Review;

import java.util.List;

@Dao
public interface ReviewDAO {
    @Query("SELECT * FROM review WHERE food_id = :foodId")
    List<Review> getReviewsByFood(String foodId);

    @Query("SELECT * FROM review WHERE user_id = :userId")
    List<Review> getReviewsByUser(String userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Review review);

    @Delete
    void delete(Review review);

    @Query("DELETE FROM review")
    void deleteAll();
}
