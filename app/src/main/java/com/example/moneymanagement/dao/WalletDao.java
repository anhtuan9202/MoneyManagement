package com.example.moneymanagement.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moneymanagement.entity.Expense;
import com.example.moneymanagement.entity.Wallet;

import java.util.List;

@Dao
public interface WalletDao {
    @Query("Select * from tb_wallet")
    LiveData<List<Wallet>> findAll();
    @Query("Select * from tb_wallet")
    List<Wallet> findAllWithList();

    @Query("Select * from tb_wallet where wal_id = :id")
    Wallet findWalletWithId(int id);
    @Insert
    void insert(Wallet wallet);
    @Delete
    void delete(Wallet wallet);
    @Update
    void update(Wallet wallet);
}
