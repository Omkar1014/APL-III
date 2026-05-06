package com.example.layoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listView = findViewById(R.id.listView);

        String[] layoutNames = {
                "Linear Layout",
                "Table Layout",
                "Grid Layout",
                "Frame Layout"
        };

        String[] layoutDescriptions = {
                "Organizes elements in a single row or column.",
                "Arranges elements into rows and columns like a table.",
                "Displays items in a flexible grid structure.",
                "Stacks elements on top of each other."
        };

        int[] layoutIds = {
                R.layout.linear,
                R.layout.table,
                R.layout.grid,
                R.layout.frame
        };

        LayoutAdapter adapter = new LayoutAdapter(this, layoutNames, layoutDescriptions);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ListActivity.this, DetailActivity.class);
            intent.putExtra("layout_id", layoutIds[position]);
            startActivity(intent);
        });
    }
}
