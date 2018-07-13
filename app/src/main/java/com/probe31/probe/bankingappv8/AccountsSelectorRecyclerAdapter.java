package com.probe31.probe.bankingappv8;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.probe31.probe.bankingappv8.model.Account;

import java.util.List;

public class AccountsSelectorRecyclerAdapter extends RecyclerView.Adapter<AccountsSelectorRecyclerAdapter.ViewHolder>{

    static View myview;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameAccount_text;
        TextView amountAccount_text;
        TextView numberAccount_text;
        Button useButton;



        public ViewHolder(View view) {
            super(view);
            nameAccount_text = (TextView)view.findViewById(R.id.nameAccount_text);
            amountAccount_text = (TextView)view.findViewById(R.id.amountAccount_text);
            numberAccount_text = (TextView)view.findViewById(R.id.numberAccount_text);
            useButton = (Button)view.findViewById(R.id.use_btn);
            //useButton.setOnClickListener(new );

            myview = view;

        }
    }

    List<Account> accountList;
    ViewGroup mParent;

    public AccountsSelectorRecyclerAdapter(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public AccountsSelectorRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.accounts_selector_adapter, parent, false);

        mParent = parent;
        return new AccountsSelectorRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AccountsSelectorRecyclerAdapter.ViewHolder holder, int position) {
        final Account account = accountList.get(position);
        holder.nameAccount_text.setText(account.getType());
        holder.amountAccount_text.setText("S/."+account.getBalance());
        holder.numberAccount_text.setText(account.getNumber());


        holder.useButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView type =  mParent.getRootView().findViewById(R.id.main_nameAccount_text);
                TextView amount =  myview.getRootView().findViewById(R.id.main_amountAccount_text);
                TextView numbraccount =  myview.getRootView().findViewById(R.id.main_from_account_text);
                type.setText(account.getType());
                amount.setText("S/. "+account.getBalance());
                numbraccount.setText(account.getNumber());
                mParent.setVisibility(View.GONE);
            }
        });
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
