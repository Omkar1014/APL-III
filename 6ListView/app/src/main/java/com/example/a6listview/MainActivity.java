package com.example.a6listview;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ListView listView ;
    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        txtResult = findViewById(R.id.txtResult);

        String[] names = {"Aniket", "Rahul", "Amit", "Priya", "Sneha"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                names
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id)->{
            String selected = names[position];
            txtResult.setText("Selected: " + selected);
        });
    }
}
