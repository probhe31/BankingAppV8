package com.probe31.probe.bankingappv8.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.probe31.probe.bankingappv8.model.TokenRequest;
import com.probe31.probe.bankingappv8.model.TokenResponse;
import com.probe31.probe.bankingappv8.webservice.LoginAPIService;
import com.probe31.probe.bankingappv8.webservice.RetrofitClientInstance;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginRepository {

    private LoginAPIService loginAPIService;
    private MutableLiveData<Integer> responseCode;

    public LoginRepository(){
        responseCode = new MutableLiveData<>();
    }

    public MutableLiveData<Integer> getResponseCode() {
        return responseCode;
    }

    public MutableLiveData<TokenResponse> getToken(final TokenRequest tokenRequest) {

        final MutableLiveData<TokenResponse> tokenResponseMutableLiveData = new MutableLiveData<>();


        loginAPIService = RetrofitClientInstance.getRetrofitInstance().create(LoginAPIService.class);
        Call<TokenResponse> call = loginAPIService.getTokenAccess(tokenRequest);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {

                responseCode.setValue(response.code());

                if(response.isSuccessful())
                {
                    TokenResponse tokenResponse = response.body();

                    if(tokenResponse != null)
                    {
                        tokenResponseMutableLiveData.setValue(tokenResponse);
                    }
                    else
                    {
                        if(response.code()==HttpURLConnection.HTTP_UNAUTHORIZED){

                            tokenResponseMutableLiveData.setValue(null);

                        }else{


                            tokenResponseMutableLiveData.setValue(null);

                        }


                    }

                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {


                tokenResponseMutableLiveData.setValue(null);

            }
        });

        return tokenResponseMutableLiveData;

    }

}
