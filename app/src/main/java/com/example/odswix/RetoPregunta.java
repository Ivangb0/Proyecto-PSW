package com.example.odswix;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import BusinessLogic.Answer;
import BusinessLogic.Pregunta;
import BusinessLogic.User;
import Persistence.UserDAO;

public class RetoPregunta extends AppCompatActivity {
    private Pregunta pregunta = null;
    private int numPregunta = 0;
    private int vidas = 0;
    private int puntosAcum = 0;
    boolean consolidado;
    CountDownTimer countDownTimer;
    List<Answer> respuestasPreg = new ArrayList<Answer>();
    int duration;
    int durationCons = 21;

    Button botonResp1; Button botonResp2; Button botonResp3; Button botonResp4;
    RelativeLayout contenedorRespuesta; TextView textView21; TextView textView20;
    TextView textView26; TextView textView25; TextView textView5; TextView textView24;
    Button buttonPistas; ImageButton buttonPausa; ConstraintLayout idLayout;
    TextView textoPregunta; TextView textoDificultad; TextView textViewNumPreg;
    TextView textViewPuntosXPreg; TextView textView23; Button buttonSiguiente;
    TextView textViewPuntAcum; TextView textView6; TextView textViewTiempo;
    TextView textView30; TextView textView33; TextView textViewVidas;
    Button buttonConsolidar; TextView textViewObtend; TextView textViewPtosObtend;
    TextView textViewPtosTots; TextView textViewPtosAcums; CountDownTimer countDownTimerCons;
    TextView textViewTiempoC; TextView textViewTiempoCons; ImageView imageViewODS;
    TextView textViewPuntConsol; TextView textViewPtosCon; Button buttonAbandonar;
    MediaPlayer soundAcierto; MediaPlayer soundFallo; MediaPlayer soundBackground;
    MediaPlayer soundCountdown10s; MediaPlayer soundVictoria; MediaPlayer soundDerrota;
    User usuario;
    int puntosAcumTotales = 0;
    int puntosPregunta = 0;
    int PtosConsolidados = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retopregunta);


        Intent intent = getIntent();
        pregunta = (Pregunta) intent.getSerializableExtra("cuestion");
        numPregunta = (int) intent.getSerializableExtra("numPregunta");
        vidas = (int) intent.getSerializableExtra("vidas");
        consolidado = (boolean) intent.getSerializableExtra("consolidado");
        puntosAcum = (int) intent.getSerializableExtra("pntsAcum");;
        respuestasPreg = pregunta.getRespuestas();
        PtosConsolidados = (int) intent.getSerializableExtra("pntsCons");;
        duration = pregunta.getTimer();
        usuario = (User) intent.getSerializableExtra("user");


        textoPregunta = findViewById(R.id.textView5);
        textoDificultad = findViewById(R.id.textView20);

        botonResp1 = (Button) findViewById(R.id.buttonResp1);
        botonResp2 = (Button) findViewById(R.id.buttonResp2);
        botonResp3 = (Button) findViewById(R.id.buttonResp3);
        botonResp4 = (Button) findViewById(R.id.buttonResp4);
        textView20 = (TextView) findViewById(R.id.textView20);
        buttonPistas = (Button) findViewById(R.id.buttonPistas);
        buttonPausa = (ImageButton) findViewById(R.id.buttonPausa);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView24 = (TextView) findViewById(R.id.textView24);
        textView25 = (TextView) findViewById(R.id.textViewPuntAcum);
        textView26 = (TextView) findViewById(R.id.textView26);
        contenedorRespuesta =  (RelativeLayout) findViewById(R.id.contenedorRespuesta);
        textView21 = (TextView) findViewById(R.id.textView21);
        idLayout = (ConstraintLayout) findViewById(R.id.idLayout);
        textViewNumPreg = (TextView) findViewById(R.id.textViewNumPreg);
        textViewPuntosXPreg = (TextView) findViewById(R.id.textViewPuntosXPreg);
        textView23 = (TextView) findViewById(R.id.textView23);
        buttonSiguiente = (Button) findViewById(R.id.buttonSiguiente);
        textViewPuntAcum = (TextView) findViewById(R.id.textViewPuntAcum);
        textView6 = (TextView) findViewById(R.id.textView6);
        textViewTiempo = (TextView) findViewById(R.id.textViewTiempo);
        textView30 = (TextView) findViewById(R.id.textView30);
        textView33 = (TextView) findViewById(R.id.textView33);
        textViewVidas = (TextView) findViewById(R.id.textViewVidas);
        buttonConsolidar = (Button) findViewById(R.id.buttonConsolidar);
        textViewObtend = (TextView) findViewById(R.id.textViewObtend);
        textViewPtosObtend = (TextView) findViewById(R.id.textViewPtosObtend);
        textViewPtosTots = (TextView) findViewById(R.id.textViewPtosTots);
        textViewPtosAcums = (TextView) findViewById(R.id.textViewPtosAcums);
        textViewTiempoC = (TextView) findViewById(R.id.textViewTiempoC);
        textViewTiempoCons = (TextView) findViewById(R.id.textViewTiempoCons);
        imageViewODS = (ImageView) findViewById(R.id.imageViewODS);
        textViewPuntConsol = (TextView) findViewById(R.id.textViewPuntConsol);
        textViewPtosCon = (TextView) findViewById(R.id.textViewPtosCon);
        buttonAbandonar = (Button) findViewById(R.id.buttonAbandonar);
        soundAcierto = MediaPlayer.create(getApplicationContext(),R.raw.acierto);
        soundFallo = MediaPlayer.create(getApplicationContext(),R.raw.musicafracaso);
        soundBackground = MediaPlayer.create(getApplicationContext(),R.raw.backgroundmusic);
        soundCountdown10s = MediaPlayer.create(getApplicationContext(),R.raw.countdown10secs);
        soundVictoria = MediaPlayer.create(getApplicationContext(),R.raw.victoria);
        soundDerrota = MediaPlayer.create(getApplicationContext(),R.raw.gameover);
        soundBackground.start();

        cambiarImagenODS();
        buttonAbandonar.setVisibility(View.INVISIBLE);
        buttonAbandonar.setClickable(false);

        textoPregunta.setText(pregunta.getEnunciado());
        textoDificultad.setText(pregunta.getDificultad());
        botonResp1.setText(respuestasPreg.get(0).getRespuesta());
        botonResp2.setText(respuestasPreg.get(1).getRespuesta());
        botonResp3.setText(respuestasPreg.get(2).getRespuesta());
        botonResp4.setText(respuestasPreg.get(3).getRespuesta());


        textViewNumPreg.setText(String.valueOf(numPregunta));
        if (pregunta.getDificultad().equals("Facil")) {
            textViewPuntosXPreg.setText("100");
        } else if (pregunta.getDificultad().equals("Medio")) {
            textViewPuntosXPreg.setText("200");
        } else if (pregunta.getDificultad().equals("Dificil")) {
            textViewPuntosXPreg.setText("300");
        }
        buttonSiguiente.setText("Siguiente");
        textViewPuntAcum.setText(String.valueOf(puntosAcum));
        textViewTiempo.setText(String.valueOf(pregunta.getTimer()));
        textViewVidas.setText(String.valueOf(vidas));
        textViewPtosCon.setText(String.valueOf(PtosConsolidados));
        checkConsolidar(consolidado);
        reiniciarTimer();
    }
    private void checkConsolidar(Boolean consolidar){
        if(consolidar){
            buttonConsolidar.setVisibility(View.INVISIBLE);
            buttonConsolidar.setClickable(false);
            buttonAbandonar.setVisibility(View.VISIBLE);
            buttonAbandonar.setClickable(true);
        }
    }
    private void mostrarSiguiente(){
        contenedorRespuesta.setVisibility(View.VISIBLE);
        botonResp1.setClickable(false);
        botonResp2.setClickable(false);
        botonResp3.setClickable(false);
        botonResp4.setClickable(false);
    }
    public void siguientePregunta(View view) {
        countDownTimerCons.cancel();
        Intent intent = new Intent(this, Gestor.class);
        intent.putExtra("numPreg", numPregunta);
        intent.putExtra("puntosAcum", puntosAcum);
        intent.putExtra("vidasPreg", vidas);
        intent.putExtra("init", false);
        intent.putExtra("consolidado", consolidado);
        intent.putExtra("puntosCons", PtosConsolidados);
        intent.putExtra("user", usuario);
        soundBackground.start();
        startActivity(intent);
        this.finish();
    }

    public void reiniciarTimer() {
        countDownTimer = new CountDownTimer(duration * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String time = String.format("%2d", TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished));
                        textViewTiempo.setText(time);
                    }

                });
                if(millisUntilFinished <= 11000){
                    soundBackground.stop();
                    System.out.print("bolas");
                    soundCountdown10s.start();
                }
            }
            @Override
            public void onFinish() {
                soundCountdown10s.stop();
                soundFallo.start();
                duration = 30;
                vidas--;
                esconderTodo();
                if(puntosAcum >= puntosPregunta*2) puntosAcum -= puntosPregunta*2;
                else puntosAcum = 0;
                textView21.setText("Se acabó el tiempo.");
                puntosCuandoCorrecta();
                textViewPtosObtend.setText("0");

                buttonConsolidar.setVisibility(View.INVISIBLE);
                buttonConsolidar.setClickable(false);
                esconderTodo();
                timerConsolidar();

                mostrarSiguiente();
            }
        }.start();
    }
    public void timerConsolidar() {
        countDownTimerCons = new CountDownTimer(durationCons * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String time = String.format("%2d", TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished));
                        textViewTiempoCons.setText(time);
                    }
                });
            }
            @Override
            public void onFinish() {
                durationCons = 15;
                buttonSiguiente.performClick();
            }
        }.start();
    }
    public void esconderTodo(){
        textView20.setVisibility(View.INVISIBLE);
        botonResp1.setVisibility(View.INVISIBLE);
        botonResp2.setVisibility(View.INVISIBLE);
        botonResp3.setVisibility(View.INVISIBLE);
        botonResp4.setVisibility(View.INVISIBLE);
        buttonPausa.setVisibility(View.INVISIBLE);
        buttonPistas.setVisibility(View.INVISIBLE);
        textView5.setVisibility(View.INVISIBLE);
        textView24.setVisibility(View.INVISIBLE);
        textView25.setVisibility(View.INVISIBLE);
        textView26.setVisibility(View.INVISIBLE);
        textViewNumPreg.setVisibility(View.INVISIBLE);
        textViewPuntosXPreg.setVisibility(View.INVISIBLE);
        textView23.setVisibility(View.INVISIBLE);
        textView6.setVisibility(View.INVISIBLE);
        textViewPuntAcum.setVisibility(View.INVISIBLE);
        textViewTiempo.setVisibility(View.INVISIBLE);
        textView30.setVisibility(View.INVISIBLE);
        textView33.setVisibility(View.INVISIBLE);
        textViewVidas.setVisibility(View.INVISIBLE);
        imageViewODS.setVisibility(View.INVISIBLE);
        textViewPuntConsol.setVisibility(View.INVISIBLE);
        textViewPtosCon.setVisibility(View.INVISIBLE);
        buttonAbandonar.setVisibility(View.INVISIBLE);
        buttonAbandonar.setClickable(false);
    }
    public void pressConsolidar(View view){
        textView5.setText("");
        PtosConsolidados = puntosAcum;
        consolidado = true;
        buttonSiguiente.performClick();
    }
    public void cambiarImagenODS(){
        int numODS = pregunta.getQuestion().getOds();
        int pictureID = getResources().getIdentifier("ods" + numODS, "drawable", getPackageName());
        Drawable picture = getResources().getDrawable(pictureID);
        imageViewODS.setImageDrawable(picture);
    }
    public void puntosCuandoCorrecta(){
        textViewObtend.setVisibility(View.VISIBLE);
        textViewPtosObtend.setText(String.valueOf(puntosPregunta));
        textViewPtosObtend.setVisibility(View.VISIBLE);
        textViewPtosTots.setVisibility(View.VISIBLE);
        textViewPtosAcums.setText(String.valueOf(puntosAcum));
        textViewPtosAcums.setVisibility(View.VISIBLE);
    }

    public void respuestaCorrecta() {
        numPregunta++;
        puntosAcum += puntosPregunta;
        textView21.setText("Respuesta correcta.");
        puntosCuandoCorrecta();
        esconderTodo();
        buttonSiguiente.setText("Siguiente");
        mostrarSiguiente();

    }

    public void checkVidasACero(){
        if (vidas == 0) {
            textView21.setText("Game Over.");
            esconderTodo();
            mostrarSiguiente();
            soundDerrota.start();
            buttonSiguiente.setText("Volver al menu");
        }
    }
    @SuppressLint("ResourceAsColor")
    public void comprobarCorrecta(View view) {
        buttonAbandonar.setVisibility(View.INVISIBLE);
        buttonAbandonar.setClickable(false);
        textViewTiempoC.setVisibility(View.VISIBLE);
        textViewTiempoCons.setVisibility(View.VISIBLE);
        soundBackground.stop();
        countDownTimer.cancel();
        timerConsolidar();
        puntosPregunta = 0;
        puntosPregunta = Integer.parseInt(textViewPuntosXPreg.getText().toString());

        if (numPregunta == 10) {
            soundVictoria.start();
            buttonSiguiente.setText("Acabar");
        }


        if (findViewById(R.id.buttonResp1).isPressed() && respuestasPreg.get(0).esCorrecta) {
            if(numPregunta < 10) soundAcierto.start();
            findViewById(R.id.buttonResp1).setBackgroundColor(0xFF008F39);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    respuestaCorrecta();
                }
            }, 5000);


        } else if (findViewById(R.id.buttonResp2).isPressed() && respuestasPreg.get(1).esCorrecta) {
            if(numPregunta < 10) soundAcierto.start();
            findViewById(R.id.buttonResp2).setBackgroundColor(0xFF008F39);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    respuestaCorrecta();
                }
            }, 5000);
        } else if (findViewById(R.id.buttonResp3).isPressed() && respuestasPreg.get(2).esCorrecta) {
            if(numPregunta < 10) soundAcierto.start();
            findViewById(R.id.buttonResp3).setBackgroundColor(0xFF008F39);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    respuestaCorrecta();
                }
            }, 5000);

        } else if (findViewById(R.id.buttonResp4).isPressed() && respuestasPreg.get(3).esCorrecta) {
            if(numPregunta < 10) soundAcierto.start();
            findViewById(R.id.buttonResp4).setBackgroundColor(0xFF008F39);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    respuestaCorrecta();
                }
            }, 5000);

        } else {
            soundFallo.start();
            int preg;
            for (preg = 0; preg < 4; preg++) {
                if (respuestasPreg.get(preg).esCorrecta) {
                    break;
                }
            }
            switch (preg){
                case 0:
                    if (findViewById(R.id.buttonResp2).isPressed()){findViewById(R.id.buttonResp2).setBackgroundColor(0xFFFF0000);}
                    else if (findViewById(R.id.buttonResp3).isPressed()){findViewById(R.id.buttonResp3).setBackgroundColor(0xFFFF0000);}
                    else if (findViewById(R.id.buttonResp4).isPressed()){findViewById(R.id.buttonResp4).setBackgroundColor(0xFFFF0000);}
                    findViewById(R.id.buttonResp1).setBackgroundColor(0xFF008F39);
                    Handler handler0 = new Handler();
                    handler0.postDelayed(new Runnable() {
                        public void run() {
                            vidas--;
                            if (puntosAcum >= puntosPregunta * 2) {
                                puntosAcum -= puntosPregunta * 2;
                            } else puntosAcum = 0;
                            buttonSiguiente.setText("Vuelve a intentarlo");
                            textView21.setText("Respuesta incorrecta.");
                            puntosCuandoCorrecta();
                            textViewPtosObtend.setText("0");

                            buttonConsolidar.setVisibility(View.INVISIBLE);
                            buttonConsolidar.setClickable(false);
                            esconderTodo();
                            mostrarSiguiente();
                            checkVidasACero();

                        }
                    }, 5000);
                    break;
                case 1:
                    if (findViewById(R.id.buttonResp1).isPressed()){findViewById(R.id.buttonResp1).setBackgroundColor(0xFFFF0000);}
                    else if (findViewById(R.id.buttonResp3).isPressed()){findViewById(R.id.buttonResp3).setBackgroundColor(0xFFFF0000);}
                    else if (findViewById(R.id.buttonResp4).isPressed()){findViewById(R.id.buttonResp4).setBackgroundColor(0xFFFF0000);}
                    findViewById(R.id.buttonResp2).setBackgroundColor(0xFF008F39);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        public void run() {
                            vidas--;
                            if (puntosAcum >= puntosPregunta * 2) {
                                puntosAcum -= puntosPregunta * 2;
                            } else puntosAcum = 0;
                            buttonSiguiente.setText("Vuelve a intentarlo");
                            textView21.setText("Respuesta incorrecta.");
                            puntosCuandoCorrecta();
                            textViewPtosObtend.setText("0");

                            buttonConsolidar.setVisibility(View.INVISIBLE);
                            buttonConsolidar.setClickable(false);
                            esconderTodo();
                            mostrarSiguiente();
                            checkVidasACero();

                        }
                    }, 5000);
                    break;
                case 2:
                    if (findViewById(R.id.buttonResp1).isPressed()){findViewById(R.id.buttonResp1).setBackgroundColor(0xFFFF0000);}
                    else if (findViewById(R.id.buttonResp2).isPressed()){findViewById(R.id.buttonResp2).setBackgroundColor(0xFFFF0000);}
                    else if (findViewById(R.id.buttonResp4).isPressed()){findViewById(R.id.buttonResp4).setBackgroundColor(0xFFFF0000);}
                    findViewById(R.id.buttonResp3).setBackgroundColor(0xFF008F39);
                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        public void run() {
                            vidas--;
                            if (puntosAcum >= puntosPregunta * 2) {
                                puntosAcum -= puntosPregunta * 2;
                            } else puntosAcum = 0;
                            buttonSiguiente.setText("Vuelve a intentarlo");
                            textView21.setText("Respuesta incorrecta.");
                            puntosCuandoCorrecta();
                            textViewPtosObtend.setText("0");

                            buttonConsolidar.setVisibility(View.INVISIBLE);
                            buttonConsolidar.setClickable(false);
                            esconderTodo();
                            mostrarSiguiente();
                            checkVidasACero();

                        }
                    }, 5000);
                    break;
                case 3:
                    if (findViewById(R.id.buttonResp1).isPressed()){findViewById(R.id.buttonResp1).setBackgroundColor(0xFFFF0000);}
                    else if (findViewById(R.id.buttonResp2).isPressed()){findViewById(R.id.buttonResp2).setBackgroundColor(0xFFFF0000);}
                    else if (findViewById(R.id.buttonResp3).isPressed()){findViewById(R.id.buttonResp3).setBackgroundColor(0xFFFF0000);}
                    findViewById(R.id.buttonResp4).setBackgroundColor(0xFF008F39);
                    Handler handler3 = new Handler();
                    handler3.postDelayed(new Runnable() {
                        public void run() {
                            vidas--;
                            if (puntosAcum >= puntosPregunta * 2) {
                                puntosAcum -= puntosPregunta * 2;
                            } else puntosAcum = 0;
                            buttonSiguiente.setText("Vuelve a intentarlo");
                            textView21.setText("Respuesta incorrecta.");
                            puntosCuandoCorrecta();
                            textViewPtosObtend.setText("0");

                            buttonConsolidar.setVisibility(View.INVISIBLE);
                            buttonConsolidar.setClickable(false);
                            esconderTodo();
                            mostrarSiguiente();
                            checkVidasACero();

                        }
                    }, 5000);
                    break;
            }

        }

    }
    public void botonAbandonar(View view){
        countDownTimer.cancel();
        puntosAcumTotales = PtosConsolidados + usuario.getPuntosAcumTotales();
        usuario.setPuntosAcumTotales(puntosAcumTotales);
        UserDAO userdao = new UserDAO();
        userdao.actualizar(usuario);
        Intent abandonarpartida = new Intent(this, AbandonarPartida.class);
        abandonarpartida.putExtra("pntsFin", PtosConsolidados);
        abandonarpartida.putExtra("user", usuario);
        startActivity(abandonarpartida);
        this.finish();
    }
    @Override
    public void onBackPressed() {}
}
