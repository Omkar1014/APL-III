package com.example.explicitintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.buttonSend);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create explicit intent to start SecondActivity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                // Pass data using intent extras
                String message = editText.getText().toString();
                intent.putExtra("MESSAGE_KEY", message);

                startActivity(intent);
            }
        });
    }
}