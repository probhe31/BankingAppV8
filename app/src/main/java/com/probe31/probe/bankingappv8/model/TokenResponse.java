package com.probe31.probe.bankingappv8.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenResponse {

    @SerializedName("customer_id")
    @Expose
    private int customer_id;


    @SerializedName("access_token")
    @Expose
    private String access_token;

    @SerializedName("token_type")
    @Expose
    private String token_type;

    @SerializedName("expires_in")
    @Expose
    private int expires_in;

    @SerializedName("refresh_token")
    @Expose
    private String refresh_token;


    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public int getCustomer_id() {
        return customer_id;
    }
}
