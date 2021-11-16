package com.example.ig3_smartcity_android.ui.actitvity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    Button buttonRegister;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firstnameEditText = findViewById(R.id.prenom);
        nameEditText = findViewById(R.id.nom);
        pseudoEditText = findViewById(R.id.pseudo);
        passwordEditText = findViewById(R.id.password);
        provinceEditText = findViewById(R.id.province);
        cityEditText = findViewById(R.id.city);
        addressEditText = findViewById(R.id.adresse);

        buttonRegister = findViewById(R.id.registrationBtnID);

    }
}