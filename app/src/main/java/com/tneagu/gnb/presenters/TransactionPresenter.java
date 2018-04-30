package com.tneagu.gnb.presenters;

import com.tneagu.gnb.network.ApiFactory;
import com.tneagu.gnb.network.ApiService;
import com.tneagu.gnb.objects.AppData;
import com.tneagu.gnb.objects.Rate;
import com.tneagu.gnb.objects.Transaction;
import com.tneagu.gnb.presenters.interfaces.ITransactionCallback;
import com.tneagu.gnb.presenters.interfaces.ITransactionPresenter;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.HashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by neagu on 24/04/2018.
 */

@EBean
public class TransactionPresenter implements ITransactionPresenter {
    ITransactionCallback callback;


    public void setCallback(ITransactionCallback callback) {
        this.callback = callback;
    }

    @Override
    public void getTransactions() {
        ApiService apiService = ApiFactory.getClient();
        apiService.getTransactions().enqueue(new Callback<ArrayList<Transaction>>() {
            @Override
            public void onResponse(Call<ArrayList<Transaction>> call, Response<ArrayList<Transaction>> response) {
                ArrayList<Transaction> transactions = response.body();
                //save the data to AppData singleton
                AppData.getInstance().setTransactions(transactions);
                //prepare the answer for the UI
                HashSet<String> products = new HashSet<>();
                for(Transaction t : transactions){
                    products.add(t.getSku());
                }
                callback.onTransactionsSuccess(new ArrayList<String>(products));
            }

            @Override
            public void onFailure(Call<ArrayList<Transaction>> call, Throwable t) {
                callback.onTrasactionsFailed(t.getMessage());
            }
        }
        );
    }

}
