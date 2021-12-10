package com.example.ig3_smartcity_android.ui.actitvity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ig3_smartcity_android.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(R.string.header_text);
    }
}