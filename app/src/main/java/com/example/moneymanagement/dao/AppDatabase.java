package com.example.moneymanagement.dao;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.moneymanagement.entity.Category;
import com.example.moneymanagement.entity.Expense;
import com.example.moneymanagement.entity.MoneyType;
import com.example.moneymanagement.entity.Wallet;

@Database(entities = {Category.class, Expense.class, MoneyType.class, Wallet.class},version = 2)
@TypeConverters(Converter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CategoryDao categoryDao();
    public abstract ExpenseDao expenseDao();
    public abstract MoneyTypeDao moneyTypeDao();
    public abstract WalletDao walletDao();

    public static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized(AppDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "MoneyManagement")
                        .createFromAsset("database/MoneyManagement.db")
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return INSTANCE;
    }

}
