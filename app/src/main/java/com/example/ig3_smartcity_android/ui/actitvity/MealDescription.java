package com.example.ig3_smartcity_android.ui.actitvity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.example.ig3_smartcity_android.R;

public class MealDescription extends AppCompatActivity {

    private TextView mealName,description, price,publication_date,isAvailable,user,category;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_description);
        this.intent = getIntent();
        mealName = (TextView) findViewById(R.id.mealName);
        description =(TextView) findViewById(R.id.description);
        price =(TextView) findViewById(R.id.price);
        publication_date =(TextView) findViewById(R.id.publication_date);
        isAvailable =(TextView) findViewById(R.id.isAvailable);
        user =(TextView) findViewById(R.id.user);
        category =(TextView) findViewById(R.id.category);

        setText(mealName,intent.getStringExtra("name"));
        setText(description,intent.getStringExtra("description"));
        setText(price,intent.getStringExtra("price "));
        setText(publication_date,intent.getStringExtra("publication_date"));
        setText(isAvailable,intent.getStringExtra("isAvailable"));
        setText(user,intent.getStringExtra("user"));
        setText(category,intent.getStringExtra("category"));
    }

    public void setText(TextView view,String text){
        view.setText(text);
    }
}