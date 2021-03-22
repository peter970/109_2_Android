package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;
    public static final String mShowtext = "";
    public static final String counter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.count_initial_value);
    }

    /*
     * Shows a Toast when the TOAST button is clicked.
     *
     * @param view The view that triggered this onClick handler.
     *             Since a toast always shows on the top,
     *             the passed in view is not used.
     */
    /*
     * Increments the number in the TextView when the COUNT button is clicked.
     *
     * @param view The view that triggered this onClick handler.
     *             Since the count always appears in the TextView,
     *             the passed in view is not used.
     */
    public void countUp(View view) {
        mCount++;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));
    }

    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        String message = "Hello";
        String count_number = Integer.toString(mCount);
        String text = message + "\n" +count_number;
        intent.putExtra(mShowtext, text);
        intent.setClass(MainActivity.this, MainActivity2.class);
        MainActivity.this.startActivity(intent);
    }

}