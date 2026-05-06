package com.example.demo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText searchEdt;
    private RecyclerView currenciesRV;
    private ProgressBar loadingPB;
    private Spinner currencySpinner;

    private String selectedCurrency = "USD";
    private ArrayList<CurrencyRVModal> currencyRVModalArrayList;
    private CurrencyRVAdapter currencyRVAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        searchEdt = findViewById(R.id.idEdtSearch);
        currenciesRV = findViewById(R.id.idRVcurrency);
        loadingPB = findViewById(R.id.idPBLoading);
        currencySpinner = findViewById(R.id.idCurrencySpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencySpinner.setAdapter(adapter);

        currencyRVModalArrayList = new ArrayList<>();
        currencyRVAdapter = new CurrencyRVAdapter(currencyRVModalArrayList, this, selectedCurrency);
        currenciesRV.setLayoutManager(new LinearLayoutManager(this));
        currenciesRV.setAdapter(currencyRVAdapter);

        getCurrencyData();

        currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCurrency = parent.getItemAtPosition(position).toString();
                currencyRVAdapter.setSelectedCurrency(selectedCurrency);
                currencyRVModalArrayList.clear();
                getCurrencyData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                filterCurrencies(editable.toString());
            }
        });

        currencyRVAdapter.setOnItemClickListener(new CurrencyRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CurrencyRVModal currencyRVModal) {
                if (currencyRVModal != null) {
                    Intent intent = new Intent(MainActivity.this, CurrencyDetailsActivity.class);
                    intent.putExtra("name", currencyRVModal.getName() != null ? currencyRVModal.getName() : "N/A");
                    intent.putExtra("symbol", currencyRVModal.getSymbol() != null ? currencyRVModal.getSymbol() : "N/A");
                    intent.putExtra("price", currencyRVModal.getPrice());
                    intent.putExtra("change1h", currencyRVModal.getChange1h());
                    intent.putExtra("change24h", currencyRVModal.getChange24h());
                    intent.putExtra("change7d", currencyRVModal.getChange7d());
                    intent.putExtra("change30d", currencyRVModal.getChange30d());
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Error: Invalid data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void filterCurrencies(String currency) {
        ArrayList<CurrencyRVModal> filteredList = new ArrayList<>();
        for (CurrencyRVModal item : currencyRVModalArrayList) {
            if (item.getName().toLowerCase().contains(currency.toLowerCase())) {
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No currency found for search query", Toast.LENGTH_SHORT).show();
        } else {
            currencyRVAdapter.filterList(filteredList);
        }
    }

    private void getCurrencyData() {
        loadingPB.setVisibility(View.VISIBLE);
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?convert=" + selectedCurrency;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loadingPB.setVisibility(View.GONE);
                        try {
                            JSONArray dataArray = response.getJSONArray("data");
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject dataObj = dataArray.getJSONObject(i);
                                String name = dataObj.getString("name");
                                String symbol = dataObj.getString("symbol");
                                String iconUrl = "https://cryptoicons.org/api/icon/" + symbol.toLowerCase() + "/64";


                                JSONObject quote = dataObj.getJSONObject("quote");
                                JSONObject currencyData = quote.getJSONObject(selectedCurrency);
                                double price = currencyData.optDouble("price", 0.0);
                                double change1h = currencyData.optDouble("percent_change_1h", 0.0);
                                double change24h = currencyData.optDouble("percent_change_24h", 0.0);
                                double change7d = currencyData.optDouble("percent_change_7d", 0.0);
                                double change30d = currencyData.optDouble("percent_change_30d", 0.0);

                                CurrencyRVModal currencyRVModal = new CurrencyRVModal(name, symbol, price, change1h, change24h, change7d, change30d,iconUrl);
                                currencyRVModalArrayList.add(currencyRVModal);
                            }
                            currencyRVAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Failed to parse data.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingPB.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Failed to load data.", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("X-CMC_PRO_API_KEY", "281bca87-2a06-4afb-9864-dd0c6e331f79");
                return headers;
            }
        };

        requestQueue.add(jsonObjectRequest);
    }
}
