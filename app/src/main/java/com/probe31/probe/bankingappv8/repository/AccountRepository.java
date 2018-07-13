package com.probe31.probe.bankingappv8.repository;

import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.probe31.probe.bankingappv8.model.Account;
import com.probe31.probe.bankingappv8.model.AccountResponse;
import com.probe31.probe.bankingappv8.model.AccountsRequest;
import com.probe31.probe.bankingappv8.webservice.AccountAPIService;
import com.probe31.probe.bankingappv8.webservice.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {

    private AccountAPIService accountAPIService;

    public MutableLiveData<AccountResponse> getAccountList(final String token, final int customerID) {

        final MutableLiveData<AccountResponse> accountsResponseMutableLiveData = new MutableLiveData<>();

        Log.d("bh_acc_rep" , token);
        accountAPIService = RetrofitClientInstance.getRetrofitInstance(token).create(AccountAPIService.class);

        Call<AccountResponse> call = accountAPIService.getAccountList(customerID);

        call.enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {

                AccountResponse accountResponse = response.body();

                if(accountResponse != null)
                {

                    accountsResponseMutableLiveData.setValue(accountResponse);
                }
                else
                {
                    accountsResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {

                accountsResponseMutableLiveData.setValue(null);

            }
        });

        return accountsResponseMutableLiveData;

    }

}
