package com.example.a7tablelayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2;
    TextView txtResult;
    Button submit;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        txtResult = findViewById(R.id.txtResult);
        submit = findViewById(R.id.submit);
        
        submit.setOnClickListener(new View.OnClickListener(){
            
            public void onClick(View v){
                int n1 = Integer.parseInt(num1.getText().toString());
                int n2 = Integer.parseInt(num2.getText().toString());
                int res = n1 + n2;
                txtResult.setText("Result: " + res);
            }
        });
    }

}
