package com.example.project_prm392_se1614.converter;

import android.util.Base64;

import androidx.room.TypeConverter;

public class ByteConverter {

    @TypeConverter
    public static byte[] fromString(String value) {
        return Base64.decode(value, Base64.DEFAULT);
    }

    @TypeConverter
    public static String toString(byte[] value) {
        return Base64.encode(value, Base64.DEFAULT).toString();
    }
}
