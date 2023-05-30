package com.example.odswix;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.User;
import ClasesEstado.ContextoReto;
import ClasesEstado.Nivel0EstadoReto;
import ClasesEstado.Nivel1EstadoReto;
import ClasesEstado.Nivel2EstadoReto;
import ClasesEstado.Nivel3EstadoReto;
import ClasesStrategy.ContextoStrategy;
import ClasesStrategy.RetoAhorcadoStrategy;
import ClasesStrategy.RetoMixtoStrategy;
import ClasesStrategy.RetoPreguntaStrategy;

public class CatalogoRetos extends AppCompatActivity {
    Button botonRetoPregunta;
    Button botonRetoFrase;
    Button botonRetoAhorcado;
    Button botonRetoMixto;
    private User usuario = null;

    private final String tipo = null;
    boolean init;
    boolean appMuted;
    private ContextoReto contextoReto;
    int nivel;
    ContextoStrategy context;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.catalogo_retos);
        init = true;
        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");
        appMuted = intent.getBooleanExtra("muted",false);
        botonRetoPregunta = findViewById(R.id.botonRetoPreg);
        botonRetoFrase = findViewById(R.id.botonRetoFrase);
        botonRetoAhorcado = findViewById(R.id.botonRetoAhor);
        botonRetoMixto = findViewById(R.id.botonRetoMixto);

        contextoReto = new ContextoReto();
        nivel = usuario.getNivel();
        actualizarEstado();
    }

    public void mixta(View view){
        context = new ContextoStrategy(new RetoMixtoStrategy());
        context.elegirTipo(this,"mixto",usuario, appMuted);
        this.finish();
    }
    public void pregunta(View view){
        context = new ContextoStrategy(new RetoPreguntaStrategy());
        context.elegirTipo(this,"pregunta",usuario, appMuted);
        this.finish();
    }
    public void frase(View view){
        context = new ContextoStrategy(new RetoMixtoStrategy());
        context.elegirTipo(this,"frase",usuario, appMuted);
        this.finish();
    }

    public void ahorcado(View view){
        context = new ContextoStrategy(new RetoAhorcadoStrategy());
        context.elegirTipo(this, "ahorcado",usuario, appMuted);
        this.finish();
    }

    public void cancelar(View view){
        Intent intent = new Intent(this, JugarPartida.class);
        intent.putExtra("user", usuario);
        finishAfterTransition();
        startActivity(intent);
    }
    public void actualizarEstado(){
        switch (nivel){
            case 0:
                contextoReto.setEstado(new Nivel0EstadoReto());
                break;
            case 1:
                contextoReto.setEstado(new Nivel1EstadoReto());
                break;
            case 2:
                contextoReto.setEstado(new Nivel2EstadoReto());
                break;
            case 3:
                contextoReto.setEstado(new Nivel3EstadoReto());
                break;
        }
        contextoReto.configurarVistas(this);
    }
    @SuppressLint("ResourceAsColor")
    public void desbloquearRetoPregunta(boolean desbloqueado) {
        botonRetoPregunta.setClickable(desbloqueado);
        if(!desbloqueado) {
            botonRetoPregunta.setText(R.string.RetoBloqueado);
            botonRetoPregunta.setBackgroundColor(R.color.black);
        }
    }

    @SuppressLint("ResourceAsColor")
    public void desbloquearRetoCompletarFrase(boolean desbloqueado) {
        botonRetoFrase.setClickable(desbloqueado);
        if(!desbloqueado) {
            botonRetoFrase.setText(R.string.RetoBloqueado);
            botonRetoFrase.setBackgroundColor(R.color.black);
        }
    }

    @SuppressLint("ResourceAsColor")
    public void desbloquearRetoAhorcado(boolean desbloqueado) {
        botonRetoAhorcado.setClickable(desbloqueado);
        if(!desbloqueado) {
            botonRetoAhorcado.setText(R.string.RetoBloqueado);
            botonRetoAhorcado.setBackgroundColor(R.color.black);
        }
    }

    @SuppressLint("ResourceAsColor")
    public void desbloquearRetoMixto(boolean desbloqueado) {
        botonRetoMixto.setClickable(desbloqueado);
        if(!desbloqueado) {
            botonRetoMixto.setText(R.string.RetoBloqueado);
            botonRetoMixto.setBackgroundColor(R.color.black);
        }
    }
    @Override
    public void onBackPressed() {}
}
