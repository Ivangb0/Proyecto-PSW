package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
/*
        String url = "jdbc:mysql://wixserver.mysql.database.azure.com:3306/wixdatabase?useSSL=true";
        String user = "KogMaw";
        String password = "WIXAdmin1";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
           wait(6000);
        }catch (java.lang.InterruptedException e){
            e.printStackTrace();
        }*/


        Intent intent = new Intent(this, IniciarSesion.class);


        startActivity(intent);


    }
}