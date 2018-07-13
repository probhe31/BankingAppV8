package com.probe31.probe.bankingappv8;


import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.probe31.probe.bankingappv8.model.Account;

import java.util.List;

public class AccountsRecyclerAdapter extends RecyclerView.Adapter<AccountsRecyclerAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameAccount_text;
        TextView amountAccount_text;
        TextView numberAccount_text;

        public ViewHolder(View view) {
            super(view);
            nameAccount_text = (TextView)view.findViewById(R.id.nameAccount_text);
            amountAccount_text = (TextView)view.findViewById(R.id.amountAccount_text);
            numberAccount_text = (TextView)view.findViewById(R.id.numberAccount_text);
        }
    }

    List<Account> accountList;

    public AccountsRecyclerAdapter(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public AccountsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.accounts_adapter, parent, false);

        return new AccountsRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AccountsRecyclerAdapter.ViewHolder holder, int position) {
        Account account = accountList.get(position);
        holder.nameAccount_text.setText(account.getType());
        //holder.amountAccount_text.setText("S/. "+String.valueOf(account.getAmount()));
        holder.amountAccount_text.setText("S/."+account.getBalance());
        holder.numberAccount_text.setText(String.valueOf(account.getNumber()));
    }




    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        if(accountList != null){
            return accountList.size();
        }
        return 0;

    }



}

