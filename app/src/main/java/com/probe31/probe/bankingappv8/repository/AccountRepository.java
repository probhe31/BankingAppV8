package com.probe31.probe.bankingappv8.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

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

    public MutableLiveData<AccountResponse> getAccountList(final int customerID) {

        final MutableLiveData<AccountResponse> accountsResponseMutableLiveData = new MutableLiveData<>();

        accountAPIService = RetrofitClientInstance.getRetrofitInstance().create(AccountAPIService.class);

        Call<AccountResponse> call = accountAPIService.getAccountList("Token 4358a340d8d7aa32d537bdd5c4e1378ea3a9bc58", customerID);

        call.enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {

                //Log.d("onresponse: ", "response: " + response );
                //Log.d("onresponse: ", "body: " + response.body());

                AccountResponse accountResponse = response.body();

                //Log.d("onresponse: ", "body: " + accountResponse.get(0).getName());

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
            public void onFailure(Call<AccountResponse> call, Throwable t) {

                Log.d("failure: ", "error: " + t );
                accountsResponseMutableLiveData.setValue(null);

            }
        });

        return accountsResponseMutableLiveData;

    }

}
