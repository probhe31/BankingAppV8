package com.probe31.probe.bankingappv8.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.probe31.probe.bankingappv8.model.AccountResponse;
import com.probe31.probe.bankingappv8.model.TransferRequest;
import com.probe31.probe.bankingappv8.model.TransferResponse;
import com.probe31.probe.bankingappv8.repository.AccountRepository;
import com.probe31.probe.bankingappv8.repository.TransferRepository;

public class TransferFragmentViewModel extends ViewModel {

    private MutableLiveData<TransferResponse> transferResponse;
    private MutableLiveData<AccountResponse> accountResponse;
    private TransferRepository transferRepository;
    private AccountRepository accountRepository;

    public TransferFragmentViewModel(){
        transferResponse = new MutableLiveData<>();
        accountResponse = new MutableLiveData<>();
        transferResponse.setValue(null);
        accountResponse.setValue(null);
        transferRepository = new TransferRepository();
        accountRepository = new AccountRepository();
    }

    public LiveData<TransferResponse> makeTransfer(String token, TransferRequest transferRequest)
    {
        transferResponse = transferRepository.makeTransfer(token, transferRequest);
        return transferResponse;
    }

    public LiveData<AccountResponse> getAccountsList(String token, int customerID)
    {
        accountResponse = accountRepository.getAccountList(token, customerID);
        return accountResponse;
    }

    public boolean ValidateAmount(float value){
       return value!=0;
    }




}
