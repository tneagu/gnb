package com.tneagu.gnb.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by neagu on 24/04/2018.
 */

public class ApiFactory {
    public static final String BASE_URL = "http://gnb.dev.airtouchmedia.com";

    public static ApiService getClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }
}
