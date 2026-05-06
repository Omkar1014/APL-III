package com.example.uicomponents;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner mySpinner;
    Button btnAlert, btnPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySpinner = findViewById(R.id.mySpinner);
        btnAlert = findViewById(R.id.btnAlert);
        btnPopup = findViewById(R.id.btnPopup);

        // Spinner Setup
        String[] items = {"Item 1", "Item 2", "Item 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        mySpinner.setAdapter(adapter);

        mySpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Selected: " + items[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) { }
        });

        // AlertDialog Button
        btnAlert.setOnClickListener(v -> {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Alert Dialog")
                    .setMessage("This is a simple alert dialog.")
                    .setPositiveButton("OK", (dialog, which) -> Toast.makeText(MainActivity.this, "OK clicked", Toast.LENGTH_SHORT).show())
                    .setNegativeButton("Cancel", (dialog, which) -> Toast.makeText(MainActivity.this, "Cancel clicked", Toast.LENGTH_SHORT).show())
                    .show();
        });

        // Popup Menu Button
        btnPopup.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(MainActivity.this, btnPopup);
            popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                Toast.makeText(MainActivity.this, item.getTitle() + " clicked", Toast.LENGTH_SHORT).show();
                return true;
            });
            popup.show();
        });
    }

    // Options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle() + " selected", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}