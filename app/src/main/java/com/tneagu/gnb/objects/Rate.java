package com.tneagu.gnb.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by neagu on 24/04/2018.
 */

public class Rate {

    @SerializedName("from")
    private String from;
    @SerializedName("to")
    private String to;
    @SerializedName("rate")
    private double rate;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
