package com.leite.vanilson.startapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.leite.vanilson.startapp.Util.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Bem Vindo");

        Util.showMsgToast(this, "Util do Toast");

        Util.showMsgAlertOK(this, "Titulo", "Mensagem ....");

    }
}
