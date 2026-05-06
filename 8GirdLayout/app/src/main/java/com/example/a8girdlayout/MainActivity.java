package com.example.a8girdlayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

        EditText num1, num2;
        Button btnSubmit;
        TextView txtResult;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            num1 = findViewById(R.id.num1);
            num2 = findViewById(R.id.num2);
            btnSubmit = findViewById(R.id.btnSubmit);
            txtResult = findViewById(R.id.txtResult);

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int a = Integer.parseInt(num1.getText().toString());
                    int b = Integer.parseInt(num2.getText().toString());

                    txtResult.setText("Sum = " + (a + b));
                }
            });
    }
}
