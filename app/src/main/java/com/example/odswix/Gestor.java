package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.Frase;
import BusinessLogic.Pregunta;
import BusinessLogic.User;
import CalsesBuilder.*;
import Persistence.UserDAO;

public class Gestor extends AppCompatActivity {
    private int numPreg;
    private int puntosAcum;
    private int puntosCons;
    private int vidas;
    private boolean init;
    private boolean consolidado;
    private int puntosAcumTotales;
    private String tipo;
    User usuario = null;
    boolean appMuted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setVariables();
        Intent intent = getIntent();
        //appMuted = (boolean) intent.getSerializableExtra("muted");

        switch (tipo) {
            case "mixta":
                cuestionAleatoria();
                break;
            case "pregunta":
                cuestionPregunta();
                break;
            case "frase":
                cuestionFrase();
                break;
        }
    }

    private void cuestionAleatoria() {

    }
    private void cuestionFrase() {

        Intent intent = new Intent(this, RetoFrasePrueba.class);

        if (vidas <= 0) {
            intent = new Intent(this, JugarPartida.class);
            intent.putExtra("user", usuario);
            startActivity(intent);
            this.finish();
        }

        Frase tipoFrase = new Frase();
        tipoFrase.setDificultad("Facil");
        tipoFrase.setTimer(120);
        tipoFrase.setEnunciado("Descripción");
        tipoFrase.setRespuesta("HOLA P");

        /*
        Director director = new Director();
        RetoFraseFacilBuilder fraseFacilBuilder = new RetoPreguntaFacilBuilder();
        RetoFraseMedioBuilder fraseMedioBuilder = new RetoPreguntaMedioBuilder();
        RetoFraseDificilBuilder fraseDificilBuilder = new RetoPreguntaDificilBuilder();
        if (numPreg < 4) {
            director.construirPregunta(fraseFacilBuilder);
            tipoFrase = fraseFacilBuilder.getTipo();
        } else if (numPreg < 7) {
            director.construirPregunta(fraseMedioBuilder);
            tipoFrase = fraseMedioBuilder.getTipo();
        } else if (numPreg <= 10) {
            director.construirPregunta(fraseDificilBuilder);
            tipoFrase = fraseDificilBuilder.getTipo();
        }else{
            UserDAO userdao = new UserDAO();

            intent = new Intent(this, PartidaFinalizada.class);
            puntosAcumTotales = puntosAcum + usuario.getPuntosAcumTotales();
            usuario.setPuntosAcumTotales(puntosAcumTotales);
            userdao.actualizar(usuario);
            intent.putExtra("pntsFin", puntosAcum);
            intent.putExtra("user", usuario);
            startActivity(intent);
            this.finish();
        }
        */



        intent.putExtra("cuestion", tipoFrase);
        intent.putExtra("vidas", vidas);
        intent.putExtra("numPregunta", numPreg);
        intent.putExtra("consolidado", consolidado);
        intent.putExtra("pntsAcum", puntosAcum);
        intent.putExtra("pntsCons", puntosCons);
        intent.putExtra("user", usuario);
        intent.putExtra("tipo", tipo);
        intent.putExtra("muted",appMuted);
        startActivity(intent);
    }
    private void cuestionPregunta() {

        Intent intent = new Intent(this, RetoPregunta.class);

        if (vidas <= 0) {
            intent = new Intent(this, JugarPartida.class);
            intent.putExtra("user", usuario);
            startActivity(intent);
            this.finish();
        }

        Director director = new Director();
        Pregunta pregunta = new Pregunta();
        RetoPreguntaFacilBuilder preguntaFacilBuilder = new RetoPreguntaFacilBuilder();
        RetoPreguntaMedioBuilder preguntaMedioBuilder = new RetoPreguntaMedioBuilder();
        RetoPreguntaDificilBuilder preguntaDificilBuilder = new RetoPreguntaDificilBuilder();
        if (numPreg < 4) {
            director.construirPregunta(preguntaFacilBuilder);
            pregunta = preguntaFacilBuilder.getTipo();
        } else if (numPreg < 7) {
            director.construirPregunta(preguntaMedioBuilder);
            pregunta = preguntaMedioBuilder.getTipo();
        } else if (numPreg <= 10) {
            director.construirPregunta(preguntaDificilBuilder);
            pregunta = preguntaDificilBuilder.getTipo();
        }else{
            UserDAO userdao = new UserDAO();

            intent = new Intent(this, PartidaFinalizada.class);
            puntosAcumTotales = puntosAcum + usuario.getPuntosAcumTotales();
            usuario.setPuntosAcumTotales(puntosAcumTotales);
            userdao.actualizar(usuario);
            intent.putExtra("pntsFin", puntosAcum);
            intent.putExtra("user", usuario);
            startActivity(intent);
            this.finish();
        }

        intent.putExtra("cuestion", pregunta);
        intent.putExtra("vidas", vidas);
        intent.putExtra("numPregunta", numPreg);
        intent.putExtra("consolidado", consolidado);
        intent.putExtra("pntsAcum", puntosAcum);
        intent.putExtra("pntsCons", puntosCons);
        intent.putExtra("user", usuario);
        intent.putExtra("tipo", tipo);
        intent.putExtra("muted",appMuted);
        startActivity(intent);

    }

    private void setVariables(){
        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");
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
        tipo = (String) intent.getSerializableExtra("tipo");
    }
}
