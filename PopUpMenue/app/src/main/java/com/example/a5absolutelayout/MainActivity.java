package com.example.a5absolutelayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText edttext;
    Button btnSubmit;
    TextView txtresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edttext = findViewById(R.id.edttext);
        btnSubmit = findViewById(R.id.btnSubmit);
        txtresult = findViewById(R.id.txtresult);

        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String name = edttext.getText().toString();
                txtresult.setText("Hello " + name);
            }
        });
    }
}
