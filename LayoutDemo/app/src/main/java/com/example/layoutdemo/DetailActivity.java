package com.example.layoutdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        FrameLayout container = findViewById(R.id.detailContainer);
        int layoutId = getIntent().getIntExtra("layout_id", -1);

        if (layoutId != -1) {
            View view = LayoutInflater.from(this).inflate(layoutId, container, false);
            container.addView(view);
        }
    }
}
