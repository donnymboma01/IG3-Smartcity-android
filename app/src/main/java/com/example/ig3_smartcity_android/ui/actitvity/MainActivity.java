package com.example.ig3_smartcity_android.ui.actitvity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ig3_smartcity_android.R;
import com.example.ig3_smartcity_android.model.MealToReceive;
import com.example.ig3_smartcity_android.ui.fragment.CartRecyclerViewFragment;
import com.example.ig3_smartcity_android.ui.fragment.DonnationFragment;
import com.example.ig3_smartcity_android.ui.fragment.MealRecycleViewFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    private static ArrayList<MealToReceive> mealsForCart = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout =findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MealRecycleViewFragment()).commit();
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    /**
     * cette fonction permet de naviguer entre les differents fragments .
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_meals:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MealRecycleViewFragment()).commit();
                break;
            case R.id.nav_donnation:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DonnationFragment()).commit();
                break;
            case R.id.nav_cart:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CartRecyclerViewFragment()).commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public static ArrayList<MealToReceive> getMealsForCart(){
        return mealsForCart;
    }
    public static void addMealToCart(MealToReceive mealToAdd){
        mealsForCart.add(mealToAdd);
    }
    public static void resetCart(){
        mealsForCart.clear();
    }
}