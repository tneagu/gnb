package com.tneagu.gnb.network;

import com.tneagu.gnb.objects.Rate;
import com.tneagu.gnb.objects.Transaction;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by neagu on 24/04/2018.
 */

public interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("/rates.json")
    Call<ArrayList<Rate>> getRates();

    @Headers("Content-Type: application/json")
    @GET("/transactions.json")
    Call<ArrayList<Transaction>> getTransactions();

}