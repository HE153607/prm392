package com.example.project_prm392_se1614.converter;

import androidx.room.TypeConverter;

import com.example.project_prm392_se1614.entity.Role;
import com.example.project_prm392_se1614.entity.StatusFood;

public class StatusConverter {
    @TypeConverter
    public static StatusFood fromString(String value) {
        return value == null ? null : StatusFood.valueOf(value);
    }

    @TypeConverter
    public static String genderToString(StatusFood status) {
        return status == null ? null : status.name();
    }
}
