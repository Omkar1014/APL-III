// File: MainActivity.java
package com.example.databasedemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etID, etName, etEmail;
    Button btnAdd, btnView, btnUpdate, btnDelete;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);

        etID = findViewById(R.id.etID);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);

        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        // Insert
        btnAdd.setOnClickListener(v -> {
            boolean inserted = db.insertData(etName.getText().toString(), etEmail.getText().toString());
            if(inserted)
                Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "Insertion Failed", Toast.LENGTH_SHORT).show();
        });

        // View
        btnView.setOnClickListener(v -> {
            Cursor res = db.getAllData();
            if(res.getCount() == 0) {
                showMessage("Error","No Data Found");
                return;
            }

            StringBuilder buffer = new StringBuilder();
            while(res.moveToNext()){
                buffer.append("ID: ").append(res.getString(0)).append("\n");
                buffer.append("Name: ").append(res.getString(1)).append("\n");
                buffer.append("Email: ").append(res.getString(2)).append("\n\n");
            }
            showMessage("Data", buffer.toString());
        });

        // Update
        btnUpdate.setOnClickListener(v -> {
            boolean updated = db.updateData(etID.getText().toString(),
                    etName.getText().toString(),
                    etEmail.getText().toString());
            if(updated)
                Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
        });

        // Delete
        btnDelete.setOnClickListener(v -> {
            boolean deleted = db.deleteData(etID.getText().toString());
            if(deleted)
                Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "Delete Failed", Toast.LENGTH_SHORT).show();
        });
    }

    private void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}