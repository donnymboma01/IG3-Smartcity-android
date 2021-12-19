package com.example.ig3_smartcity_android.ui.actitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ig3_smartcity_android.R;
import com.example.ig3_smartcity_android.model.NetworkError;
import com.example.ig3_smartcity_android.model.User;
import com.example.ig3_smartcity_android.ui.viewModel.RegistrationViewModel;

public class RegistrationActivity extends AppCompatActivity {

    private EditText firstnameEditText,
            nameEditText,
            pseudoEditText,
            passwordEditText,
            provinceEditText,
            cityEditText,
            addressEditText,
            telephoneText;

    Button buttonRegister;
    private boolean areAllFieldsChecked = false;
    private SharedPreferences sharedPreferences;
    private RegistrationViewModel registrationViewModel;
    private boolean isUserRegistred = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        sharedPreferences = getSharedPreferences(getString(R.string.sharedPref), Context.MODE_PRIVATE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //pour afficher le bouton retour vers l'activité login.
        getSupportActionBar().setTitle(R.string.inscription);
        registrationViewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);

        firstnameEditText = findViewById(R.id.prenom);
        nameEditText = findViewById(R.id.nom);
        pseudoEditText = findViewById(R.id.pseudo);
        passwordEditText = findViewById(R.id.password);
        provinceEditText = findViewById(R.id.province);
        cityEditText = findViewById(R.id.city);
        addressEditText = findViewById(R.id.adresse);
        buttonRegister = findViewById(R.id.registrationBtnID);
        telephoneText = findViewById(R.id.telephone);

        //l'utilisateur n'est enregistré que si tous les champs du formulaire sont remplis.
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areAllFieldsChecked = isFormValid();
                if(areAllFieldsChecked){
                    registerUser();
                    resetFormAfterRegister();
                }
            }
        });

        registrationViewModel.getError().observe(this,networkError -> {
            showError(networkError);
            if(isUserRegistred){
                Toast.makeText(this,R.string.registration_message,Toast.LENGTH_LONG).show();
            }
        });

    }

    /**
     * allows to have an icon so that we can move back to the previous activity
     * @param item
     * @return
     */

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
     * Checks if all fields have values
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
        if(telephoneText.length() == 0){
            telephoneText.setError(getResources().getText(R.string.phone_error));
            return false;
        }
        return true;
    }

    /**
     * this method allows to reset the form after a new user has been registered.
     */
    public void resetFormAfterRegister(){
        firstnameEditText.getText().clear();
        nameEditText.getText().clear();
        pseudoEditText.getText().clear();
        passwordEditText.getText().clear();
        provinceEditText.getText().clear();
        cityEditText.getText().clear();
        cityEditText.getText().clear();
        telephoneText.getText().clear();
        addressEditText.getText().clear();
    }

    public void showError(NetworkError networkError){
        switch (networkError){
            case NO_CONNECTION_ERROR:
                Toast.makeText(this,R.string.connection_error,Toast.LENGTH_LONG).show();
                break;
            case TECHNICAL_ERROR:
                Toast.makeText(this,R.string.technical_error,Toast.LENGTH_LONG).show();
                break;
            case USER_ALREADY_EXIST:
                Toast.makeText(this,R.string.user_exists,Toast.LENGTH_LONG).show();
                break;
            case REQUEST_ERROR:
                Toast.makeText(this,R.string.request_error,Toast.LENGTH_LONG).show();
                break;
            default:
                isUserRegistred = true;
        }
    }


    /**
     * this method allows to register a new user to the database.
     */
    public void registerUser(){
        User user = new User(
                firstnameEditText.getText().toString(),
                nameEditText.getText().toString(),
                telephoneText.getText().toString(),
                pseudoEditText.getText().toString(),
                passwordEditText.getText().toString(),
                provinceEditText.getText().toString(),
                cityEditText.getText().toString(),
                addressEditText.getText().toString()

        );
        registrationViewModel.registerUser(user);
    }

}