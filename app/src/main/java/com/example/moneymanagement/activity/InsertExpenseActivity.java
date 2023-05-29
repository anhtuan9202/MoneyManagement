package com.example.moneymanagement.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanagement.R;
import com.example.moneymanagement.entity.Category;
import com.example.moneymanagement.entity.Expense;
import com.example.moneymanagement.entity.Wallet;
import com.example.moneymanagement.fragment.ChooseCategoryFragment;
import com.example.moneymanagement.fragment.ChooseWalletFragment;
import com.example.moneymanagement.viewmodel.CategoryViewModel;
import com.example.moneymanagement.viewmodel.ExpenseViewModel;
import com.example.moneymanagement.viewmodel.WalletViewModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InsertExpenseActivity extends AppCompatActivity implements View.OnClickListener, ChooseCategoryFragment.ChooseCategoryFragmentListener, ChooseWalletFragment.ChooseWalletFragmentListener {
    private CategoryViewModel categoryViewModel;
    private WalletViewModel walletViewModel;
    private ExpenseViewModel expenseViewModel;
    private MaterialToolbar tbInsertExpense;
    private TextView tvExpenseTitle,tvAmount, tvMoneyUnit, tvWalletName, tvWalletMoney,tvWalletMoneyExist,tvCategoryName, tvCategoryType, tvExpenseDate;
    private EditText etExpenseNote;
    private Button btnSave;
    private ImageView ivWallet, ivCategory;
    private RelativeLayout rlWallet, rlCategory;
    // Database
    private Category category;
    private Wallet wallet;
    private String expenseNote;
    private String expenseMoney;
    private Date expenseDate;
    private int expenseType;
    private int expenseCategory;
    private int expenseWallet;
    private boolean isEdit = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_expense);
        //View model
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        walletViewModel = new ViewModelProvider(this).get(WalletViewModel.class);
        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);
        //Toolbar
        tbInsertExpense = findViewById(R.id.tbInsertExpense);
        tvExpenseTitle = findViewById(R.id.tvExpenseTitle);
        tbInsertExpense.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Money
        tvAmount = findViewById(R.id.tvAmount);
        tvMoneyUnit = findViewById(R.id.tvMoneyUnit);

        //Wallet
        rlWallet = findViewById(R.id.rlWallet);
        ivWallet = findViewById(R.id.ivWallet);
        tvWalletName = findViewById(R.id.tvWalletName);
        tvWalletMoney = findViewById(R.id.tvWalletMoney);
        tvWalletMoneyExist = findViewById(R.id.tvMoneyExist);
        rlWallet.setOnClickListener(this);

        //Category
        rlCategory = findViewById(R.id.rlCategory);
        ivCategory = findViewById(R.id.ivCategoryImage);
        tvCategoryName = findViewById(R.id.tvCategoryName);
        tvCategoryType = findViewById(R.id.tvCategoryType);
        rlCategory.setOnClickListener(this);

        //Note
        etExpenseNote = findViewById(R.id.etExpenseNote);
        //Date
        tvExpenseDate = findViewById(R.id.etExpenseDate);
        tvExpenseDate.setOnClickListener(this);

        //Button
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        //
        Intent intent = getIntent();
        showInitialInfomation(intent);

    }
    private void showInitialInfomation(Intent intent){
        if(intent.hasExtra("expenseId")) {
            isEdit = true;

            int expenseId = intent.getIntExtra("expenseId", -1);
            Expense expense;
            try {
                expense = expenseViewModel.findExpenseWithId(expenseId);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            tvAmount.setText(expense.getExpMoney());
            category = categoryViewModel.findCategoryById(expense.getExpCategory());
            showCategoryInfomation(category);
            wallet = walletViewModel.findWalletWithId(expense.getExpWallet());
            showWalletInfomation(wallet);

            etExpenseNote.setText(expense.getExpNote());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = dateFormat.format(expense.getExpDate());
            tvExpenseDate.setText(dateString);
            expenseDate = expense.getExpDate();
        }
        else {
            //Show initial infomation
            category = categoryViewModel.findAllCategoryWithList().get(0);
            showCategoryInfomation(category);
            showChooseCategory(category);

            wallet = walletViewModel.findAllWalletWithList().get(0);
            showWalletInfomation(wallet);
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            tvExpenseDate.setText(day + "/" + (month + 1) + "/" + year);
            expenseDate = calendar.getTime();
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlCategory:
                showChooseCategory(category);
                break;
            case R.id.rlWallet:
                showChooseWallet();
                break;
            case R.id.etExpenseDate:
                showDatePicker();
                break;
            case R.id.btnSave:
                saveExpense();
                break;
        }
    }
    public void saveExpense(){
        expenseNote = etExpenseNote.getText().toString();
        expenseMoney = tvAmount.getText().toString();
        if(!isNumeric(expenseMoney)){
            Toast.makeText(this, "Số tiền không được chứa ký tự đặc biệt!", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(expenseMoney)){
            Toast.makeText(this, "Số tiền không được để trống!", Toast.LENGTH_SHORT).show();
        }
        Expense expense = new Expense(expenseNote, expenseMoney, expenseDate, expenseType, expenseCategory, expenseWallet);
        if(isEdit){
            int expId = getIntent().getIntExtra("expenseId", -1);
            expense.setExpId(expId);
            expenseViewModel.updateExpense(expense);
//            if(wallet != null){
//                long walletMoney = Long.parseLong(wallet.getWalMoney());
//                long oldExpenseMoney = Long.parseLong(expense.getExpMoney());
//                long newExpenseMoney = Long.parseLong(tvAmount.getText().toString());
//                long newWalletMoney;
//
//                if (expenseType == 2) {
//                    newWalletMoney = walletMoney - oldExpenseMoney + newExpenseMoney;
//                } else {
//                    newWalletMoney = walletMoney + oldExpenseMoney - newExpenseMoney;
//                }
//
//                wallet.setWalMoney(String.valueOf(newWalletMoney));
//                walletViewModel.updateWallet(wallet);
//            }
        }
        else {
            expenseViewModel.insertExpense(expense);
            if(wallet != null){
                if(expenseType== 2){
                    wallet.setWalMoney(String.valueOf(Long.parseLong(wallet.getWalMoney()) - Long.parseLong(expenseMoney)));
                }
                else {
                    wallet.setWalMoney(String.valueOf(Long.parseLong(wallet.getWalMoney()) + Long.parseLong(expenseMoney)));
                }
            walletViewModel.updateWallet(wallet);
            }
        }
        finish();
    }
    public void showDatePicker(){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String dateString = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                tvExpenseDate.setText(dateString);
                expenseDate = calendar.getTime();
            }
        },year,month,day);
        datePickerDialog.show();
    }
    public void showWalletInfomation(Wallet wallet) {
        ivWallet.setImageResource(getIdImage(wallet.getWalImage()));
        tvWalletName.setText(wallet.getWalName());
        tvWalletMoneyExist.setText("Số tiền: " + numberFormat(wallet.getWalMoney()));
        expenseWallet = wallet.getWalId();
    }

    public void showChooseWallet() {
        ChooseWalletFragment fragment;
        fragment = ChooseWalletFragment.newInstance();
        fragment.setDialogFragmentListener(this);
        fragment.show(getSupportFragmentManager(),ChooseWalletFragment.TAG);
    }

    @Override
    public void onItemClick(Wallet wallet) {
        this.wallet = wallet;
        showWalletInfomation(wallet);
    }

    private void showChooseCategory(Category category) {
        ChooseCategoryFragment fragment;
        fragment = ChooseCategoryFragment.newInstance(category);
        fragment.setDialogFragmentListener(this);
        fragment.show(this.getSupportFragmentManager(), ChooseCategoryFragment.TAG);
    }

    private void showCategoryInfomation(Category category) {
        ivCategory.setImageResource(getIdImage(category.getCateImage()));
        tvCategoryName.setText(category.getCateName());
        if(category.getCateId() == 1){
            tvCategoryType.setText("Khoản thu");
            tvAmount.setHintTextColor(getResources().getColor(R.color.button_success));
            tvAmount.setTextColor(getResources().getColor(R.color.button_success));
            tvExpenseTitle.setText("Thêm khoản thu");
            if(isEdit){
                tvExpenseTitle.setText("Sửa khoản thu");
            }
        }
        else{
            tvCategoryType.setText("Khoản chi");
            tvAmount.setHintTextColor(getResources().getColor(R.color.button_cancel));
            tvAmount.setTextColor(getResources().getColor(R.color.button_cancel));
            tvExpenseTitle.setText("Thêm khoản chi");
            if(isEdit){
                tvExpenseTitle.setText("Sửa khoản chi");
            }
        }
        expenseCategory = category.getCateId();
        expenseType = category.getCateType();
    }
    @Override
    public void onClickListener(Category category) {
        this.category = category;
        showCategoryInfomation(category);
    }

    public int getIdImage(String name){
        return getResources().getIdentifier(name,"drawable",getPackageName());
    }
    public String numberFormat(String string){
        Long number = Long.parseLong(string);
        DecimalFormat formatter= (DecimalFormat) NumberFormat.getInstance();
        formatter.applyPattern("#,###,###,###");
        String formatString = formatter.format(number);
        return formatString;
    }
    public static boolean isNumeric(String str){
        try {
            Long.parseLong(str);
            return true;
        }
        catch(NumberFormatException e){

            return false;
        }
    }



}