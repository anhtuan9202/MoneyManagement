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

public class CategoryRepository {

    private final CategoryDao categoryDao;
    private final LiveData<List<Category>> allCategory;
    private final List<Category> allCategoryWithList;

    private final LiveData<List<Category>> allExpense;
    private final LiveData<List<Category>> allRevenue;

    public CategoryRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        this.categoryDao = appDatabase.categoryDao();
        allCategory = categoryDao.findAllCategory();
        allCategoryWithList = categoryDao.findAllCategoryWithList();
        allExpense = categoryDao.findAllExpense();
        allRevenue = categoryDao.findAllRevenue();


    }


    public Category findCategoryById( int id){
        return categoryDao.findCategoryById(id);
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
