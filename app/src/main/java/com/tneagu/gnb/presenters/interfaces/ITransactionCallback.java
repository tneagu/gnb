package com.tneagu.gnb.presenters.interfaces;

import com.tneagu.gnb.objects.Rate;
import com.tneagu.gnb.objects.Transaction;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by neagu on 24/04/2018.
 */

public interface ITransactionCallback {

    void onTransactionsSuccess(ArrayList<String> products);
    void onTrasactionsFailed(String message);

}
