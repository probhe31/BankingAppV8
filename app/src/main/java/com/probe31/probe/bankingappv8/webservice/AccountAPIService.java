package com.probe31.probe.bankingappv8.webservice;

import com.probe31.probe.bankingappv8.model.Account;
import com.probe31.probe.bankingappv8.model.AccountsRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountAPIService {

    @POST("api/accounts")
    Call<List<Account>> getAccountList(@Body AccountsRequest accountRequest);

}
