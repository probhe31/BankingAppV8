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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.probe31.probe.bankingappv8.model.Account;
import com.probe31.probe.bankingappv8.model.AccountResponse;
import com.probe31.probe.bankingappv8.model.TransferRequest;
import com.probe31.probe.bankingappv8.model.TransferResponse;
import com.probe31.probe.bankingappv8.viewmodel.AccountsFragmentViewModel;
import com.probe31.probe.bankingappv8.viewmodel.TransferFragmentViewModel;

import org.w3c.dom.Text;

import java.util.List;

public class TransferFragment extends Fragment {

    RecyclerView recyclerView;
    AccountsSelectorRecyclerAdapter accountsRecyclerAdapter;
    List<Account> accountList;


    TransferFragmentViewModel transferFragmentVM;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_transfer, null);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        transferFragmentVM = ViewModelProviders.of(this).get(TransferFragmentViewModel.class);

        fillRecyclerView();

    }

    void fillRecyclerView()
    {
        final Button transfer_btn = (Button) getView().findViewById(R.id.transfer_btn);
        transfer_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MakeTransfer();
            }
        });

        final Button select_btn = (Button) getView().findViewById(R.id.select_account_btn);
        select_btn .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ShowRecyclerView();
            }
        });


        recyclerView = (RecyclerView)getView().findViewById(R.id.accounts_selection_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        accountsRecyclerAdapter = new AccountsSelectorRecyclerAdapter(accountList);
        recyclerView.setAdapter(accountsRecyclerAdapter);

        SharedPreferences settings = getActivity().getSharedPreferences("myPrefs", 0);
        String token = settings.getString("token", "");
        int idClient = settings.getInt("id_customer", 0);

        transferFragmentVM.getAccountsList(token, idClient).observe(this, new Observer<AccountResponse>() {
            @Override
            public void onChanged(@Nullable AccountResponse accountResponse) {

                if(accountResponse!=null)
                {
                    accountList= accountResponse.getBankAccounts();
                    accountsRecyclerAdapter.setAccountList(accountList);
                    TextView typeAccount = getView().findViewById(R.id.main_nameAccount_text);
                    TextView numberAccount = getView().findViewById(R.id.main_from_account_text);
                    TextView amountAccount = getView().findViewById(R.id.main_amountAccount_text);
                    typeAccount.setText(accountList.get(0).getType());
                    numberAccount.setText(accountList.get(0).getNumber());
                    amountAccount.setText("S/. "+accountList.get(0).getBalance());
                }
            }
        });
    }

    public void MakeTransfer(){

        TextView fromAccountText = (TextView)getView().findViewById(R.id.main_from_account_text);
        EditText amountEdit = (EditText) getView().findViewById(R.id.amount_transfer);

        EditText toAccountEdit1 = (EditText) getView().findViewById(R.id.to_account_p1_text);
        EditText toAccountEdit2 = (EditText) getView().findViewById(R.id.to_account_p2_text);
        EditText toAccountEdit3 = (EditText) getView().findViewById(R.id.to_account_p3_text);
        EditText toAccountEdit4 = (EditText) getView().findViewById(R.id.to_account_p4_text);


        SharedPreferences settings = getActivity().getSharedPreferences("myPrefs", 0);
        String token = settings.getString("token", "");

        boolean cancel = false;
        View focusView = null;

        float amount = 0;
        if(!TextUtils.isEmpty(amountEdit.getText().toString())) {
            amount = Float.parseFloat(amountEdit.getText().toString());
        }else{
            focusView = amountEdit;
            cancel = true;
        }

        String fromAccount = fromAccountText.getText().toString();

        String toAccount1 = toAccountEdit1.getText().toString();
        String toAccount2 = toAccountEdit2.getText().toString();
        String toAccount3 = toAccountEdit3.getText().toString();
        String toAccount4 = toAccountEdit4.getText().toString();
        if(TextUtils.isEmpty(toAccount1)){
            focusView = toAccountEdit1;
            cancel = true;
        }

        if(TextUtils.isEmpty(toAccount2)){
            focusView = toAccountEdit2;
            cancel = true;
        }

        if(TextUtils.isEmpty(toAccount3)){
            focusView = toAccountEdit3;
            cancel = true;
        }

        if(TextUtils.isEmpty(toAccount4)){
            focusView = toAccountEdit4;
            cancel = true;
        }

        if(cancel){



        }else{

            TransferRequest transferRequest = new TransferRequest();
            transferRequest.setAmount(amount);
            transferRequest.setFromAccountNumber(fromAccount);
            transferRequest.setToAccountNumber(toAccount1+"-"+toAccount2+"-"+toAccount3+"-"+toAccount4);


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



    }

    void GotoBoucher(){

    }

    void ShowRecyclerView(){
        recyclerView.setVisibility(View.VISIBLE);
    }
}
