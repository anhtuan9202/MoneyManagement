package com.example.moneymanagement.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moneymanagement.dao.ExpenseDao;
import com.example.moneymanagement.entity.Category;
import com.example.moneymanagement.entity.Expense;
import com.example.moneymanagement.repository.ExpenseRepository;

import java.text.ParseException;
import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {
    private final ExpenseRepository repository;
    private final LiveData<List<Expense>> allExpense;


    public ExpenseViewModel(@NonNull Application application) {
        super(application);
        repository = new ExpenseRepository(application);
        allExpense = repository.findAllExpense();


    }
    public LiveData<List<Expense>> findAllExpense(){
        return allExpense;
    }
    public List<Expense> findAllExpenseWithList() throws ParseException {
        return repository.findAllExpenseWithList();
    }
    public LiveData<List<Expense>> findAllExpenseInMonth(String month, String year ){
        return repository.findAllExpenseInMonth(month,year);
    }
    public Expense findExpenseWithId(int id) throws ParseException {
        return repository.findExpenseWithId(id);
    }
    public void insertExpense(Expense expense){
        repository.InsertExpense(expense);
    }
    public void updateExpense(Expense expense){
        repository.updateExpense(expense);
    }
    public void deleteExpense(Expense expense){
        repository.deleteExpense(expense);
    }

}