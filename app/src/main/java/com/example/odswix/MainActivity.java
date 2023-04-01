package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;


public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.texto);

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        String url = "jdbc:mysql://wixserver.mysql.database.azure.com:3306/wixdatabase?useSSL=true";
        String user = "KogMaw";
        String password = "WIXAdmin1";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, Registrarse.class);

        startActivity(intent);
    }

    //Metodo de prueba para transferir datos    //intent.putExtra("ejemplo", stmt);

    public void ejecutar_registrarse(View view) {

    }

    public void buttonConnectToMySQL(View view){

    }

    public void datosTrans(View vista){

    }
}