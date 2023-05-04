package com.example.odswix;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.User;

public class JugarPartida extends AppCompatActivity {
    private User usuario = null;
    boolean appMuted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jugarpartida);
        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");

        Toast.makeText(this,"" + appMuted, Toast.LENGTH_LONG);
    }
    public void jugar (View view){
        Intent intent = new Intent(this, CatalogoRetos.class);
        intent.putExtra("user", usuario);
        intent.putExtra("muted", appMuted);
        finishAfterTransition();
        startActivity(intent);
    }
    public void configuracion (View view){
        Intent intent = new Intent(this, menu_configuracion.class);
        intent.putExtra("muted", appMuted);
        startActivity(intent);
    }
    public void perfil (View view){
        Intent perfil = new Intent(this, Perfil.class);
        perfil.putExtra("user", usuario);
        finishAfterTransition();
        startActivity(perfil);
    }
    public void cerrarSes (View view){
        Intent cerrarSes = new Intent(this, IniciarSesion.class);
        finishAfterTransition();
        startActivity(cerrarSes);
    }
    @Override
    public void onBackPressed() {}
}


