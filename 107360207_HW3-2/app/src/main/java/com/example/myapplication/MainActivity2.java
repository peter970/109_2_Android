package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.mShowtext);
        // Put that message into the text_message TextView
        TextView textView = findViewById(R.id.show_text);
        textView.setText(message);

        //String count_number = intent.getStringExtra(MainActivity.counter);
        // Put that message into the text_message TextView
        //TextView textView2 = findViewById(R.id.show_count);
        //textView2.setText(count_number);
    }
}