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

        if(numPreg > 10){
            UserDAO userdao = new UserDAO();

            intent = new Intent(this, PartidaFinalizada.class);
            puntosAcumTotales = puntosAcum + usuario.getPuntosAcumTotales();
            usuario.setPuntosAcumTotales(puntosAcumTotales);
            userdao.actualizar(usuario);
            intent.putExtra("pntsFin", puntosAcum);
            intent.putExtra("user", usuario);
            intent.putExtra("muted",appMuted);
            startActivity(intent);
            this.finish();
        }

        GestorConstructor gestor = new GestorConstructor();
        gestor.init(usuario, puntosAcumTotales, puntosAcum, appMuted);
        Frase tipoFrase = gestor.construirFrase(numPreg);

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

        checkVidas(vidas);

        if(numPreg > 10){
            UserDAO userdao = new UserDAO();

            intent = new Intent(this, PartidaFinalizada.class);
            puntosAcumTotales = puntosAcum + usuario.getPuntosAcumTotales();
            usuario.setPuntosAcumTotales(puntosAcumTotales);
            userdao.actualizar(usuario);
            intent.putExtra("pntsFin", puntosAcum);
            intent.putExtra("user", usuario);
            intent.putExtra("muted",appMuted);
            startActivity(intent);
            this.finish();
        }

        GestorConstructor gestor = new GestorConstructor();
        gestor.init(usuario, puntosAcumTotales, puntosAcum, appMuted);
        Pregunta preg = gestor.construirPregunta( numPreg);

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

    private void cuestionAhorcado() {

        Intent intent = new Intent(this, RetoAhorcado.class);

        checkVidas(vidas);

        if(numPreg > 10){
            UserDAO userdao = new UserDAO();

            intent = new Intent(this, PartidaFinalizada.class);
            puntosAcumTotales = puntosAcum + usuario.getPuntosAcumTotales();
            usuario.setPuntosAcumTotales(puntosAcumTotales);
            userdao.actualizar(usuario);
            intent.putExtra("pntsFin", puntosAcum);
            intent.putExtra("user", usuario);
            intent.putExtra("muted",appMuted);
            startActivity(intent);
            this.finish();
        }

        GestorConstructor gestor = new GestorConstructor();
        gestor.init(usuario, puntosAcumTotales, puntosAcum, appMuted);
        Ahorcado ahorcado = gestor.construirAhorcado(numPreg);

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
