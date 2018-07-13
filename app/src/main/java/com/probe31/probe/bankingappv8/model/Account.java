package com.probe31.probe.bankingappv8.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account
{
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("balance")
    @Expose
    private float balance;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public float getBalance() {
        return balance;
    }
}
