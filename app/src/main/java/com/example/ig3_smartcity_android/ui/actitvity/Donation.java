package com.example.ig3_smartcity_android.ui.actitvity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.ig3_smartcity_android.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Donation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.donation);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        return true;
    }
}