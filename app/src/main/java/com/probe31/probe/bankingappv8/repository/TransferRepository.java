package com.probe31.probe.bankingappv8.repository;

import android.arch.lifecycle.MutableLiveData;
import com.probe31.probe.bankingappv8.model.TransferRequest;
import com.probe31.probe.bankingappv8.model.TransferResponse;
import com.probe31.probe.bankingappv8.webservice.RetrofitClientInstance;
import com.probe31.probe.bankingappv8.webservice.TransferAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferRepository {

    private TransferAPIService transferAPIService;

    public MutableLiveData<TransferResponse> makeTransfer(final String token, final TransferRequest transferRequest) {

        final MutableLiveData<TransferResponse> transferResponseMutableLiveData = new MutableLiveData<>();

        transferAPIService = RetrofitClientInstance.getRetrofitInstance(token).create(TransferAPIService.class);

        Call<TransferResponse> call = transferAPIService.makeTransfer(transferRequest);

        call.enqueue(new Callback<TransferResponse>() {
            @Override
            public void onResponse(Call<TransferResponse> call, Response<TransferResponse> response) {

                TransferResponse transferResponse = response.body();

                if(transferResponse != null)
                {

                    transferResponseMutableLiveData.setValue(transferResponse);
                }
                else
                {
                    transferResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<TransferResponse> call, Throwable t) {

                transferResponseMutableLiveData.setValue(null);

            }
        });

        return transferResponseMutableLiveData;

    }

}
