package com.probe31.probe.bankingappv8.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.probe31.probe.bankingappv8.model.AccountResponse;
import com.probe31.probe.bankingappv8.repository.AccountRepository;

public class AccountsFragmentViewModel extends ViewModel{

    private MutableLiveData<AccountResponse> accountResponse;
    private AccountRepository accountRepository;

    public AccountsFragmentViewModel(){
        accountResponse = new MutableLiveData<>();
        accountResponse.setValue(null);
        accountRepository = new AccountRepository();
    }

    public LiveData<AccountResponse> getAccountsList(String token, int customerID)
    {
        accountResponse = accountRepository.getAccountList(token, customerID);
        return accountResponse;
    }

}
