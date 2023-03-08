package com.example.project_prm392_se1614.converter;

import androidx.room.TypeConverter;

import com.example.project_prm392_se1614.entity.Role;

public class RoleConverter {
    @TypeConverter
    public static Role fromString(String value) {
        return value == null ? null : Role.valueOf(value);
    }

    @TypeConverter
    public static String genderToString(Role role) {
        return role == null ? null : role.name();
    }
}
