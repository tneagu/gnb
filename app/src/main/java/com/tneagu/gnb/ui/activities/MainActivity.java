package com.tneagu.gnb.ui.activities;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.tneagu.gnb.R;
import com.tneagu.gnb.presenters.RatesPresenter;
import com.tneagu.gnb.presenters.TransactionPresenter;
import com.tneagu.gnb.presenters.interfaces.ITransactionCallback;
import com.tneagu.gnb.ui.OnItemClick;
import com.tneagu.gnb.ui.adapters.MainAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by neagu on 24/04/2018.
 */

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity implements ITransactionCallback, OnItemClick {
    private static final String TAG = "MainActivity";

    @ViewById(R.id.products_recycler_view)
    RecyclerView recyclerView;
    private MainAdapter adapter;
    @Bean(TransactionPresenter.class)
    TransactionPresenter transactionPresenter;


    @AfterViews
    public void init(){
        transactionPresenter.setCallback(this);
        setupRecyclerView();
        transactionPresenter.getTransactions();
    }


    /*
    CALLBACKS
     */

    @Override
    public void onTransactionsSuccess(ArrayList<String> products) {
        Log.v(TAG, "onTransactionsSuccess...");
        adapter.setItems(products);
    }

    @Override
    public void onTrasactionsFailed(String message) {
        Log.e(TAG, "onTransactionsFailed...");
    }


    @Override
    public void onItemClick(String s) {
        ProductDetails.openActivity(this, s);
    }
    /*
        PRIVATE IMPLEMENTATION
         */
    private void setupRecyclerView() {
        adapter = new MainAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }



}
