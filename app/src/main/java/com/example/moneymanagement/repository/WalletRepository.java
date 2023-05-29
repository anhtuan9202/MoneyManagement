package com.example.moneymanagement.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.moneymanagement.dao.AppDatabase;
import com.example.moneymanagement.dao.CategoryDao;
import com.example.moneymanagement.dao.ExpenseDao;
import com.example.moneymanagement.dao.MoneyTypeDao;
import com.example.moneymanagement.dao.WalletDao;
import com.example.moneymanagement.entity.Category;
import com.example.moneymanagement.entity.Expense;
import com.example.moneymanagement.entity.Wallet;

import java.util.List;

public class WalletRepository {

    private final WalletDao walletDao;
    private final LiveData<List<Wallet>> allWallet;
    private final List<Wallet> allWalletWithList;

    public WalletRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        this.walletDao = appDatabase.walletDao();
        allWallet = walletDao.findAll();
        allWalletWithList = walletDao.findAllWithList();

    }
    public LiveData<List<Wallet>> findAllWallet(){
        return allWallet;
    }
    public Wallet findWalletWithId(int id){
        return walletDao.findWalletWithId(id);
    }
    public List<Wallet> findAllWalletWithList(){
        return allWalletWithList;
    }
    public void insertWallet(Wallet wallet){
        new InsertWalletAsyntask(walletDao).execute(wallet);
    }
    public class InsertWalletAsyntask  extends AsyncTask<Wallet, Void, Void>{
        public WalletDao walletDao;

        public InsertWalletAsyntask(WalletDao walletDao) {
            this.walletDao = walletDao;
        }

        @Override
        protected Void doInBackground(Wallet... wallets) {
            walletDao.insert(wallets[0]);
            return null;
        }
    }
    public void updateWallet(Wallet wallet){
        new UpdateWalletAsyntask(walletDao).execute(wallet);
    }
    public class UpdateWalletAsyntask  extends AsyncTask<Wallet, Void, Void>{
        public WalletDao walletDao;

        public UpdateWalletAsyntask(WalletDao walletDao) {
            this.walletDao = walletDao;
        }

        @Override
        protected Void doInBackground(Wallet... wallets) {
            walletDao.update(wallets[0]);
            return null;
        }
    }
}
