package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.Pregunta;

public class Gestor extends AppCompatActivity {

    private int numPreg;
    private int puntosAcum;
    private int vidas;
    private boolean init;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cuestionAleatoria();
    }

    private void cuestionAleatoria() {

        Intent intent = new Intent(this, PruebaLayout.class);

        setVariables();
        Director director = new Director();

        RetoPreguntaBuilder preguntaBuilder = new RetoPreguntaBuilder();

        if (numPreg < 4) {
            director.construirPreguntaFacil(preguntaBuilder);
        } else if (numPreg < 7) {
            director.construirPreguntaMedio(preguntaBuilder);
        } else if (numPreg <= 10) {
            director.construirPreguntaDificil(preguntaBuilder);
        }else{
            intent = new Intent(this, PartidaFinalizada.class);
            startActivity(intent);
    }
        Pregunta prueba = preguntaBuilder.getTipo();
        intent.putExtra("cuestion", prueba);
        intent.putExtra("vidas", vidas);
        intent.putExtra("numPregunta", numPreg);
        startActivity(intent);
    }

    private void setVariables(){
        Intent intent = getIntent();
        init = (boolean) intent.getSerializableExtra("init");
        if(init){
            numPreg = 1;
            puntosAcum = 0;
            vidas = 2;
        } else{
            numPreg = (int) intent.getSerializableExtra("numPreg");
            puntosAcum = puntosAcum + (int) intent.getSerializableExtra("puntosAcum");
            vidas = (int) intent.getSerializableExtra("vidasPreg");
        }
    }
}
