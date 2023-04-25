package com.example.odswix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RetoSuperado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reto_superado);
    }
    public void irPantPpal(View view) {
        Intent intent = new Intent(this, JugarPartida.class);
        startActivity(intent);
    }
}