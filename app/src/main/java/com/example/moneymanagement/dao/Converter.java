package com.example.moneymanagement.dao;

import android.text.format.DateFormat;
import android.util.Log;

import androidx.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Converter {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    @TypeConverter
    public static Date toDate(String value) throws ParseException {
        try {
            if(value == null){
                return null;
            }
            else {
                return DATE_FORMAT.parse(value);
            }
        }
        catch (ParseException e){
            Log.e("ERROR", "toDate: error");
            throw  e;
        }
    }

    @TypeConverter
    public static String ToString(Date date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMAT.format(date);
    }
}
