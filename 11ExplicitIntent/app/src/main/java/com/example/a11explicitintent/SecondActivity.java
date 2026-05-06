package com.example.a11explicitintent;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView txt = new TextView(this);
        txt.setText("Welcome to Second Activity");
        txt.setTextSize(20);

        setContentView(txt);
    }
}
