package com.tneagu.gnb.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tneagu.gnb.R;
import com.tneagu.gnb.objects.Transaction;
import com.tneagu.gnb.ui.OnItemClick;

import java.util.ArrayList;

/**
 * Created by neagu on 29/04/2018.
 */

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.ViewHolder> {

    ArrayList<Transaction> items;

    public void setItems(ArrayList<Transaction>  items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public TransactionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaction, parent, false);

        return new TransactionsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TransactionsAdapter.ViewHolder holder, final int position) {
        holder.onBind(items.get(position));
    }

    @Override
    public int getItemCount() {
        if(items == null){
            return 0;
        }else{
            return items.size();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sku;
        TextView amount;
        TextView currency;

        public ViewHolder(View itemView) {
            super(itemView);
            sku = itemView.findViewById(R.id.tv_sku);
            amount = itemView.findViewById(R.id.tv_amount);
            currency = itemView.findViewById(R.id.tv_currency);
        }


        public void onBind(Transaction t){
            sku.setText(t.getSku());
            amount.setText(String.valueOf(t.getAmount()));
            currency.setText(t.getCurrency());
        }
    }
}
