package com.example.a4relativelayout;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a4relativelayout.R;

public class MainActivity extends AppCompatActivity {
    EditText edttext;
    TextView txtresult;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edttext = findViewById(R.id.edttext);
        txtresult = findViewById(R.id.txtresult);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edttext.getText().toString();
                txtresult.setText("Hello "+userName);
            }
        });
    }


}
