package com.example.moneymanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagement.R;
import com.example.moneymanagement.activity.InsertExpenseActivity;
import com.example.moneymanagement.entity.Category;
import com.example.moneymanagement.entity.Expense;
import com.example.moneymanagement.viewmodel.CategoryViewModel;
import com.example.moneymanagement.viewmodel.ExpenseViewModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ExpenseAdapter extends ListAdapter<Expense, ExpenseAdapter.ExpensViewHolder> {
    private final Context context;
    private final ExpenseViewModel expenseViewModel;
    private CategoryViewModel categoryViewModel;

    public ExpenseAdapter(Context context,ExpenseViewModel expenseViewModel) {
        super(new DiffUtil.ItemCallback<Expense>() {
            @Override
            public boolean areItemsTheSame(@NonNull Expense oldItem, @NonNull Expense newItem) {
                return oldItem.getExpId() == newItem.getExpId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Expense oldItem, @NonNull Expense newItem) {
                return oldItem.getExpMoney().equals(newItem.getExpMoney()) &&
                        oldItem.getExpCategory() == newItem.getExpCategory() &&
                        oldItem.getExpType() == newItem.getExpType() &&
                        oldItem.getExpWallet() == newItem.getExpWallet() &&
                        oldItem.getExpNote().equals(newItem.getExpNote());
            }
        });
        this.context = context;
        this.expenseViewModel = expenseViewModel;
    }


    @NonNull
    @Override
    public ExpensViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_expense,null,false);
        return new ExpensViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensViewHolder holder, int position) {
        Expense expense = getItem(position);
        categoryViewModel =  new ViewModelProvider((ViewModelStoreOwner) context).get(CategoryViewModel.class);
        Category category = categoryViewModel.findCategoryById(expense.getExpCategory());
        holder.ivCateogry.setImageResource(getIdImage(category.getCateImage()));
        holder.tvCategory.setText(category.getCateName());
        if(!expense.getExpNote().isEmpty()){
            holder.tvNote.setVisibility(View.VISIBLE);
            holder.tvNote.setText(expense.getExpNote());
        }else{
            RelativeLayout.LayoutParams layoutParams =(RelativeLayout.LayoutParams) holder.tvCategory.getLayoutParams();
            holder.tvNote.setVisibility(View.GONE);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,1);
            holder.tvCategory.setLayoutParams(layoutParams);

        }

        holder.tvMoney.setText(expense.getExpMoney());
        if(expense.getExpType() == 1){
            holder.tvMoney.setTextColor(context.getResources().getColor(R.color.button_success));
            holder.ivUpDown.setImageResource(R.drawable.ic_up);
            holder.ivUpDown.setColorFilter(ContextCompat.getColor(context,R.color.button_success));
        }
        else{
            holder.tvMoney.setTextColor(context.getResources().getColor(R.color.button_cancel));
            holder.ivUpDown.setImageResource(R.drawable.ic_down);
            holder.ivUpDown.setColorFilter(ContextCompat.getColor(context,R.color.button_cancel));

        }
        Date date = expense.getExpDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        holder.tvDate.setText(day + "/" + month + "/"+ year );
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showPopupMenu(v, expense);
                return true;
            }
        });

    }

    private void showPopupMenu(View view, Expense expense){
        PopupMenu popupMenu = new PopupMenu(view.getContext(),view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.edit:
                        Intent i = new Intent(view.getContext(), InsertExpenseActivity.class);
                        i.putExtra("expenseId",expense.getExpId());
                        view.getContext().startActivity(i);
                        return true;
                    case R.id.delete:
                        try{
                            expenseViewModel.deleteExpense(expense);
                            Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception ex){
                            Toast.makeText(context, "Xóa thất bại!", Toast.LENGTH_SHORT).show();

                        }
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();


    }
    public class ExpensViewHolder extends RecyclerView.ViewHolder{
        ImageView ivCateogry, ivUpDown;
        TextView tvCategory, tvNote, tvMoney, tvDate;

        public ExpensViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCateogry = itemView.findViewById(R.id.ivCategory);
            ivUpDown = itemView.findViewById(R.id.ivUpDown);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvNote = itemView.findViewById(R.id.tvNote);
            tvMoney = itemView.findViewById(R.id.tvMoney);
            tvDate = itemView.findViewById(R.id.tvDate);

        }
    }
    private int getIdImage(String name) {
        int drawableResourceId = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        return drawableResourceId;
    }

    //Định dạng số tiền
    public String numberFormat(String string) {
        Long number = Long.parseLong(string);
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        formatter.applyPattern("#,###,###,###");
        String formattedString = formatter.format(number);
        return formattedString;
    }

}
