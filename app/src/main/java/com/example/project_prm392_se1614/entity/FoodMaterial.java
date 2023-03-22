package com.example.project_prm392_se1614.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "food_material",
        foreignKeys = {@ForeignKey(
                entity = Food.class,
                parentColumns = "id",
                childColumns = "food_id"),
                @ForeignKey(
                        entity = Material.class,
                        parentColumns = "id",
                        childColumns = "material_id")
        }
)
public class FoodMaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    @NotNull
    private int id;

    @ColumnInfo(name = "food_id")
    private int foodId;

    @ColumnInfo(name = "material_id")
    private int materialId;

    @ColumnInfo(name = "status")
    private boolean status;

    public FoodMaterial(long foodId, long materialId) {
    }

    public FoodMaterial(int foodId, int materialId, boolean status) {
        this.foodId = foodId;
        this.materialId = materialId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
