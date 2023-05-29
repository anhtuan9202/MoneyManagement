package com.example.moneymanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagement.R;
import com.example.moneymanagement.entity.Category;

public class CategoryAdapter extends ListAdapter<Category, CategoryAdapter.CategoryHolder> {
    private final Context context;
    private OnItemClickListener listener;

    public CategoryAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }
    private static final DiffUtil.ItemCallback<Category> DIFF_CALLBACK = new DiffUtil.ItemCallback<Category>() {
        @Override
        public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.getCateId() == newItem.getCateId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.getCateName().equals(newItem.getCateName()) && oldItem.getCateImage().equals(newItem.getCateImage());
        }
    };
    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category,null);
        return new CategoryHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Category category = getItem(position);
        holder.ivCategoryImageItem.setImageResource(getIdImage(category.getCateImage()));
        holder.tvCategoryNameItem.setText(category.getCateName());
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {
        TextView tvCategoryNameItem;
        ImageView ivCategoryImageItem;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            tvCategoryNameItem = itemView.findViewById(R.id.tvCategoryNameItem);
            ivCategoryImageItem = itemView.findViewById(R.id.ivCategoryImageItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }


    private int getIdImage(String image){
        int imageId = context.getResources().getIdentifier(image, "drawable", context.getPackageName());
        return  imageId;
    }
    public interface OnItemClickListener{
        void onItemClick(Category category);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
