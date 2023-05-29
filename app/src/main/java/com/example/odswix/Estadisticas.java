package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.User;
import ClasesObserver.EstadisticasObserver;
import ClasesObserver.GestorEstadisticas;
import ClasesObserver.ObserverEstadisticas;
import ClasesObserver.ObserverPerfil;

public class Estadisticas extends AppCompatActivity {

    TextView textViewUserStats;
    TextView textViewGlobStats;
    TextView textViewWonStats;
    TextView textViewLostStats;
    TextView textViewAbandStats;
    TextView textViewTiempoUso;
    User usu;
    GestorEstadisticas gestorEstadisticas;

    @Override
    protected void onCreate(Bundle savedInstance){

        GestorEstadisticas observer = new GestorEstadisticas();
        observer.registrarObserver(new ObserverEstadisticas(this));


        super.onCreate(savedInstance);
        setContentView(R.layout.estadisticas);
        Intent intent = getIntent();
        usu = (User) intent.getSerializableExtra("user");

        textViewUserStats = (TextView) findViewById(R.id.textViewUserStats);
        textViewGlobStats = (TextView) findViewById(R.id.textViewGlob);
        textViewWonStats = (TextView) findViewById(R.id.textViewWon);
        textViewLostStats = (TextView) findViewById(R.id.textViewLost);
        textViewAbandStats = (TextView) findViewById(R.id.textViewAband);
        textViewTiempoUso = (TextView) findViewById(R.id.textViewTiempoUso);
        setStatsUser(usu);
    }

    public void setStatsUser(User usu){
        textViewUserStats.setText("Usuario: " + usu.getUsername());
        textViewGlobStats.setText("Aciertos globales: " + usu.getAciertos() + " / " + (usu.getAciertos() + usu.getFallos()));
        textViewWonStats.setText("Partidas ganadas: " + usu.getGanadas());
        textViewLostStats.setText("Partidas perdidas: " + usu.getPerdidas());
        textViewAbandStats.setText("Partidas abandonadas: " + usu.getAbandonadas());
        textViewTiempoUso.setText("Tiempo de uso: " + usu.getTiempoUso());

    }

    //observer

    public void obserSetData() {
        setStatsUser(usu);
    }

    public Estadisticas() {
        // Código del constructor (si es necesario)
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
