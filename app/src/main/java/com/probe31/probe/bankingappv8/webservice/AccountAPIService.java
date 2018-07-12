package com.probe31.probe.bankingappv8.webservice;

import com.probe31.probe.bankingappv8.model.Account;
import com.probe31.probe.bankingappv8.model.AccountResponse;
import com.probe31.probe.bankingappv8.model.AccountsRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AccountAPIService {

    //@POST("api/accounts")
    //Call<List<Account>> getAccountList(@Body AccountsRequest accountRequest);

    //@Headers({"Authorization : 4358a340d8d7aa32d537bdd5c4e1378ea3a9bc58"})
    @GET("api/v1/customers/{id_customer}")
    Call<AccountResponse> getAccountList(@Header("Authorization")String access_token, @Path("id_customer")int customerId);

}
