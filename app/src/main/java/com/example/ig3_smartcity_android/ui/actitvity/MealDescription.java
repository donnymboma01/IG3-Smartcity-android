package com.example.ig3_smartcity_android.ui.actitvity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ig3_smartcity_android.R;
import com.example.ig3_smartcity_android.repositories.configuration.RetrofitConfigurationService;

public class MealDescription extends AppCompatActivity {

    private TextView mealName,description, portion_number,publication_date,isAvailable,user,category;
    private ImageView mealImage;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_description);

        //affiche l'option retour vers la liste(recycleView)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.intent = getIntent();

        mealImage = (ImageView)findViewById(R.id.mealImageId);
        mealName = (TextView) findViewById(R.id.mealName);
        description =(TextView) findViewById(R.id.description);
        portion_number =(TextView) findViewById(R.id.portion_number_id);

        setText(mealName,intent.getStringExtra("name"));
        setText(description,intent.getStringExtra("description"));
        setText(portion_number,String.valueOf(intent.getIntExtra("portion_number",0)));
        Glide.with(getApplicationContext()).load(RetrofitConfigurationService.BASE_URL + "mealimages/" + intent.getStringExtra("image")).into(mealImage);
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

    public void setText(TextView view,String text){
        view.setText(text);
    }


}