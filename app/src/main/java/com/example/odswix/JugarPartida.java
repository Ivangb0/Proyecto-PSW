package com.example.odswix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.odswix.R;

public class JugarPartida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jugarpartida);
    }
    //Método para ir a la pantalla de perfil
    public void perfil (View view){
        Intent perfil = new Intent(this, Perfil.class);
        startActivity(perfil);
    }

    //Método para cerrar sesion
    public void cerrarSes (View view){
        Intent cerrarSes = new Intent(this, IniciarSesion.class);
        startActivity(cerrarSes);
    }
}


