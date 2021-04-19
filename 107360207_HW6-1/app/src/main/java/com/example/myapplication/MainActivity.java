package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayToast(StringBuffer toppings) {
        Toast.makeText(getApplicationContext(), toppings,
                Toast.LENGTH_SHORT).show();
    }

    public void onSubmit(View view) {
        StringBuffer toppings = new
                StringBuffer().append(getString(R.string.toppings_label));
        if  (((CheckBox) findViewById(R.id.checkBox1)).isChecked()) {
            toppings.append(getString(R.string.checkbox1));
        }
        if  (((CheckBox) findViewById(R.id.checkBox2)).isChecked()) {
            toppings.append(getString(R.string.checkbox1));
        }
        if  (((CheckBox) findViewById(R.id.checkBox3)).isChecked()) {
            toppings.append(getString(R.string.checkbox3));
        }
        if  (((CheckBox) findViewById(R.id.checkBox4)).isChecked()) {
            toppings.append(getString(R.string.checkbox4));
        }
        if  (((CheckBox) findViewById(R.id.checkBox5)).isChecked()) {
            toppings.append(getString(R.string.checkbox5));
        }
// Code to display the result...
        displayToast(toppings);
    }


}