package com.example.moneymanagement.viewmodel;

import android.app.Application;
import android.support.v4.os.IResultReceiver;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moneymanagement.entity.Category;
import com.example.moneymanagement.entity.Expense;
import com.example.moneymanagement.repository.CategoryRepository;
import com.example.moneymanagement.repository.ExpenseRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
    private final CategoryRepository repository;
    private final LiveData<List<Category>> allCategory;
    private final List<Category> allCategoryWithList;
    private final LiveData<List<Category>> allExpense;
    private final LiveData<List<Category>> allRevenue;
    public CategoryViewModel(@NonNull Application application) {
        super(application);
        repository = new CategoryRepository(application);
        allCategory = repository.findAllCategory();
        allCategoryWithList = repository.findAllCategoryWithList();
        allExpense = repository.findAllExpense();
        allRevenue = repository.findAllRevenue();


    }
    public Category findCategoryById(int id){
        return repository.findCategoryById(id);
    }
    public LiveData<List<Category>> findAllCategory(){
        return allCategory;
    }
    public List<Category> findAllCategoryWithList(){
        return allCategoryWithList;
    }
    public LiveData<List<Category>> findAllRevenue(){
        return allRevenue;
    }
    public LiveData<List<Category>> findAllExpense(){
        return allExpense;
    }

}