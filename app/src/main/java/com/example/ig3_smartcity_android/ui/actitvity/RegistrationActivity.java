package com.example.ig3_smartcity_android.ui.actitvity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.ig3_smartcity_android.R;

public class RegistrationActivity extends AppCompatActivity {

    private EditText firstnameEditText,
            nameEditText,
            pseudoEditText,
            passwordEditText,
            provinceEditText,
            cityEditText,
            addressEditText;
    Button buttonRegister, switchBackToLogin;
    private boolean areAllFieldsChecked = false;

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //pour afficher le bouton retour vers l'activité login.

        firstnameEditText = findViewById(R.id.prenom);
        nameEditText = findViewById(R.id.nom);
        pseudoEditText = findViewById(R.id.pseudo);
        passwordEditText = findViewById(R.id.password);
        provinceEditText = findViewById(R.id.province);
        cityEditText = findViewById(R.id.city);
        addressEditText = findViewById(R.id.adresse);

        buttonRegister = findViewById(R.id.registrationBtnID);
        switchBackToLogin = findViewById(R.id.backToLoginID);

        /*switchBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areAllFieldsChecked = isFormValid();
                if(areAllFieldsChecked){
                    goBackToLogin();
                }
            }
        });*/

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

    /**
     *
     * @return true if all fields are filled and false otherwise.
     */


    public boolean isFormValid(){
        if(firstnameEditText.length()==0){
            firstnameEditText.setError(getResources().getText(R.string.firstname_empty_error));
            return false;
        }
        if(nameEditText.length()==0){
            nameEditText.setError(getResources().getText(R.string.lastname_empty_error));
            return false;
        }
        if(pseudoEditText.length()==0){
            pseudoEditText.setError(getResources().getText(R.string.username_empty_message));
            return true;
        }
        if(passwordEditText.length()==0){
            passwordEditText.setError(getResources().getText(R.string.password_empty_message));
            return false;
        }
        if(provinceEditText.length() == 0){
            provinceEditText.setError(getResources().getText(R.string.province_empty_error));
            return false;
        }
        if(cityEditText.length()==0){
            cityEditText.setError(getResources().getText(R.string.city_empty_error));
            return false;
        }
        if(addressEditText.length()==0){
            addressEditText.setError(getResources().getText(R.string.address_empty_error));
            return false;
        }
        return true;
    }


}