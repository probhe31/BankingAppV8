package com.probe31.probe.bankingappv8.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.probe31.probe.bankingappv8.model.AccountResponse;
import com.probe31.probe.bankingappv8.model.TransferRequest;
import com.probe31.probe.bankingappv8.model.TransferResponse;
import com.probe31.probe.bankingappv8.repository.AccountRepository;
import com.probe31.probe.bankingappv8.repository.TransferRepository;

public class TransferFragmentViewModel extends ViewModel {

    private MutableLiveData<TransferResponse> transferResponse;
    private TransferRepository transferRepository;

    public TransferFragmentViewModel(){
        transferResponse = new MutableLiveData<>();
        transferResponse.setValue(null);
        transferRepository = new TransferRepository();
    }

    public LiveData<TransferResponse> makeTransfer(String token, TransferRequest transferRequest)
    {
        transferResponse = transferRepository.makeTransfer(token, transferRequest);
        return transferResponse;
    }


}
