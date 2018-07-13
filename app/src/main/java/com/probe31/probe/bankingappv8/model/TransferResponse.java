package com.probe31.probe.bankingappv8.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransferResponse {

    @SerializedName("dateTime")
    @Expose
    private String dateTime;

    @SerializedName("ownerOtherAccount")
    @Expose
    private String ownerOtherAccount;


    public String getDateTime() {
        return dateTime;
    }

    public String getOwnerOtherAccount() {
        return ownerOtherAccount;
    }
}
