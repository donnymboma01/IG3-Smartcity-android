package com.example.ig3_smartcity_android.ui.actitvity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ig3_smartcity_android.R;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameText, passwordText;
    private Button buttonLogin;
    private Button switchToSecondActivity;
    private TextView textWarnUsername, textWarnPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.loginButtonID);
        switchToSecondActivity = findViewById(R.id.signupID);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(isFormVlaid()){
                    String username = usernameText.getText().toString();
                    String password = passwordText.getText().toString();
                    Toast.makeText(LoginActivity.this,getResources().getText(R.string.bonjour)+" "+username,Toast.LENGTH_LONG).show();

                //Toast.makeText(LoginActivity.this,getResources().getText(R.string.connection_message),Toast.LENGTH_LONG).show();
            }
        });


        switchToSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(LoginActivity.this,getResources().getText(R.string.register_page_message),Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP|Gravity.CENTER,20,30);
                toast.show();
                goToRegisterActivity();
            }
        });
    }

    public boolean checkFields(){
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

    public boolean isFormVlaid(){
        if(usernameText.getText().toString().equals("")){
            textWarnUsername.setVisibility(View.VISIBLE);
            textWarnUsername.setText("Entrez votre pseudo");
            return false;
        }
        if(passwordText.getText().toString().equals("")){
            textWarnPassword.setVisibility(View.VISIBLE);
            textWarnPassword.setText("Entre votre mot de passe");
            return false;
        }
        return true;
    }

    public void goToRegisterActivity(){
        Intent switchToRegister = new Intent(this,RegistrationActivity.class);
        startActivity(switchToRegister);
    }
}