package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.User;

public class JugarPartida extends AppCompatActivity {
    User usuario = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jugarpartida);

        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");
    }
    //Método para ir a la pantalla de perfil
    public void perfil (View view){
        Intent perfil = new Intent(this, Perfil.class);
        perfil.putExtra("user", usuario);
        startActivity(perfil);

    }

    //Método para cerrar sesion
    public void cerrarSes (View view){
        Intent cerrarSes = new Intent(this, IniciarSesion.class);
        startActivity(cerrarSes);
    }
}


