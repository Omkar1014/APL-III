package com.example.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class CurrencyDetailsActivity extends AppCompatActivity {

    private TextView currencyNameTV, symbolTV, priceTV, change1hTV, change24hTV, change7dTV, change30dTV;
    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_details);

        currencyNameTV = findViewById(R.id.idTVCurrencyName);
        symbolTV = findViewById(R.id.idTVSymbol);
        priceTV = findViewById(R.id.idTVCurrencyRate);
        change1hTV = findViewById(R.id.idTVChange1h);
        change24hTV = findViewById(R.id.idTVChange24h);
        change7dTV = findViewById(R.id.idTVChange7d);
        change30dTV = findViewById(R.id.idTVChange30d);
        lineChart = findViewById(R.id.idLineChart);

        if (getIntent() != null) {
            String name = getIntent().getStringExtra("name");
            String symbol = getIntent().getStringExtra("symbol");
            double price = getIntent().getDoubleExtra("price", 0.0);
            double change1h = getIntent().getDoubleExtra("change1h", 0.0);
            double change24h = getIntent().getDoubleExtra("change24h", 0.0);
            double change7d = getIntent().getDoubleExtra("change7d", 0.0);
            double change30d = getIntent().getDoubleExtra("change30d", 0.0);

            currencyNameTV.setText("Name: " + name);
            symbolTV.setText("Symbol: " + symbol);
            priceTV.setText("Price: $" + price);
            change1hTV.setText(" 1h Change: " + change1h + "%");
            change24hTV.setText(" 24h Change: " + change24h + "%");
            change7dTV.setText(" 7d Change: " + change7d + "%");
            change30dTV.setText(" 30d Change: " + change30d + "%");

            setUpLineChart(change1h, change24h, change7d, change30d);
        }
    }

    private void setUpLineChart(double change1h, double change24h, double change7d, double change30d) {
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, (float) change30d));
        entries.add(new Entry(2, (float) change7d));
        entries.add(new Entry(3, (float) change24h));
        entries.add(new Entry(4, (float) change1h));

        LineDataSet lineDataSet = new LineDataSet(entries, "Price Change (%)");
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawValues(true);
        lineDataSet.setColor(Color.MAGENTA);
        lineDataSet.setCircleColor(Color.RED);
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setValueTextColor(Color.WHITE);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        Description description = new Description();
        description.setText("");
        lineChart.setDescription(description);

        lineChart.setTouchEnabled(true);
        lineChart.setPinchZoom(true);
        lineChart.getXAxis().setTextColor(Color.WHITE);
        lineChart.getAxisLeft().setTextColor(Color.WHITE);
        lineChart.getAxisRight().setTextColor(Color.WHITE);
        lineChart.getXAxis().setGridColor(Color.WHITE);
        lineChart.getAxisLeft().setGridColor(Color.WHITE);
        lineChart.getAxisRight().setGridColor(Color.WHITE);
        lineChart.getLegend().setTextColor(Color.WHITE);

        lineChart.invalidate();
        lineChart.animateX(1000); // Animate chart entry
    }
}
