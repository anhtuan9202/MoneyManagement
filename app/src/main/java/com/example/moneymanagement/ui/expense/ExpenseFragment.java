package com.example.moneymanagement.ui.expense;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanagement.R;
import com.example.moneymanagement.activity.InsertExpenseActivity;
import com.example.moneymanagement.adapter.ExpenseAdapter;
import com.example.moneymanagement.entity.Category;
import com.example.moneymanagement.entity.Expense;
import com.example.moneymanagement.viewmodel.CategoryViewModel;
import com.example.moneymanagement.viewmodel.ExpenseViewModel;
import com.github.mikephil.charting.charts.BarChart;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExpenseFragment extends Fragment  implements View.OnClickListener{

    private ExpenseViewModel expenseViewModel;
    private CategoryViewModel categoryViewModel;
    private ExpenseAdapter adapter;
    private RecyclerView recyclerView;
    private LiveData<List<Expense>> listLiveData;
    private List<Expense> listExpense;
    private List<Category> listCategory;

    private TextView tvTotalRevenue,tvTotalExpense,tvDifferent,tvTitleExpense, tvTitleRevenue;
    private BarChart bcExpense,bcRevenue;
    private long totalRevenue, totalExpense;


    public static ExpenseFragment newInstance() {
        return new ExpenseFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense, container, false);
        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        //recycler view
        recyclerView = view.findViewById(R.id.rvExpense);
        adapter = new ExpenseAdapter(getContext(),expenseViewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        //Chart
        try {
            listExpense = expenseViewModel.findAllExpenseWithList();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        listCategory = categoryViewModel.findAllCategoryWithList();
        bcExpense = view.findViewById(R.id.bcExpense);
        bcRevenue = view.findViewById(R.id.bcRevenue);
        tvTitleExpense = view.findViewById(R.id.tvTitleExpense);
        tvTitleRevenue = view.findViewById(R.id.tvTitleRevenue);

        //Statistical
        tvTotalExpense = view.findViewById(R.id.tvTotalExpense);
        tvTotalRevenue = view.findViewById(R.id.tvTotalRevenue);
        tvDifferent = view.findViewById(R.id.tvDifferent);
        calculate();
        //Live data
        listLiveData = expenseViewModel.findAllExpense();
        listLiveData.observe(getViewLifecycleOwner(), new Observer<List<Expense>>() {
            @Override
            public void onChanged(List<Expense> expenses) {
                List<Expense> expenseList = new ArrayList<>();
                for (Expense expense : expenses) {
                        expenseList.add(expense);
                    }
                adapter.submitList(expenseList);
                listExpense = expenseList;
                calculate();
            }
        });
        return view;
    }

    private void calculate() {
        totalExpense = 0;
        totalRevenue = 0;
        for(int i = 0; i< listExpense.size(); i ++){
            Expense expense = listExpense.get(i);
            if(expense.getExpType()==1){
                totalRevenue += Long.parseLong(expense.getExpMoney());
            }
            else if (expense.getExpType()==2){
                totalExpense += Long.parseLong(expense.getExpMoney());
            }
        }
        tvTotalExpense.setText(String.valueOf(totalExpense));
        tvTotalRevenue.setText(String.valueOf(totalRevenue));
        tvDifferent.setText(String.valueOf(totalRevenue - totalExpense));

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}