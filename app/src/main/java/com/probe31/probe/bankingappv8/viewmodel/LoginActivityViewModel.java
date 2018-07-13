package com.probe31.probe.bankingappv8.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.probe31.probe.bankingappv8.model.TokenRequest;
import com.probe31.probe.bankingappv8.model.TokenResponse;
import com.probe31.probe.bankingappv8.repository.LoginRepository;

public class LoginActivityViewModel extends ViewModel{

    private MutableLiveData<TokenResponse> tokenResponse;
    private LoginRepository loginRepository;

    public LoginActivityViewModel(){
        tokenResponse = new MutableLiveData<>();
        tokenResponse.setValue(null);
        loginRepository = new LoginRepository();
    }

    public LiveData<TokenResponse> getTokenResponse()
    {
        return tokenResponse;
    }

    public LiveData<Integer> checkNetwork(){
        return loginRepository.getResponseCode();
    }


    public LiveData<TokenResponse> tryLogin(String username, String password)
    {
        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setUsername(username);
        tokenRequest.setPassword(password);


        tokenResponse = loginRepository.getToken(tokenRequest);

        return tokenResponse;
    }

    public boolean validateDNI(String username)
    {
        return !TextUtils.isEmpty(username) && TextUtils.getTrimmedLength(username)==8;
    }

    public boolean validatePassword(String password)
    {
        return !TextUtils.isEmpty(password);
    }
}
