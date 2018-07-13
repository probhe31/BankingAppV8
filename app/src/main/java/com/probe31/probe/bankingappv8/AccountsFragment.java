package com.probe31.probe.bankingappv8;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.probe31.probe.bankingappv8.model.Account;
import com.probe31.probe.bankingappv8.model.AccountResponse;
import com.probe31.probe.bankingappv8.viewmodel.AccountsFragmentViewModel;

import java.util.List;


public class AccountsFragment extends Fragment {


    RecyclerView recyclerView;
    AccountsRecyclerAdapter accountsRecyclerAdapter;

    AccountsFragmentViewModel accountsFragmentVM;

    List<Account> accountList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_accounts, null);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        accountsFragmentVM = ViewModelProviders.of(this).get(AccountsFragmentViewModel.class);

        fillRecyclerView();


    }


    void fillRecyclerView()
    {
        recyclerView = (RecyclerView)getView().findViewById(R.id.accountsRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        accountsRecyclerAdapter = new AccountsRecyclerAdapter(accountList);
        recyclerView.setAdapter(accountsRecyclerAdapter);

        SharedPreferences settings = getActivity().getSharedPreferences("myPrefs", 0);
        String token = settings.getString("token", "");
        int idClient = settings.getInt("id_customer", 0);

        accountsFragmentVM.getAccountsList(token, idClient).observe(this, new Observer<AccountResponse>() {
            @Override
            public void onChanged(@Nullable AccountResponse accountResponse) {

                if(accountResponse!=null)
                {
                    accountList= accountResponse.getBankAccounts();
                    accountsRecyclerAdapter.setAccountList(accountList);
                    //Toast.makeText(getActivity(), "You are inside Import Fragment", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
