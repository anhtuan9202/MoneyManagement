package com.example.moneymanagement.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moneymanagement.R;
import com.example.moneymanagement.adapter.WalletAdapter;
import com.example.moneymanagement.entity.Wallet;
import com.example.moneymanagement.viewmodel.WalletViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class ChooseWalletFragment extends BottomSheetDialogFragment {
    private TextView tvChooseWallet;
    private RecyclerView rvChooseWallet;
    public static final String TAG = "ChooseWallet";
    private WalletAdapter adapter;
    private ChooseWalletFragmentListener listener;
    private LiveData<List<Wallet>> allWallet;
    private WalletViewModel walletViewModel;
    public interface ChooseWalletFragmentListener{
        void onItemClick(Wallet wallet);
    }
    public void setDialogFragmentListener(ChooseWalletFragmentListener listener){
        this.listener = listener;
    }
    public ChooseWalletFragment() {

    }
    // TODO: Rename and change types and number of parameters
    public static ChooseWalletFragment newInstance() {
        ChooseWalletFragment fragment = new ChooseWalletFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_wallet, container, false);
        tvChooseWallet = view.findViewById(R.id.tvChooseWallet);
        rvChooseWallet = view.findViewById(R.id.rvChooseWallet);
        rvChooseWallet.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WalletAdapter(getContext());
        rvChooseWallet.setAdapter(adapter);
        adapter.setOnItemClickListener(new WalletAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Wallet wallet) {
                listener.onItemClick(wallet);
                dismiss();
            }
        });
        walletViewModel = new ViewModelProvider(this).get(WalletViewModel.class);
        allWallet = walletViewModel.findAllWallet();
        allWallet.observe(getActivity(), new Observer<List<Wallet>>() {
            @Override
            public void onChanged(List<Wallet> wallets) {
                adapter.submitList(wallets);
            }
        });
        return view;
    }
}