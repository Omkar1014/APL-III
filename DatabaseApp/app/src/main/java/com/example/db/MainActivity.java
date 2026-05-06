package com.example.db;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.ContentValues;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;

    EditText roll,name;
    Button insert,display;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roll=findViewById(R.id.editText1);
        name=findViewById(R.id.editText2);

        insert=findViewById(R.id.button1);
        display=findViewById(R.id.button2);

        output=findViewById(R.id.textView3);

        db=openOrCreateDatabase("StudentDB",MODE_PRIVATE,null);

        db.execSQL("CREATE TABLE IF NOT EXISTS Temp(id INTEGER,name TEXT)");

        insert.setOnClickListener(v -> insertData());

        display.setOnClickListener(v -> showData());
    }

    private void insertData(){

        ContentValues cv=new ContentValues();

        cv.put("id",roll.getText().toString());
        cv.put("name",name.getText().toString());

        long result=db.insert("Temp",null,cv);

        if(result!=-1)
        {
            Toast.makeText(this,"Student Inserted Successfully",Toast.LENGTH_SHORT).show();

            roll.setText("");
            name.setText("");
        }
        else
        {
            Toast.makeText(this,"Insert Failed",Toast.LENGTH_SHORT).show();
        }

    }

    private void showData(){

        Cursor c=db.rawQuery("SELECT * FROM Temp",null);

        StringBuilder data=new StringBuilder();

        while(c.moveToNext())
        {
            data.append("Roll No : ")
                    .append(c.getString(0))
                    .append("\nName : ")
                    .append(c.getString(1))
                    .append("\n\n");
        }

        output.setText(data.toString());

        c.close();
    }

}