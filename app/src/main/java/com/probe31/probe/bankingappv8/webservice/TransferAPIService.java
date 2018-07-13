package com.probe31.probe.bankingappv8.webservice;

import com.probe31.probe.bankingappv8.model.TransferRequest;
import com.probe31.probe.bankingappv8.model.TransferResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TransferAPIService {

    @POST("api/v1/transfers")
    Call<TransferResponse> makeTransfer(@Body TransferRequest transferRequest);


}
