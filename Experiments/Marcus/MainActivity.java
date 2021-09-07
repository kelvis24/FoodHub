package com.example.experimentmarcus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

public class MainActivity extends AppCompatActivity() {
    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner firstspinner = (Spinner) findViewByID(R.id.spinner2);
        ArrayAdapter<String> newadapts = new ArrayAdapter<String>(MainActivity.this,
        android.R.layout.simple_list_item_1, getResources().getStringArray(R.getStringArray(R.array.Foods));

        newadapts.setDropdownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter.(newadapts);
        spinner2.setOnItemSelectedListener(this);

        @Override
        public void noselect(AdapterView<?> parent) {
            system.out.print("Nothing selected, please select");
        }
    
}
