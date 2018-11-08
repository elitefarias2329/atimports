package com.atimports.converter;

import android.arch.persistence.room.TypeConverter;

import java.math.BigDecimal;

public class BigDecimalTypeConverter {

    @TypeConverter
    public static BigDecimal fromString(String value) {
        return value == null ? null : new BigDecimal(value);
    }

    @TypeConverter
    public static Double toDouble(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return null;
        } else {
            return bigDecimal.doubleValue();
        }
    }

}
