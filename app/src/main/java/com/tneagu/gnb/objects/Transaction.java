package com.tneagu.gnb.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by neagu on 24/04/2018.
 */

public class Transaction {

    @SerializedName("sku")
    private String sku;
    @SerializedName("amount")
    private double amount;
    @SerializedName("currency")
    private String currency;

    public String getSku() {
        return sku;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
