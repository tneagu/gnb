package com.tneagu.gnb.presenters;

import android.util.Log;

import com.tneagu.gnb.network.ApiFactory;
import com.tneagu.gnb.network.ApiService;
import com.tneagu.gnb.objects.AppData;
import com.tneagu.gnb.objects.Rate;
import com.tneagu.gnb.presenters.interfaces.IRatesCallback;
import com.tneagu.gnb.presenters.interfaces.IRatesPresenter;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.Extra;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by neagu on 29/04/2018.
 */

@EBean
public class RatesPresenter implements IRatesPresenter {
    private static final String TAG = "RatesPresenter";
    private IRatesCallback callback;

    public void setCallback(IRatesCallback callback){
        this.callback = callback;
    }

    @Override
    public void getRates() {
        ApiService apiService = ApiFactory.getClient();
        apiService.getRates().enqueue(new Callback<ArrayList<Rate>>() {
            @Override
            public void onResponse(Call<ArrayList<Rate>> call, Response<ArrayList<Rate>> response) {
                ArrayList<Rate> rates = response.body();
                AppData.getInstance().setRates(rates);
                callback.onGetRatesSuccess();
            }

            @Override
            public void onFailure(Call<ArrayList<Rate>> call, Throwable t) {
                Log.e(TAG, "Getting rates failed");
                callback.onGetRatesFailed();
            }
        });
    }
}
