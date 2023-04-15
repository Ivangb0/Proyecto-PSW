package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.Pregunta;

public class Gestor extends AppCompatActivity {

    private int numPreg;
    private int puntosAcum;
    private int puntosCons;
    private int vidas;
    private boolean init;
    private boolean consolidado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cuestionAleatoria();
    }

    private void cuestionAleatoria() {

        Intent intent = new Intent(this, PruebaLayout.class);

        setVariables();
        Director director = new Director();


        if (vidas <= 0) {
            intent = new Intent(this, JugarPartida.class);
            startActivity(intent);
            this.finish();
        }
        RetoPreguntaBuilder preguntaBuilder = new RetoPreguntaBuilder();

        if (numPreg < 4) {
            director.construirPreguntaFacil(preguntaBuilder);
        } else if (numPreg < 7) {
            director.construirPreguntaMedio(preguntaBuilder);
        } else if (numPreg <= 10) {
            director.construirPreguntaDificil(preguntaBuilder);
        }else{
            //puntosAcumTotales += puntosAcum;
            //usuario.setPuntosAcumTotales(puntosAcumTotales);
            //userdao.actualizar(usuario);
            intent = new Intent(this, PartidaFinalizada.class);
            if(consolidado)intent.putExtra("pntsFin", puntosCons);
            else intent.putExtra("pntsFin", puntosAcum);
            startActivity(intent);
            this.finish();
        }

        Pregunta prueba = preguntaBuilder.getTipo();
        intent.putExtra("cuestion", prueba);
        intent.putExtra("vidas", vidas);
        intent.putExtra("numPregunta", numPreg);
        intent.putExtra("consolidado", consolidado);
        intent.putExtra("pntsAcum", puntosAcum);
        intent.putExtra("pntsCons", puntosCons);
        startActivity(intent);
        this.finish();
    }

    private void setVariables(){
        Intent intent = getIntent();
        init = (boolean) intent.getSerializableExtra("init");
        if(init){
            numPreg = 1;
            puntosAcum = 0;
            puntosCons = 0;
            vidas = 2;
            consolidado = false;
        } else{
            numPreg = (int) intent.getSerializableExtra("numPreg");
            consolidado = (boolean) intent.getSerializableExtra("consolidado");
            vidas = (int) intent.getSerializableExtra("vidasPreg");
            if(consolidado)puntosCons = (int) intent.getSerializableExtra("puntosCons");
            puntosAcum = puntosAcum + (int) intent.getSerializableExtra("puntosAcum");

        }
    }
}
