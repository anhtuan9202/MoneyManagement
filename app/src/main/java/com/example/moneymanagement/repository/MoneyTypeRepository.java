package com.example.moneymanagement.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.moneymanagement.dao.AppDatabase;
import com.example.moneymanagement.dao.CategoryDao;
import com.example.moneymanagement.dao.ExpenseDao;
import com.example.moneymanagement.dao.MoneyTypeDao;
import com.example.moneymanagement.dao.WalletDao;
import com.example.moneymanagement.entity.Category;
import com.example.moneymanagement.entity.Expense;

import java.util.List;

public class MoneyTypeRepository {

    private MoneyTypeDao moneyTypeDao;
    public MoneyTypeRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);

    }
}
