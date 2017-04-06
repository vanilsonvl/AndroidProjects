package com.leite.vanilson.startapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Bem Vindo");

        LayoutInflater inflater = getLayoutInflater();
        View layoutToast = inflater.inflate(R.layout.my_toast, (ViewGroup) findViewById(R.id.lytToast));

        TextView txtToast = (TextView) layoutToast.findViewById(R.id.txtToast);
        txtToast.setText("Segundo Toast");


//        Toast toast = Toast.makeText(this, "Primeiro Toast", Toast.LENGTH_LONG);
        Toast toast = new Toast(this);
        toast.setView(layoutToast);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();


    }
}
