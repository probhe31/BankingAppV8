package com.probe31.probe.bankingappv8.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.probe31.probe.bankingappv8.model.Account;
import com.probe31.probe.bankingappv8.model.AccountsRequest;
import com.probe31.probe.bankingappv8.repository.AccountRepository;

import java.util.List;

public class AccountsFragmentViewModel extends ViewModel{

    private MutableLiveData<List<Account>> accountResponse;
    private AccountRepository accountRepository;

    public AccountsFragmentViewModel(){
        accountResponse = new MutableLiveData<>();
        accountResponse.setValue(null);
        accountRepository = new AccountRepository();
    }

    public LiveData<List<Account>> getAccountsList(int userId)
    {
        AccountsRequest accountRequest = new AccountsRequest();
        accountRequest.setId(userId);
        accountResponse = accountRepository.getAccountList(accountRequest);
        return accountResponse;
    }

}
