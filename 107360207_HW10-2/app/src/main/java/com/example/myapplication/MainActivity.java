package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.net.URL;


public class MainActivity extends AppCompatActivity {


    private TextView eTextView;
    private TextView sTextView;
    private Spinner  spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTextView = findViewById(R.id.editTextTextPersonName);
        sTextView = findViewById(R.id.textView3);
        spinner = findViewById(R.id.spinner2);

        final String[] lunch = {"http://", "https://"};
        ArrayAdapter<String> lunchList = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                lunch);spinner.setAdapter(lunchList);

    }

    public void getData (View view){
        String url = spinner + eTextView.getText().toString();
        Toast.makeText(this,url,Toast.LENGTH_LONG).show();
        Ion.with(getApplicationContext()).load(url).asString().setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {
                sTextView.setText(result);
            }
        });
    }



}