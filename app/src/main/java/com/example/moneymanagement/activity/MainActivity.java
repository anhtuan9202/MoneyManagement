package com.example.moneymanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.moneymanagement.R;
import com.example.moneymanagement.adapter.ViewPager2Adapter;
import com.example.moneymanagement.ui.home.HomeFragment;
import com.example.moneymanagement.ui.setting.SettingFragment;
import com.example.moneymanagement.ui.expense.ExpenseFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager2 vpMain;
    ViewPager2Adapter adapter;
    ImageButton ibtnAdd;
    BottomNavigationView bnvMain;
    TextView tvTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find View
        initial();
        // create list fragment
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        // add fragment to list fragment
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ExpenseFragment());
        fragmentList.add(new SettingFragment());
        // create and set adapter
        adapter = new ViewPager2Adapter(this, fragmentList);
        vpMain.setAdapter(adapter);
        //catch event
        vpMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        tvTitle.setText("Trang chủ");
                        bnvMain.getMenu().getItem(position).setChecked(true);
                        showButtonAdd();
                        break;
                    case 1:
                        tvTitle.setText("Thu chi");
                        bnvMain.getMenu().getItem(position).setChecked(true);
                        showButtonAdd();
                        break;
                    case 2:
                        tvTitle.setText("Cài đặt");
                        bnvMain.getMenu().getItem(position).setChecked(true);
                        hideButtonAdd();
                        break;
                }
            }
        });
        bnvMain.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        vpMain.setCurrentItem(0);
                        tvTitle.setText("Trang chủ");
                        showButtonAdd();
                        break;
                    case R.id.navigation_expense:
                        vpMain.setCurrentItem(1);
                        tvTitle.setText("Thu chi");
                        showButtonAdd();
                        break;
                    case R.id.navigation_settings:
                        vpMain.setCurrentItem(2);
                        tvTitle.setText("Cài đặt");
                        hideButtonAdd();
                        break;

                }

                return true;
            }
        });
        ibtnAdd.setOnClickListener(this);
    }


    private void initial() {
        vpMain = findViewById(R.id.vpMain);
        ibtnAdd = findViewById(R.id.ibtnAdd);
        bnvMain = findViewById(R.id.bnvMain);
        tvTitle = findViewById(R.id.tvTitle);
    }

    private void hideButtonAdd(){
        if(ibtnAdd.getVisibility() == View.VISIBLE){
            ibtnAdd.setVisibility(View.GONE);
        }
    }
    private void showButtonAdd(){
        if(ibtnAdd.getVisibility() != View.VISIBLE){
            ibtnAdd.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtnAdd:
                Intent intent = new Intent(getApplicationContext(), InsertExpenseActivity.class);
                startActivity(intent);
                break;
        }
    }
}