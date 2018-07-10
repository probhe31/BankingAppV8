package com.probe31.probe.bankingappv8.webservice;

import android.content.res.Resources;

import com.probe31.probe.bankingappv8.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    //.baseUrl(Resources.getSystem().getString(R.string.api_url))
                    .baseUrl("http://192.168.1.6:8000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }


}
