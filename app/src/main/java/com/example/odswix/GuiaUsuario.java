package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.User;

public class GuiaUsuario extends AppCompatActivity {

    private User usuario = null;
    boolean appMuted = false;

    ImageView ivRetoPregunta;
    ImageView ivRetoFrase;
    ImageView ivArmario;
    ImageView ivCobertura;
    ImageView ivEscogerReto;
    ImageView ivPerfil;
    ImageView ivEstadisticas;
    ImageView ivConfiguracion;
    ImageView ivRetoAhorcado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");
        appMuted = (boolean) intent.getBooleanExtra("muted", false);
        setContentView(R.layout.activity_guia_usuario);
        ivRetoPregunta = findViewById(R.id.imageView15);
        ivRetoFrase = findViewById(R.id.imageView16);
        ivArmario = findViewById(R.id.imageView20);
        ivCobertura = findViewById(R.id.imageView22);
        ivEscogerReto = findViewById(R.id.imageView11);
        ivPerfil = findViewById(R.id.imageView19);
        ivEstadisticas = findViewById(R.id.imageView21);
        ivConfiguracion = findViewById(R.id.imageView18);
        ivRetoAhorcado = findViewById(R.id.imageView17);

        ivRetoPregunta.setImageResource(R.drawable.gretopregunta);
        ivRetoFrase.setImageResource(R.drawable.gretofrase);
        ivArmario.setImageResource(R.drawable.garmario);
        ivCobertura.setImageResource(R.drawable.gcobertura);
        ivEscogerReto.setImageResource(R.drawable.gcatalogo);
        ivPerfil.setImageResource(R.drawable.gperfil);
        ivEstadisticas.setImageResource(R.drawable.stats);
        ivConfiguracion.setImageResource(R.drawable.gconfig);
        ivRetoAhorcado.setImageResource(R.drawable.gretoahorcado);

    }
    public void volverMainMenu (View view){
        Intent intent = new Intent(this, JugarPartida.class);
        intent.putExtra("user", usuario);
        intent.putExtra("muted", appMuted);
        finishAfterTransition();
        startActivity(intent);
    }
}