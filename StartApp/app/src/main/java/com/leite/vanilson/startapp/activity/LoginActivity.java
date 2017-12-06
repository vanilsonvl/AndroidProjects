package com.leite.vanilson.startapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.leite.vanilson.startapp.R;
import com.leite.vanilson.startapp.bo.LoginBO;
import com.leite.vanilson.startapp.validation.ValidationLogin;

public class LoginActivity extends AppCompatActivity {

    private EditText edtLogin;
    private EditText edtPassword;
    private Button btnLogin;
    private SharedPreferences preferences;
    private LoginBO loginBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        String login = preferences.getString("login", null);
        String password = preferences.getString("password", null);
        loginBO = new LoginBO(this);
        if(login != null && password != null){
            Intent intent = new Intent(LoginActivity.this, PersonActivity.class);
            startActivity(intent);
            finish();
        }
        edtLogin = (EditText) findViewById(R.id.edt_login);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = edtLogin.getText().toString();
                String password = edtPassword.getText().toString();
                ValidationLogin validationLogin = new ValidationLogin();
                validationLogin.setActivity(LoginActivity.this);
                validationLogin.setLogin(login);
                validationLogin.setPassword(password);
                validationLogin.setEdtLogin(edtLogin);
                validationLogin.setEdtPassword(edtPassword);
                boolean isValidLogin = loginBO.validateFields(validationLogin);
                if(isValidLogin){
                    Intent intent = new Intent(LoginActivity.this, PersonActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
