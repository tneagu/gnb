package com.tneagu.gnb.network;

import com.tneagu.gnb.objects.Rate;
import com.tneagu.gnb.objects.Transaction;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by neagu on 24/04/2018.
 */

public interface ApiService {

    @GET("/rates.json")
    Call<ArrayList<Rate>> getRates();

    @GET("/transactions.json")
    Call<ArrayList<Transaction>> getTransactions();

}