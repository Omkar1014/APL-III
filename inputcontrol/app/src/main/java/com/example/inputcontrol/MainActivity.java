package com.example.inputcontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 101;

    private EditText etInput;
    private Button btnSubmit, btnCamera;
    private SeekBar seekZoom;
    private TextView tvZoomValue;

    // ✅ DatePicker Variables
    private DatePicker datePicker;
    private TextView tvSelectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupEventHandlers();
        setupDatePicker(); // ✅ Added
    }

    private void initializeViews() {
        etInput = findViewById(R.id.etInput);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCamera = findViewById(R.id.btnCamera);
        seekZoom = findViewById(R.id.seekZoom);
        tvZoomValue = findViewById(R.id.tvZoomValue);

        // ✅ Initialize DatePicker
        datePicker = findViewById(R.id.datePicker);
        tvSelectedDate = findViewById(R.id.tvSelectedDate);
    }

    private void setupEventHandlers() {

        // Display Input Event
        btnSubmit.setOnClickListener(v -> {
            String userInput = etInput.getText().toString().trim();
            if (!userInput.isEmpty()) {
                Toast.makeText(this,
                        "Entered Text: " + userInput,
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,
                        "Please enter some text",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // SeekBar Event
        seekZoom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvZoomValue.setText("Zoom Level: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        // Camera Button Event
        btnCamera.setOnClickListener(v -> checkCameraPermission());
    }

    // ✅ DatePicker Setup Method
    private void setupDatePicker() {

        datePicker.init(
                datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth(),
                (view, year, monthOfYear, dayOfMonth) -> {

                    monthOfYear = monthOfYear + 1; // Month starts from 0
                    String selectedDate = dayOfMonth + "/" + monthOfYear + "/" + year;

                    tvSelectedDate.setText("Selected Date: " + selectedDate);
                });
    }

    private void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_REQUEST_CODE);
        } else {
            openCamera();
        }
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }
}