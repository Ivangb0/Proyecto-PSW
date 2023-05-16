package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.Ahorcado;
import BusinessLogic.User;

public class RetoAhorcado extends AppCompatActivity {
    private Ahorcado ahorcado;

    private int numPregunta = 0;
    private int vidas = 0;
    private int puntosAcum = 0;

    int aciertoStat;
    int falloStat;
    int wonStat;
    int lostStat;
    int abandonedStat;
    int tiempoStat;
    boolean consolidado;
    CountDownTimer countDownTimer;
    CountDownTimer countDownTimerCons;
    int duration;
    int durationCons = 21;
    private User usuario;
    private int puntosAcumTotales = 0;
    private int puntosPregunta = 0;
    private int PtosConsolidados = 0;
    private String tipo = null;
    private int porcentaje = 0;
    boolean appMuted;
    ImageButton muteButton;
    Sound sound = new Sound();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.retoahorcado);

        Intent intent = getIntent();

        ahorcado = (Ahorcado) intent.getSerializableExtra("cuestion");
        numPregunta = (int) intent.getSerializableExtra("numPregunta");
        vidas = (int) intent.getSerializableExtra("vidas");
        consolidado = (boolean) intent.getSerializableExtra("consolidado");
        puntosAcum = (int) intent.getSerializableExtra("pntsAcum");
        PtosConsolidados = (int) intent.getSerializableExtra("pntsCons");
        duration = ahorcado.getTimer();
        usuario = (User) intent.getSerializableExtra("user");
        appMuted = intent.getBooleanExtra("muted", false);
        tipo = (String) intent.getSerializableExtra("tipo");
    }


    /* este metodo es para las estadisticas
    public void asignarStats() {
        aciertoStat = usuario.getAciertos();
        falloStat = usuario.getFallos();
        wonStat = usuario.getGanadas();
        lostStat = usuario.getPerdidas();
        abandonedStat = usuario.getAbandonadas();
        tiempoStat = usuario.getTiempoUso();
    }*/
    @Override
    public void onBackPressed() {}
}
