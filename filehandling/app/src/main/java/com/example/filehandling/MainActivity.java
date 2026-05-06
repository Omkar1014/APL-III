package com.example.filehandling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView result;

    Button btnSave, btnLoad, btnClear;

    String fileName = "mydata.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        result = findViewById(R.id.textViewResult);

        btnSave = findViewById(R.id.btnSave);
        btnLoad = findViewById(R.id.btnLoad);
        btnClear = findViewById(R.id.btnClear);

        // SAVE DATA
        btnSave.setOnClickListener(view -> {

            String data = editText.getText().toString();

            if(data.isEmpty()){
                Toast.makeText(this,"Enter some text",Toast.LENGTH_SHORT).show();
                return;
            }

            try {

                FileOutputStream fout = openFileOutput(fileName, MODE_APPEND);

                fout.write((data + "\n").getBytes());

                fout.close();

                Toast.makeText(this,"Data Saved Successfully",Toast.LENGTH_SHORT).show();

                editText.setText("");

            } catch (Exception e) {
                e.printStackTrace();
            }

        });


        // LOAD DATA
        btnLoad.setOnClickListener(view -> {

            try {

                FileInputStream fin = openFileInput(fileName);

                int c;
                String temp = "";

                while((c = fin.read()) != -1){

                    temp = temp + Character.toString((char)c);

                }

                result.setText(temp);

                Toast.makeText(this,"Data Loaded",Toast.LENGTH_SHORT).show();

                fin.close();

            } catch (Exception e) {

                result.setText("No Data Found");

            }

        });


        // CLEAR TEXT
        btnClear.setOnClickListener(view -> {

            result.setText("");

            Toast.makeText(this,"Screen Cleared",Toast.LENGTH_SHORT).show();

        });

    }
}