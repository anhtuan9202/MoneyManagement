package com.example.moneymanagement.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moneymanagement.entity.Expense;

import java.text.ParseException;
import java.util.List;

@Dao
public interface ExpenseDao {
    @Query("SELECT * FROM tb_expense WHERE strftime('%m', :month) AND strftime('%Y', :year)")
    LiveData<List<Expense>> findAllExpenseInMonth(String month, String year);
    @Query("Select * from tb_expense")
    LiveData<List<Expense>> findAll();
    @Query("Select * from tb_expense")
    List<Expense> findAllExpenseWithList() throws ParseException;
    @Query("Select * from tb_expense where exp_id = :id")
    Expense findExpenseWithId(int id) throws ParseException;
    @Insert
    void insert(Expense expense);
    @Delete
    void delete(Expense expense);
    @Update
    void update(Expense expense);

}
