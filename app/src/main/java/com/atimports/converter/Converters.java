package com.atimports.converter;

import android.arch.persistence.room.TypeConverter;

import java.math.BigDecimal;

public class Converters {

    @TypeConverter
    public BigDecimal fromString(String value) {
        return value == null ? null : new BigDecimal(value);
    }

    @TypeConverter
    public Double amountToString(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return null;
        } else {
            return bigDecimal.doubleValue();
        }
    }
}
