package com.tneagu.gnb.objects;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by neagu on 29/04/2018.
 */

public class AppData {
    private static AppData instance = null;
    private ArrayList<Transaction> transactions;
    private ArrayList<Rate> rates;
    private float foundRate;
    private HashSet<String> alreadyVisited;

    private AppData() {
    }

    public static AppData getInstance(){
        if(instance == null){
            instance = new AppData();
        }
        return instance;
    }

    /*
    PUBLIC IMPLENETATION
     */
    public ArrayList<Transaction> getAllTransactionofType(String type){
        ArrayList<Transaction> result = new ArrayList<>();
        if(transactions == null){
            return result;
        }

        for(Transaction t: transactions){
            if(t.getSku().equals(type)){
                result.add(t);
            }
        }

        return result;
    }


    /*
    Get transaction rate between two currencies
     */
    public float getTransactionRate(String from, String to){
        if(alreadyVisited == null){
            alreadyVisited = new HashSet<>();
        }else{
            alreadyVisited.clear();
        }
        foundRate = 1;
        foundRate = getRate(from, to, 1);
        return foundRate;
    }

    /*
    PRIVATE IMPLEMENTATION
     */
    private float getRate(String from, String to, float rate){
        float f = getRateBetween(from, to);
        if(f != 0){//we have a route
            return rate * f;
        }else{
            alreadyVisited.add(from);
            for(Rate r : rates){
                if(r.getFrom().equals(from)
                 && !alreadyVisited.contains(r.getTo())){//we search for another transaction departing from this currency
                    foundRate = getRate(r.getTo(), to, rate * r.getRate());
                    if(foundRate != 0){
                        return  foundRate;
                    }
                }
            }
        }

        return 0;
    }


    private float getRateBetween(String from, String to){
        for(Rate r: rates){
            if(r.getFrom().equals(from) && r.getTo().equals(to)){
                return r.getRate();
            }
        }

        return 0;
    }

    /*
    GETTERS AND SETTERS
     */
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public ArrayList<Rate> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Rate> rates) {
        this.rates = rates;
    }
}
