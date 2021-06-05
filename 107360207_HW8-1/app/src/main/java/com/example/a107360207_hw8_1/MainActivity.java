package com.example.a107360207_hw8_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mLevel = 0;
    private ImageView imageView;
    private TextView  textnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.battery);
        imageView.setImageResource(R.drawable.battery_level);
        textnum = (TextView)findViewById(R.id.textView);
    }

    public void decreaseScore(View view) {
        // Get the ID of the button that was clicked
        mLevel--;
        if(mLevel<0)mLevel=0;
        imageView.setImageLevel(mLevel);
        textnum.setText(String.valueOf(mLevel));
    }

    /**
     * Method that handles the onClick of both the increment buttons
     * @param view The button view that was clicked
     */
    public void increaseScore(View view) {
        //Get the ID of the button that was clicked
        mLevel++;
        if(mLevel>7)mLevel=7;
        imageView.setImageLevel(mLevel);
        textnum.setText(String.valueOf(mLevel));
    }
}