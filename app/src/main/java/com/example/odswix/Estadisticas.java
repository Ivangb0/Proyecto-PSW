package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.User;
import ClasesObserver.EstadisticasObservable;
import ClasesObserver.EstadisticasObserver;
import ClasesObserver.GestorEstadisticas;

public class Estadisticas extends AppCompatActivity implements EstadisticasObserver {

    TextView textViewUserStats;
    TextView textViewGlobStats;
    TextView textViewWonStats;
    TextView textViewLostStats;
    TextView textViewAbandStats;
    TextView textViewTiempoUso;
    User usu;
    GestorEstadisticas gestorEstadisticas;

    private int obsAciertos;
    private int obsFallos;
    private int obsGanadas;
    private int obsPerdidas;
    private int obsAbandonadas;


    public Estadisticas(){}

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
        int aciertosYFallos = usu.getAciertos() + usu.getFallos();
        textViewUserStats.setText("Usuario: " + usu.getUsername());
        textViewGlobStats.setText("Aciertos globales: " + usu.getAciertos() + " / " + aciertosYFallos);
        textViewWonStats.setText("Partidas ganadas: " + usu.getGanadas());
        textViewLostStats.setText("Partidas perdidas: " + usu.getPerdidas());
        textViewAbandStats.setText("Partidas abandonadas: " + usu.getAbandonadas());
        textViewTiempoUso.setText("Tiempo de uso: " + usu.getTiempoUso());

    }

    //observer
    @Override
    public void onEstadisticasActualizadas(int aciertos, int fallos, int ganadas, int perdidas, int abandonadas) {

        int aciertosYFallos = usu.getAciertos() + usu.getFallos();
        textViewUserStats.setText("Usuario: " + usu.getUsername());
        textViewGlobStats.setText("Aciertos globales: " + usu.getAciertos() + " / " + aciertosYFallos);
        textViewWonStats.setText("Partidas ganadas: " + usu.getGanadas());
        textViewLostStats.setText("Partidas perdidas: " + usu.getPerdidas());
        textViewAbandStats.setText("Partidas abandonadas: " + usu.getAbandonadas());
        textViewTiempoUso.setText("Tiempo de uso: " + usu.getTiempoUso());
    }
    public Estadisticas(GestorEstadisticas gestorEstadisticas){
        this.gestorEstadisticas = gestorEstadisticas;
    }


    public void volverDeEstadisticas(View view){
        Intent salirEstadisticas = new Intent(this, Perfil.class);
        salirEstadisticas.putExtra("user", usu);
        finishAfterTransition();
        startActivity(salirEstadisticas);
    }


}
