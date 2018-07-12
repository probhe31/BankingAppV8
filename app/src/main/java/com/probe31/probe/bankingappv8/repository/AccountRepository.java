package com.probe31.probe.bankingappv8.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.probe31.probe.bankingappv8.model.Account;
import com.probe31.probe.bankingappv8.model.AccountsRequest;
import com.probe31.probe.bankingappv8.webservice.AccountAPIService;
import com.probe31.probe.bankingappv8.webservice.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {

    private AccountAPIService accountAPIService;

    public MutableLiveData<List<Account>> getAccountList(final AccountsRequest accountRequest) {

        final MutableLiveData<List<Account>> accountsResponseMutableLiveData = new MutableLiveData<>();

        accountAPIService = RetrofitClientInstance.getRetrofitInstance().create(AccountAPIService.class);

        Call<List<Account>> call = accountAPIService.getAccountList(accountRequest);

        call.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {

                Log.d("onresponse: ", "response: " + response );
                Log.d("onresponse: ", "body: " + response.body());

                List<Account> accountResponse = response.body();

                Log.d("onresponse: ", "body: " + accountResponse.get(0).getName());

                if(accountResponse != null)
                {
                    accountsResponseMutableLiveData.setValue(accountResponse);
                }
                else
                {
                    //-if(response.code()== HttpURLConnection.HTTP_UNAUTHORIZED)
                    accountsResponseMutableLiveData.setValue(null);

                }
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {

                Log.d("failure: ", "error: " + t );
                accountsResponseMutableLiveData.setValue(null);

            }
        });

        return accountsResponseMutableLiveData;

    }

}
