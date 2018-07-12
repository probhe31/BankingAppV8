package com.probe31.probe.bankingappv8.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccountResponse
{
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("firstName")
    @Expose
    private String firstName;

    @SerializedName("lastName")
    @Expose
    private String lastName;

    @SerializedName("documentNumber")
    @Expose
    private String documentNumber;

    @SerializedName("bankAccounts")
    @Expose
    private List<Account> bankAccounts;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public List<Account> getBankAccounts() {
        return bankAccounts;
    }
}
