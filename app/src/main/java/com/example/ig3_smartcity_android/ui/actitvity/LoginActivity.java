package com.example.ig3_smartcity_android.ui.actitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ig3_smartcity_android.R;
import com.example.ig3_smartcity_android.model.LoginUser;
import com.example.ig3_smartcity_android.model.Token;
import com.example.ig3_smartcity_android.repositories.configuration.RetrofitConfigurationService;
import com.example.ig3_smartcity_android.ui.fragment.MealRecycleViewFragment;
import com.example.ig3_smartcity_android.ui.viewModel.LoginUserViewModel;

import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameText, passwordText;
    private Button buttonLogin;
    private Button switchToRegisterActivity;
    private LoginUserViewModel loginUserViewModel;
    private boolean areAllFieldsChecked = false;
    private SharedPreferences sharedPreferences;
    private Token token;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle(R.string.connexion);

        sharedPreferences = getSharedPreferences(getString(R.string.sharedPref),Context.MODE_PRIVATE);

        usernameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.loginButtonID);
        loginUserViewModel = new  ViewModelProvider(this).get(LoginUserViewModel.class);
        switchToRegisterActivity = findViewById(R.id.signupID);



        /*buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areAllFieldsChecked = areFiledsNotEmpty();
                if(areAllFieldsChecked){
                    login();
                    goToMainActivity();
                }
            }

        });*/

       // loginUserViewModel.getJwt().observe();

        switchToRegisterActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterActivity();
            }
        });
    }

    /**
     *
     * @return true if all fields are filled and false otherwise.
     */

    public boolean areFiledsNotEmpty(){
        if(usernameText.length() == 0){
            usernameText.setError(getResources().getText(R.string.username_empty_message));
            return false;
        }
        if(passwordText.length() == 0){
            passwordText.setError(getResources().getText(R.string.password_empty_message));
            return false;
        }
        return true;
    }



    public void goToRegisterActivity(){
        Intent switchToRegister = new Intent(this,RegistrationActivity.class);
        startActivity(switchToRegister);
    }
}