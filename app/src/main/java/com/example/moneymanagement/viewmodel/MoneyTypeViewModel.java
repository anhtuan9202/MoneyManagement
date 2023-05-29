package com.example.moneymanagement.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moneymanagement.entity.Category;
import com.example.moneymanagement.entity.Expense;
import com.example.moneymanagement.entity.MoneyType;
import com.example.moneymanagement.repository.ExpenseRepository;
import com.example.moneymanagement.repository.MoneyTypeRepository;

import java.util.List;

public class MoneyTypeViewModel extends AndroidViewModel {
    private final MoneyTypeRepository repository;


    public MoneyTypeViewModel(@NonNull Application application) {
        super(application);
        repository = new MoneyTypeRepository(application);



    }
}