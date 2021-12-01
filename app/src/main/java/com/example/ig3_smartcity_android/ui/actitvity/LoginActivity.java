package com.example.ig3_smartcity_android.ui.actitvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ig3_smartcity_android.R;
import com.example.ig3_smartcity_android.model.LoginUser;
import com.example.ig3_smartcity_android.ui.viewModel.LoginUserViewModel;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameText, passwordText;
    private Button buttonLogin;
    private Button switchToRegisterActivity;
    private TextView textWarnUsername, textWarnPassword;
    private LoginUserViewModel loginUserViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.loginButtonID);
        loginUserViewModel = new  ViewModelProvider(this).get(LoginUserViewModel.class);
        switchToRegisterActivity = findViewById(R.id.signupID);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });


        switchToRegisterActivity.setOnClickListener(new View.OnClickListener() {
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
            textWarnUsername.setText(getResources().getText(R.string.usernameWarneMessage));
            return false;
        }
        if(passwordText.getText().toString().equals("")){
            textWarnPassword.setVisibility(View.VISIBLE);
            textWarnPassword.setText(getResources().getText(R.string.passwordWarnMessage));
            return false;
        }
        return true;
    }

    //---Login--
    /*private class UserLoginTask extends AsyncTask<LoginUser,Void,String>{
        @Override
        protected String doInBackground(LoginUser... loginUsers) {
            loginUserViewModel.loginUser(loginUsers[0]);
            return loginUsers[0].getUsername();
        }
    }*/

    public void login(){
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        LoginUser loginUser = new LoginUser(username,password);
        loginUserViewModel.loginUser(loginUser);
    }

    public void goToRegisterActivity(){
        Intent switchToRegister = new Intent(this,RegistrationActivity.class);
        startActivity(switchToRegister);
    }

    public void goToMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}