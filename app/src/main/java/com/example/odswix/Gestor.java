package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import BusinessLogic.Pregunta;

public class Gestor extends AppCompatActivity {

    private int numPreg = 0;
    private int puntosAcum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cuestionAleatoria();
    }

    private void cuestionAleatoria() {
        if(this.numPreg == 10){
            Intent intent = new Intent(this, RetoSuperado.class);
            finishAfterTransition();
            startActivity(intent);
        }
        Random random = new Random();
        int resultado = 0; // random.nextInt(4);

        Intent intent = null;

        Director director = new Director();
        Pregunta prueba = null;

        if (resultado == 0) {
            RetoPreguntaBuilder preguntaBuilder = new RetoPreguntaBuilder();
            director.construirPreguntaFacil(preguntaBuilder);
            prueba = preguntaBuilder.getTipo();
            intent = new Intent(this, PruebaLayout.class);
            intent.putExtra("cuestion", prueba);
        }


        numPreg++;
        intent.putExtra("numPregunta", numPreg);
        startActivity(intent);
        //startActivityForResult(intent, 1);
    }
    //Investigar como funciona este método <<<<
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            // Procesar el resultado devuelto por la actividad iniciada
            String resultado = data.getStringExtra("resultado");
        }
    }
}
