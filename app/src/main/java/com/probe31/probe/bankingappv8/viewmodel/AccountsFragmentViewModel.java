package com.probe31.probe.bankingappv8.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.probe31.probe.bankingappv8.model.Account;
import com.probe31.probe.bankingappv8.model.AccountResponse;
import com.probe31.probe.bankingappv8.model.AccountsRequest;
import com.probe31.probe.bankingappv8.repository.AccountRepository;

import java.util.List;

public class AccountsFragmentViewModel extends ViewModel{

    private MutableLiveData<AccountResponse> accountResponse;
    private AccountRepository accountRepository;

    public AccountsFragmentViewModel(){
        accountResponse = new MutableLiveData<>();
        accountResponse.setValue(null);
        accountRepository = new AccountRepository();
    }

    public LiveData<AccountResponse> getAccountsList(int customerID)
    {
        //AccountsRequest accountRequest = new AccountsRequest();
        //accountRequest.setId(userId);
        accountResponse = accountRepository.getAccountList(customerID);
        return accountResponse;
    }

}
