package com.example.moneymanagement.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moneymanagement.dao.WalletDao;
import com.example.moneymanagement.entity.Category;
import com.example.moneymanagement.entity.Expense;
import com.example.moneymanagement.entity.Wallet;
import com.example.moneymanagement.repository.ExpenseRepository;
import com.example.moneymanagement.repository.WalletRepository;

import java.util.List;

public class WalletViewModel extends AndroidViewModel {
    private final WalletRepository repository;
    private final LiveData<List<Wallet>> allWallet;
    private final List<Wallet> allWalletWithList;



    public WalletViewModel(@NonNull Application application) {
        super(application);
        repository = new WalletRepository(application);
        allWallet = repository.findAllWallet();
        allWalletWithList = repository.findAllWalletWithList();


    }
    public LiveData<List<Wallet>> findAllWallet(){
        return allWallet;
    }
    public List<Wallet> findAllWalletWithList(){
        return allWalletWithList;
    }
    public void insertWallet(Wallet wallet){
        repository.insertWallet(wallet);
    }
    public void updateWallet(Wallet wallet){
        repository.updateWallet(wallet);
    }
    public Wallet findWalletWithId(int id){
        return repository.findWalletWithId(id);
    }

}