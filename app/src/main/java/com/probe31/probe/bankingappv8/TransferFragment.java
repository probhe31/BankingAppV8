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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.probe31.probe.bankingappv8.model.Account;
import com.probe31.probe.bankingappv8.model.AccountResponse;
import com.probe31.probe.bankingappv8.model.TransferRequest;
import com.probe31.probe.bankingappv8.model.TransferResponse;
import com.probe31.probe.bankingappv8.viewmodel.AccountsFragmentViewModel;
import com.probe31.probe.bankingappv8.viewmodel.TransferFragmentViewModel;

import java.util.List;

public class TransferFragment extends Fragment {

    RecyclerView recyclerView;
    AccountsRecyclerAdapter accountsRecyclerAdapter;
    List<Account> accountList;


    TransferFragmentViewModel transferFragmentVM;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_accounts, null);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        transferFragmentVM = ViewModelProviders.of(this).get(TransferFragmentViewModel.class);



    }


    public void MakeTransfer(View view){

        TextView fromAccountText = (TextView)getView().findViewById(R.id.from_account_text);
        EditText amountEdit = (EditText) getView().findViewById(R.id.amount_transfer);
        EditText toAccountEdit = (EditText) getView().findViewById(R.id.to_account_text);


        SharedPreferences settings = getActivity().getSharedPreferences("myPrefs", 0);
        String token = settings.getString("token", "");

        float amount = Float.parseFloat(amountEdit.getText().toString());
        String fromAccount = fromAccountText.getText().toString();
        String toAccount = toAccountEdit.getText().toString();

        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setAmount(amount);
        transferRequest.setFromAccountNumber(fromAccount);
        transferRequest.setToAccountNumber(toAccount);


        transferFragmentVM.makeTransfer(token, transferRequest).observe(this, new Observer<TransferResponse>() {
            @Override
            public void onChanged(@Nullable TransferResponse transferResponse) {

                if(transferResponse!=null)
                {
                    //accountList = accountResponse.getBankAccounts();
                    //accountsRecyclerAdapter.setAccountList(accountList);
                }
            }
        });


    }

    void GotoBoucher(){

    }
}
