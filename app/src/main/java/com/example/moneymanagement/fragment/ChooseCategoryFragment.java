package com.example.moneymanagement.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import com.example.moneymanagement.R;
import com.example.moneymanagement.adapter.CategoryAdapter;
import com.example.moneymanagement.entity.Category;
import com.example.moneymanagement.viewmodel.CategoryViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.List;

public class ChooseCategoryFragment extends BottomSheetDialogFragment{
    private CategoryViewModel categoryViewModel;
    public static final String TAG = "ChooseCategoty";
    private MaterialButtonToggleGroup tgbCategoryType;
    private MaterialButton btnRevenue, btnExpense;
    private CategoryAdapter categoryAdapter;
    private RecyclerView rvChooseCategory;
    private ChooseCategoryFragmentListener listener;
    public interface ChooseCategoryFragmentListener {
        void onClickListener(Category category);
    }
    public void setDialogFragmentListener(ChooseCategoryFragmentListener listener){
            this.listener = listener;

    }
    public static ChooseCategoryFragment newInstance(Category category) {
        ChooseCategoryFragment fragment = new ChooseCategoryFragment();
        Bundle args = new Bundle();
        args.putInt("id", category.getCateId());
        args.putString("name", category.getCateName());
        args.putString("img", category.getCateImage());
        args.putInt("type", category.getCateType());
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_category, container, false);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        tgbCategoryType = view.findViewById(R.id.tgbCategoryType);
        btnRevenue = view.findViewById(R.id.btnRevenue);
        btnExpense = view.findViewById(R.id.btnExpense);
        rvChooseCategory = view.findViewById(R.id.rvChooseCategory);
        tgbCategoryType.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                switch (checkedId){
                    case R.id.btnRevenue:
                         showRevenue();
                         break;
                    case R.id.btnExpense:
                         showExpense();
                         break;
                }
            }
        });
        if(getArguments() != null && getArguments().getInt("type") == 1){
            showRevenue();
        }
        else{
            showExpense();
        }
        rvChooseCategory.setLayoutManager(new GridLayoutManager(getContext(),4));
        categoryAdapter = new CategoryAdapter(getContext());
        rvChooseCategory.setAdapter(categoryAdapter);
        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Category category) {
                if(listener != null){
                    listener.onClickListener(category);

                }
                dismiss();

            }
        });
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private void showRevenue(){
        btnRevenue.setBackgroundColor(getResources().getColor(R.color.button_checked));
        btnRevenue.setTextColor(getResources().getColor(R.color.white));
        btnExpense.setBackgroundColor(getResources().getColor(R.color.button_uncheck));
        btnExpense.setTextColor(getResources().getColor(R.color.black));
        getListCategory(categoryViewModel.findAllRevenue());
    }
    private void showExpense() {
        btnExpense.setBackgroundColor(getResources().getColor(R.color.button_checked));
        btnExpense.setTextColor(getResources().getColor(R.color.white));
        btnRevenue.setBackgroundColor(getResources().getColor(R.color.button_uncheck));
        btnRevenue.setTextColor(getResources().getColor(R.color.black));
        getListCategory(categoryViewModel.findAllExpense());
    }
    public void getListCategory(LiveData<List<Category>> listCategory){
        listCategory.observe(getActivity(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryAdapter.submitList(categories);
            }
        });

    }
}