package com.tneagu.gnb.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.tneagu.gnb.R;
import com.tneagu.gnb.objects.AppData;
import com.tneagu.gnb.objects.Transaction;
import com.tneagu.gnb.presenters.RatesPresenter;
import com.tneagu.gnb.presenters.interfaces.IRatesCallback;
import com.tneagu.gnb.ui.adapters.TransactionsAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by neagu on 29/04/2018.
 */

@EActivity(R.layout.activity_product_details)
public class ProductDetails extends Activity implements IRatesCallback {
    private static final String TAG = "ProductDetails";
    private static final String KEY_PRODUCT = "";

    @Extra(KEY_PRODUCT)
    String sku;
    @ViewById(R.id.transactions_recycler_view)
    RecyclerView recyclerView;
    @ViewById(R.id.tv_sum)
    TextView sum;
    @Bean(RatesPresenter.class)
    RatesPresenter ratesPresenter;

    private ArrayList<Transaction> transactions;
    private TransactionsAdapter adapter;

    @AfterViews
    public void init(){
        Log.v(TAG, "Opened activity for product... " + sku);
        ratesPresenter.setCallback(this);
        ratesPresenter.getRates();
        setupRecyclerView();
    }

    /*
    CALLBACKS
     */
    @Override
    public void onGetRatesSuccess() {
        calculateSum();
    }


    @Override
    public void onGetRatesFailed() {

    }


    /*
     PUBLIC IMPLEMENTATION
     */
    public static void openActivity(Context context, String sku){
        ProductDetails_.intent(context).sku(sku).start();
    }

    @UiThread
    public void updateSumOnUi(double calculatedSum){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        sum.setText("TOTAL SUM: " + df.format(calculatedSum) + " EUR");
    }

    @Background
    public void calculateSum() {
        double calculatedSum = 1;
        for(Transaction t: transactions){
            if(t.getCurrency().equals("EUR")){
                calculatedSum = calculatedSum + t.getAmount();
            }else{
                double transactionRate = AppData.getInstance().getTransactionRate(t.getCurrency(), "EUR");
                calculatedSum = calculatedSum + t.getAmount() * transactionRate;
            }
        }

        updateSumOnUi(calculatedSum);
    }

    /*
    PRIVATE IMPLEMENTATION
     */
    private void setupRecyclerView() {
        adapter = new TransactionsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        transactions = AppData.getInstance().getAllTransactionofType(sku);
        adapter.setItems(transactions);
    }



}
