package com.example.moneymanagement.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moneymanagement.entity.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Query("Select * from tb_category")
    LiveData<List<Category>> findAllCategory();
    @Query("Select * from tb_category")
    List<Category> findAllCategoryWithList();
    @Query("Select * from tb_category where cate_id = :id")
    Category findCategoryById(int id);
    @Query("Select * from tb_category where cate_type like 2")
    LiveData<List<Category>> findAllExpense();
    @Query("Select * from tb_category where cate_type like 1")
    LiveData<List<Category>> findAllRevenue();
    @Insert
    void insert(Category category);

    @Delete
    void delete(Category category);

    @Update
    void update(Category category);
}
