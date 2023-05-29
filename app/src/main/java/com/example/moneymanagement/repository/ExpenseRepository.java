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

import java.text.ParseException;
import java.util.List;

public class ExpenseRepository {
    private final ExpenseDao expenseDao;
    //Money type
    private MoneyTypeDao moneyTypeDao;
    //Wallet
    private WalletDao walletDao;
    private final LiveData<List<Expense>> allExpense;
    private LiveData<List<Category>> allCategory;

    public ExpenseRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        this.expenseDao =  appDatabase.expenseDao();
        allExpense = expenseDao.findAll();

    }
    public LiveData<List<Expense>> findAllExpense(){
        return allExpense;
    }
    public List<Expense> findAllExpenseWithList() throws ParseException{
        return expenseDao.findAllExpenseWithList();
    }
    public LiveData<List<Expense>> findAllExpenseInMonth(String month, String year ){
        return expenseDao.findAllExpenseInMonth(month,year);
    }

    public Expense findExpenseWithId(int id) throws ParseException {
        return expenseDao.findExpenseWithId(id);
    }


    public void InsertExpense(Expense expense){
         new InsertExpenseAsyncTask(expenseDao).execute(expense);
    }
    private static class InsertExpenseAsyncTask extends AsyncTask<Expense, Void, Void>{
        private final ExpenseDao expenseDao;

        public InsertExpenseAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(Expense... expenses) {
            expenseDao.insert(expenses[0]);
            return null;
        }
    }
    public void updateExpense(Expense expense){
        new UpdateExpenseAsyncTask(expenseDao).execute(expense);
    }
    private static class UpdateExpenseAsyncTask extends AsyncTask<Expense, Void, Void>{
        private final ExpenseDao expenseDao;

        public UpdateExpenseAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(Expense... expenses) {
            expenseDao.update(expenses[0]);
            return null;
        }
    }
    public void deleteExpense(Expense expense){
        new DeleteExpenseAsyncTask(expenseDao).execute(expense);
    }
    private static class DeleteExpenseAsyncTask extends AsyncTask<Expense, Void, Void>{
        private final ExpenseDao expenseDao;

        public DeleteExpenseAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(Expense... expenses) {
            expenseDao.delete(expenses[0]);
            return null;
        }
    }
}
