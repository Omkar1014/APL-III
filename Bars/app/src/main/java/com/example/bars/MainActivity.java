package com.example.bars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RatingBar ratingBar;
    Button btnShowRating, btnIncreaseProgress;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratingBar = findViewById(R.id.ratingBar);
        btnShowRating = findViewById(R.id.btnShowRating);
        progressBar = findViewById(R.id.progressBar);
        btnIncreaseProgress = findViewById(R.id.btnIncreaseProgress);

        // RatingBar button
        btnShowRating.setOnClickListener(v -> {
            float rating = ratingBar.getRating();
            Toast.makeText(MainActivity.this, "Rating: " + rating, Toast.LENGTH_SHORT).show();
        });

        // RatingBar change listener
        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            if (fromUser) {
                Toast.makeText(MainActivity.this, "Rating Changed: " + rating, Toast.LENGTH_SHORT).show();
            }
        });

        // ProgressBar button
        btnIncreaseProgress.setOnClickListener(v -> {
            int progress = progressBar.getProgress();
            if (progress < progressBar.getMax()) {
                progress += 10;
                progressBar.setProgress(progress);
                Toast.makeText(MainActivity.this, "Progress: " + progress + "%", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Progress is full!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}