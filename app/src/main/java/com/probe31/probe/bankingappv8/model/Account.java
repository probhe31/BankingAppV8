package com.probe31.probe.bankingappv8.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account
{
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("amount")
    @Expose
    private float amount;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public float getAmount() {
        return amount;
    }
}
