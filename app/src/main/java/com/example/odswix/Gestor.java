package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import BusinessLogic.Ahorcado;
import BusinessLogic.Frase;
import BusinessLogic.Pregunta;
import BusinessLogic.User;
import CalsesBuilder.Director;
import CalsesBuilder.RetoAhorcadoDificilBuilder;
import CalsesBuilder.RetoAhorcadoFacilBuilder;
import CalsesBuilder.RetoAhorcadoMedioBuilder;
import CalsesBuilder.RetoFraseDificilBuilder;
import CalsesBuilder.RetoFraseFacilBuilder;
import CalsesBuilder.RetoFraseMedioBuilder;
import CalsesBuilder.RetoPreguntaDificilBuilder;
import CalsesBuilder.RetoPreguntaFacilBuilder;
import CalsesBuilder.RetoPreguntaMedioBuilder;
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
        appMuted = (boolean) intent.getBooleanExtra("muted", false);

        switch (tipo) {
            case "mixto":
                cuestionAleatoria();
                break;
            case "pregunta":
                cuestionPregunta();
                break;
            case "frase":
                cuestionFrase();
                break;
            case "ahorcado":
                cuestionAhorcado();
                break;
        }
        usuario = (User) intent.getSerializableExtra("user");

    }

    public void checkVidas(int vidas){
        if (vidas <= 0) {
            Intent intent = new Intent(this, JugarPartida.class);
            intent.putExtra("user", usuario);
            startActivity(intent);
            this.finish();
        }
    }

    private void cuestionAleatoria() {
        Random random = new Random();
        int resultado = random.nextInt(3);
        if(resultado == 0)
            cuestionFrase();
        else if(resultado == 1)
            cuestionPregunta();
        else
            cuestionAhorcado();
    }
    private void cuestionFrase() {

        Intent intent = new Intent(this, RetoFrasePrueba.class);

        checkVidas(vidas);

        Frase tipoFrase = construirFrase(numPreg);

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

    public Frase construirFrase(int nPreg){
        Frase tipoFrase = new Frase();
        Director director = new Director();
        RetoFraseFacilBuilder fraseFacilBuilder = new RetoFraseFacilBuilder();
        RetoFraseMedioBuilder fraseMedioBuilder = new RetoFraseMedioBuilder();
        RetoFraseDificilBuilder fraseDificilBuilder = new RetoFraseDificilBuilder();
        if (nPreg < 4) {
            director.construirFrase(fraseFacilBuilder);
            tipoFrase = fraseFacilBuilder.getTipo();
        } else if (nPreg < 7) {
            director.construirFrase(fraseMedioBuilder);
            tipoFrase = fraseMedioBuilder.getTipo();
        } else if (nPreg <= 10) {
            director.construirFrase(fraseDificilBuilder);
            tipoFrase = fraseDificilBuilder.getTipo();
        }else{
            UserDAO userdao = new UserDAO();

            Intent intent = new Intent(this, PartidaFinalizada.class);
            puntosAcumTotales = puntosAcum + usuario.getPuntosAcumTotales();
            usuario.setPuntosAcumTotales(puntosAcumTotales);
            userdao.actualizar(usuario);
            intent.putExtra("pntsFin", puntosAcum);
            intent.putExtra("user", usuario);
            intent.putExtra("muted",appMuted);
            startActivity(intent);
            this.finish();
        }
        return tipoFrase;
    }

    private void cuestionPregunta() {

        Intent intent = new Intent(this, RetoPregunta.class);

        checkVidas(vidas);

        Pregunta preg = construirPregunta(numPreg);

        intent.putExtra("cuestion", preg);
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

    public Pregunta construirPregunta(int nPreg){
        Director director = new Director();
        Pregunta pregunta = new Pregunta();
        RetoPreguntaFacilBuilder preguntaFacilBuilder = new RetoPreguntaFacilBuilder();
        RetoPreguntaMedioBuilder preguntaMedioBuilder = new RetoPreguntaMedioBuilder();
        RetoPreguntaDificilBuilder preguntaDificilBuilder = new RetoPreguntaDificilBuilder();
        if (nPreg < 4) {
            director.construirPregunta(preguntaFacilBuilder);
            pregunta = preguntaFacilBuilder.getTipo();
        } else if (nPreg < 7) {
            director.construirPregunta(preguntaMedioBuilder);
            pregunta = preguntaMedioBuilder.getTipo();
        } else if (nPreg <= 10) {
            director.construirPregunta(preguntaDificilBuilder);
            pregunta = preguntaDificilBuilder.getTipo();
        }else{
            UserDAO userdao = new UserDAO();

            Intent intent = new Intent(this, PartidaFinalizada.class);
            puntosAcumTotales = puntosAcum + usuario.getPuntosAcumTotales();
            usuario.setPuntosAcumTotales(puntosAcumTotales);
            userdao.actualizar(usuario);
            intent.putExtra("pntsFin", puntosAcum);
            intent.putExtra("user", usuario);
            intent.putExtra("muted",appMuted);
            startActivity(intent);
            this.finish();
        }
        return pregunta;
    }

    private void cuestionAhorcado() {

        Intent intent = new Intent(this, RetoAhorcado.class);

        checkVidas(vidas);

        Ahorcado ahorcado = construirAhorcado(numPreg);

        intent.putExtra("cuestion", ahorcado);
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

    public Ahorcado construirAhorcado(int npreg){
        Director director = new Director();
        Ahorcado ahorcado = new Ahorcado();
        RetoAhorcadoFacilBuilder ahorcadoFacilBuilder = new RetoAhorcadoFacilBuilder();
        RetoAhorcadoMedioBuilder ahorcadoMedioBuilder = new RetoAhorcadoMedioBuilder();
        RetoAhorcadoDificilBuilder ahorcadoDificilBuilder = new RetoAhorcadoDificilBuilder();

        if (npreg < 4) {
            director.construirAhorcado(ahorcadoFacilBuilder);
            ahorcado = ahorcadoFacilBuilder.getTipo();
        } else if (npreg < 7) {
            director.construirAhorcado(ahorcadoMedioBuilder);
            ahorcado = ahorcadoMedioBuilder.getTipo();
        } else if (npreg <= 10) {
            director.construirAhorcado(ahorcadoDificilBuilder);
            ahorcado = ahorcadoDificilBuilder.getTipo();
        }else{
            UserDAO userdao = new UserDAO();

            Intent intent = new Intent(this, PartidaFinalizada.class);
            puntosAcumTotales = puntosAcum + usuario.getPuntosAcumTotales();
            usuario.setPuntosAcumTotales(puntosAcumTotales);
            userdao.actualizar(usuario);
            intent.putExtra("pntsFin", puntosAcum);
            intent.putExtra("user", usuario);
            intent.putExtra("muted",appMuted);
            startActivity(intent);
            this.finish();
        }
        return ahorcado;
    }

    private void setVariables(){
        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");
        numPreg = (int) intent.getSerializableExtra("numPreg");
        consolidado = (boolean) intent.getSerializableExtra("consolidado");
        vidas = (int) intent.getSerializableExtra("vidasPreg");
        if(consolidado)puntosCons = (int) intent.getSerializableExtra("puntosCons");
        puntosAcum = puntosAcum + (int) intent.getSerializableExtra("puntosAcum");
        tipo = (String) intent.getSerializableExtra("tipo");
    }
}
