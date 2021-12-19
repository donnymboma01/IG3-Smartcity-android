package com.example.ig3_smartcity_android.ui.actitvity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.ig3_smartcity_android.R;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIMEOUT = 2000; //millisecondes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //tout l'ecran
        setContentView(R.layout.activity_splash);

        Animation fadeOut = new AlphaAnimation(1,0);
        //fadeOut.getInterpolator(new AccelerateInterpolator()); -->cette facon ne marche plus en Android.
        fadeOut.setStartOffset(500);
        fadeOut.setDuration(1800);
        ImageView imageView = findViewById(R.id.imageSplash);
        imageView.setAnimation(fadeOut);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN_TIMEOUT);
    }
}