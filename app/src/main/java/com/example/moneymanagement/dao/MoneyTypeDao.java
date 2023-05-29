package com.example.moneymanagement.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moneymanagement.entity.MoneyType;

@Dao
public interface MoneyTypeDao {
    @Query("Select * from tb_moneyType where moneyType_id = 1")
    LiveData<MoneyType> getTypeRevenue();
    @Query("Select * from tb_moneyType where moneyType_id = 2")
    LiveData<MoneyType> getTypeExpense();
    @Insert
    void insert(MoneyType moneyType);
    @Delete
    void delete(MoneyType moneyType);
    @Update
    void update(MoneyType moneyType);
}
