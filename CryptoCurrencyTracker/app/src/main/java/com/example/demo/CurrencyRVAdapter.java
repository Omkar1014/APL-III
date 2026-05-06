package com.example.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CurrencyRVAdapter extends RecyclerView.Adapter<CurrencyRVAdapter.ViewHolder> {
    private ArrayList<CurrencyRVModal> currencyRVModalArrayList;
    private Context context;
    private String selectedCurrency = "USD";
    private static final DecimalFormat df2 = new DecimalFormat("#.##");

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(CurrencyRVModal currencyRVModal);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public CurrencyRVAdapter(ArrayList<CurrencyRVModal> currencyRVModalArrayList, Context context, String selectedCurrency) {
        this.currencyRVModalArrayList = currencyRVModalArrayList;
        this.context = context;
        this.selectedCurrency = selectedCurrency;
    }

    public void filterList(ArrayList<CurrencyRVModal> filteredList) {
        currencyRVModalArrayList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CurrencyRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.currency_rv_item, parent, false);
        return new CurrencyRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyRVAdapter.ViewHolder holder, int position) {
        CurrencyRVModal currencyRVModal = currencyRVModalArrayList.get(position);
        holder.currencyNameTV.setText(currencyRVModal.getName());
        holder.symbolTV.setText(currencyRVModal.getSymbol());

        String symbol = getCurrencySymbol(selectedCurrency);
        holder.rateTV.setText(symbol + " " + df2.format(currencyRVModal.getPrice()));

        // Item click listener
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(currencyRVModal);
            }
        });
    }

    public void setSelectedCurrency(String currency) {
        this.selectedCurrency = currency;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return currencyRVModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView currencyNameTV, symbolTV, rateTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            currencyNameTV = itemView.findViewById(R.id.idTVCurrencyName);
            symbolTV = itemView.findViewById(R.id.idTVSymbol);
            rateTV = itemView.findViewById(R.id.idTVCurrencyRate);
        }
    }

    private String getCurrencySymbol(String currencyCode) {
        switch (currencyCode) {
            case "USD":
                return "$";
            case "INR":
                return "₹";
            case "EUR":
                return "€";
            case "GBP":
                return "£";
            default:
                return currencyCode + " ";
        }
    }
}
