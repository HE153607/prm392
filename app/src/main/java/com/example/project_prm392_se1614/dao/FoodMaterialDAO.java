package com.example.project_prm392_se1614.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project_prm392_se1614.entity.Food;
import com.example.project_prm392_se1614.entity.FoodMaterial;
import com.example.project_prm392_se1614.entity.Material;

import java.util.List;

@Dao
public interface FoodMaterialDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(FoodMaterial foodMaterialCross);

    @Query("SELECT * FROM food_material WHERE food_id = :foodId")
    List<FoodMaterial> findByFoodId(long foodId);

    @Query("SELECT * FROM food_material WHERE material_id = :materialId")
    List<FoodMaterial> findByMaterialId(long materialId);

    @Query("DELETE FROM food_material WHERE food_id = :foodId")
    void deleteByFoodId(int foodId);

    @Query("DELETE FROM food_material WHERE material_id = :materialId")
    void deleteByMaterialId(long materialId);
//    @Update
//    long updatMaterial(FoodMaterial foodMaterial);
}
