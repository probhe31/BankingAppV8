package com.probe31.probe.bankingappv8.webservice;

import com.probe31.probe.bankingappv8.model.Client;
import com.probe31.probe.bankingappv8.model.TokenRequest;
import com.probe31.probe.bankingappv8.model.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginAPIService {

    @POST("oauth/token")
    Call<TokenResponse> getTokenAccess(@Body TokenRequest tokenRequest);

}
