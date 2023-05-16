package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.User;

public class Estadisticas extends AppCompatActivity {

    TextView textViewUserStats;
    TextView textViewGlobStats;
    TextView textViewWonStats;
    TextView textViewLostStats;
    TextView textViewAbandStats;
    TextView textViewTiempoUso;

    User usu;
    int global;
    int aciertos;
    int fallos;
    int partidasGanadas;
    int partidasPerdidas;
    int partidasAbandonadas;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.estadisticas);
        Intent intent = getIntent();
        usu = IniciarSesion.usuario;

        textViewUserStats = (TextView) findViewById(R.id.textViewUserStats);
        textViewGlobStats = (TextView) findViewById(R.id.textViewGlob);
        textViewWonStats = (TextView) findViewById(R.id.textViewWon);
        textViewLostStats = (TextView) findViewById(R.id.textViewLost);
        textViewAbandStats = (TextView) findViewById(R.id.textViewAband);
        textViewTiempoUso = (TextView) findViewById(R.id.textViewTiempoUso);
        setStatsUser();
    }

    public void setStatsUser(){
        global = usu.getAciertos() / (usu.getFallos() + usu.getAciertos());
        textViewUserStats.setText("Usuario: " + usu.getUsername());
        textViewGlobStats.setText("Aciertos globales: " + global);
        textViewWonStats.setText("Partidas ganadas: " + usu.getGanadas());
        textViewLostStats.setText("Partidas perdidas: " + usu.getPerdidas());
        textViewAbandStats.setText("Partidas abandonadas: " + usu.getAbandonadas());
        textViewTiempoUso.setText("Tiempo de uso: " + usu.getTiempoUso());
    }

    public void volverDeEstadisticas(View view){
        Intent salirEstadisticas = new Intent(this, Perfil.class);
        //salirEstadisticas.putExtra("user", user);
        finishAfterTransition();
        startActivity(salirEstadisticas);
    }

}
