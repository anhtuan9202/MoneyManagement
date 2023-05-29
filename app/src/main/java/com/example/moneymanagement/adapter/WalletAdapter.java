package com.example.moneymanagement.adapter;

import android.content.Context;
import android.text.style.IconMarginSpan;
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
import com.example.moneymanagement.entity.Wallet;

public class WalletAdapter extends ListAdapter<Wallet, WalletAdapter.WalletHolder> {
    private final Context context;
    private OnItemClickListener listener;

    public WalletAdapter(Context context) {
        super(CALLBACK);
        this.context = context;
    }
    public static final DiffUtil.ItemCallback<Wallet> CALLBACK = new DiffUtil.ItemCallback<Wallet>() {
        @Override
        public boolean areItemsTheSame(@NonNull Wallet oldItem, @NonNull Wallet newItem) {
            return oldItem.getWalId() == newItem.getWalId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Wallet oldItem, @NonNull Wallet newItem) {
            return oldItem.getWalImage().equals(newItem.getWalImage())
                    && oldItem.getWalName().equals(newItem.getWalName())
                    && oldItem.getWalMoney().equals(newItem.getWalMoney());
        }
    };

    @NonNull
    @Override
    public WalletHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wallet, null);
        return new WalletHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletHolder holder, int position) {
        Wallet wallet = getItem(position);
        holder.ivWalletImage.setImageResource(getIdImage(wallet.getWalImage()));
        holder.tvItemWalletName.setText(wallet.getWalName());
        holder.tvItemWalletMoney.setText(wallet.getWalMoney());
    }


    public class WalletHolder extends RecyclerView.ViewHolder {
        ImageView ivWalletImage;
        TextView tvItemWalletName, tvItemWalletMoney;

        public WalletHolder(@NonNull View itemView) {
            super(itemView);
            ivWalletImage = itemView.findViewById(R.id.ivWalletImage);
            tvItemWalletName = itemView.findViewById(R.id.tvItemWalletName);
            tvItemWalletMoney = itemView.findViewById(R.id.tvItemWalletMoney);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getItem(getAdapterPosition()));
                }
            });
        }
    }
    public int getIdImage(String name){
        int idImage = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        return idImage;
    }
    public interface OnItemClickListener{
        void onItemClick(Wallet wallet);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
