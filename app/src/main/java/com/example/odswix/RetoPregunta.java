package com.example.odswix;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import BusinessLogic.Answer;
import BusinessLogic.Cobertura;
import BusinessLogic.Pregunta;
import BusinessLogic.User;
import Persistence.CoberturaDAO;
import Persistence.UserDAO;

public class RetoPregunta extends AppCompatActivity {

    int aciertoStat;
    int falloStat;
    int wonStat;
    int lostStat;
    int abandonedStat;
    int tiempoStat;

    private Pregunta pregunta = null;
    private int puntosAcumTotales = 0; int puntosPregunta = 0; private int PtosConsolidados = 0;
    private String tipo = null;
    private int numPregunta = 0;
    private int vidas = 0;
    private int puntosAcum = 0;
    boolean consolidado;
    List<Answer> respuestasPreg = new ArrayList<>();
    User usuario = IniciarSesion.usuario;
    int duration; int durationCons = 21; int idCoberturaUser = usuario.getIdUser(); int numODS;
    UserDAO userdao = new UserDAO();
    List<Cobertura> listaCoberturas = new ArrayList<>();
    boolean pistaPressed = false; boolean appMuted;
    Sound sound = new Sound();
    RelativeLayout contenedorRespuesta;
    ConstraintLayout idLayout;
    CountDownTimer countDownTimerCons;
    CountDownTimer countDownTimer;
    ImageView imageViewODS;

    //TextViews
    TextView textView21; TextView textoPregunta; TextView textoDificultad; TextView textViewNumPreg;
    TextView textViewPuntosXPreg;TextView textViewPuntAcum; TextView textView6; TextView textViewTiempo;
    TextView textViewVidas; TextView textViewObtend; TextView textViewPtosObtend;
    TextView textViewPtosTots; TextView textViewPtosAcums; TextView textViewTiempoC; TextView textViewTiempoCons;
    TextView textViewPuntConsol; TextView textViewPtosCon; TextView textView33; TextView textView26;
    TextView textView30; TextView textView24; TextView textView23;
    //Buttons
    Button buttonPistas; Button botonResp1; Button botonResp2; Button botonResp3; Button botonResp4;
    Button buttonSiguiente; Button buttonConsolidar; Button buttonAbandonar;
    ImageButton buttonPausa; ImageButton muteButton;

    //MediaPlayers
    MediaPlayer soundAcierto; MediaPlayer soundFallo; MediaPlayer soundBackground;
    MediaPlayer soundCountdown10s; MediaPlayer soundVictoria; MediaPlayer soundDerrota;
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar menu desplazable
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.retopregunta);

        Intent intent = getIntent();

        tipo = (String) intent.getSerializableExtra("tipo");

        pregunta = (Pregunta) intent.getSerializableExtra("cuestion");
        numPregunta = (int) intent.getSerializableExtra("numPregunta");
        vidas = (int) intent.getSerializableExtra("vidas");
        consolidado = (boolean) intent.getSerializableExtra("consolidado");
        puntosAcum = (int) intent.getSerializableExtra("pntsAcum");
        respuestasPreg = pregunta.getRespuestas();
        PtosConsolidados = (int) intent.getSerializableExtra("pntsCons");
        duration = pregunta.getTimer();
        usuario = (User) intent.getSerializableExtra("user");
        appMuted = intent.getBooleanExtra("muted", false);
        asignarStats();
        textoPregunta = findViewById(R.id.textView5);
        textoDificultad = findViewById(R.id.textView20);

        botonResp1 = findViewById(R.id.buttonResp1);
        botonResp2 = findViewById(R.id.buttonResp2);
        botonResp3 = findViewById(R.id.buttonResp3);
        botonResp4 = findViewById(R.id.buttonResp4);
        buttonPistas = findViewById(R.id.buttonPistas);
        buttonPausa = findViewById(R.id.buttonPausa);
        contenedorRespuesta =  findViewById(R.id.contenedorRespuesta);
        textView21 = findViewById(R.id.textView21);
        textView24 = findViewById(R.id.textView24);
        textView33 = findViewById(R.id.textView33);
        textView26 = findViewById(R.id.textView26);
        textView23 = findViewById(R.id.textView23);
        textView30 = findViewById(R.id.textView30);
        idLayout = findViewById(R.id.idLayout);
        textViewNumPreg = findViewById(R.id.textViewNumPreg);
        textViewPuntosXPreg = findViewById(R.id.textViewPuntosXPreg);
        buttonSiguiente = findViewById(R.id.buttonSiguiente);
        textViewPuntAcum = findViewById(R.id.textViewPuntAcum);
        textView6 = findViewById(R.id.textView6);
        textViewTiempo = findViewById(R.id.textViewTiempo);
        textViewVidas = findViewById(R.id.textViewVidas);
        buttonConsolidar = findViewById(R.id.buttonConsolidar);
        textViewObtend = findViewById(R.id.textViewObtend);
        textViewPtosObtend = findViewById(R.id.textViewPtosObtend);
        textViewPtosTots = findViewById(R.id.textViewPtosTots);
        textViewPtosAcums = findViewById(R.id.textViewPtosAcums);
        textViewTiempoC = findViewById(R.id.textViewTiempoC);
        textViewTiempoCons = findViewById(R.id.textViewTiempoCons3);
        imageViewODS = findViewById(R.id.imageViewODS);
        textViewPuntConsol = findViewById(R.id.textViewPuntConsol);
        textViewPtosCon = findViewById(R.id.textViewPtosCon);
        buttonAbandonar = findViewById(R.id.buttonAbandonar);
        muteButton = findViewById(R.id.imageMute2);

        soundAcierto = sound.getSoundAcierto(this);
        soundFallo = sound.getSoundFallo(this);
        soundBackground = sound.getSoundBackground(this);
        soundCountdown10s = sound.getSoundCountdown10s(this);
        soundVictoria = sound.getSoundVictoria(this);
        soundDerrota = sound.getSoundDerrota(this);
        soundBackground.start();
        soundBackground.setLooping(true);

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




        buttonPistas.setClickable(false);
        buttonPistas.setBackgroundColor(0xFFA7A7A7);


        if(puntosAcum >= (puntosPregunta / 2) && puntosAcum != 0) {
            buttonPistas.setClickable(true);
            buttonPistas.setBackgroundColor(0xFF6200EE);
        }
        if (appMuted) {
            muteButton.setImageResource(R.drawable.audio_muted);
            soundBackground.setVolume(0, 0);
            soundAcierto.setVolume(0, 0);
            soundDerrota.setVolume(0, 0);
            soundFallo.setVolume(0, 0);
            soundVictoria.setVolume(0, 0);
            soundCountdown10s.setVolume(0, 0);
        } else {
            muteButton.setImageResource(R.drawable.audio_on);
            soundBackground.setVolume(1, 1);
            soundAcierto.setVolume(1, 1);
            soundDerrota.setVolume(1, 1);
            soundFallo.setVolume(1, 1);
            soundVictoria.setVolume(1, 1);
            soundCountdown10s.setVolume(1, 1);
        }
    }

    public void asignarStats(){
        aciertoStat = usuario.getAciertos();
        falloStat = usuario.getFallos();
        wonStat = usuario.getGanadas();
        lostStat = usuario.getPerdidas();
        abandonedStat = usuario.getAbandonadas();
        tiempoStat = usuario.getTiempoUso();
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
        disableButonsAnswers();
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
        intent.putExtra("tipo", tipo);
        intent.putExtra("muted", appMuted);
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
        textoDificultad.setVisibility(View.INVISIBLE);
        textView23.setVisibility(View.INVISIBLE);
        textView24.setVisibility(View.INVISIBLE);
        textView30.setVisibility(View.INVISIBLE);
        textView26.setVisibility(View.INVISIBLE);
        textView33.setVisibility(View.INVISIBLE);
        textoPregunta.setVisibility(View.INVISIBLE);
        botonResp1.setVisibility(View.INVISIBLE);
        botonResp2.setVisibility(View.INVISIBLE);
        botonResp3.setVisibility(View.INVISIBLE);
        botonResp4.setVisibility(View.INVISIBLE);
        buttonPausa.setVisibility(View.INVISIBLE);
        buttonPistas.setVisibility(View.INVISIBLE);
        textViewNumPreg.setVisibility(View.INVISIBLE);
        textViewPuntosXPreg.setVisibility(View.INVISIBLE);
        textView6.setVisibility(View.INVISIBLE);
        textViewPuntAcum.setVisibility(View.INVISIBLE);
        textViewTiempo.setVisibility(View.INVISIBLE);
        textViewVidas.setVisibility(View.INVISIBLE);
        imageViewODS.setVisibility(View.INVISIBLE);
        textViewPuntConsol.setVisibility(View.INVISIBLE);
        textViewPtosCon.setVisibility(View.INVISIBLE);
        buttonAbandonar.setVisibility(View.INVISIBLE);
        buttonAbandonar.setClickable(false);
        muteButton.setVisibility(View.INVISIBLE);
    }

    public void pressConsolidar(View view){
        PtosConsolidados = puntosAcum;
        consolidado = true;
        buttonSiguiente.performClick();
    }
    public void cambiarImagenODS(){
        numODS = pregunta.getQuestion().getOds();
        int pictureID = getResources().getIdentifier("ods" + numODS, "drawable", getPackageName());
        Drawable picture = getResources().getDrawable(pictureID);
        imageViewODS.setImageDrawable(picture);
    }
    public void puntosCuandoCorrecta(){
        textViewObtend.setVisibility(View.VISIBLE);
        if(pistaPressed){textViewPtosObtend.setText(String.valueOf(puntosPregunta/2));}
        else{textViewPtosObtend.setText(String.valueOf(puntosPregunta));}
        textViewPtosObtend.setVisibility(View.VISIBLE);
        textViewPtosTots.setVisibility(View.VISIBLE);
        textViewPtosAcums.setText(String.valueOf(puntosAcum));
        textViewPtosAcums.setVisibility(View.VISIBLE);
    }
    public void respuestaCorrecta() {
        numPregunta++;
        if(pistaPressed) {
            puntosAcum += (puntosPregunta / 2);
        } else{ puntosAcum += puntosPregunta; }
        textView21.setText("Respuesta correcta.");
        puntosCuandoCorrecta();
        esconderTodo();
        buttonSiguiente.setText("Siguiente");
        mostrarSiguiente();

    }
    public void checkVidasACero(){
        if (vidas == 0) {
            lostStat++;

            usuario.setPerdidas(lostStat);
            userdao.actualizar(usuario);

            textView21.setText("Game Over.");
            esconderTodo();
            mostrarSiguiente();
            soundDerrota.start();
            buttonSiguiente.setText("Volver al menu");
        }
    }
    public void handlerRespIncorrecta(){
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
    private List<Cobertura> recuperarCoberturas (int id_user) {
        CoberturaDAO coberturas = new CoberturaDAO();
        listaCoberturas = coberturas.obtenerTodos();
        List<Cobertura> cober = new ArrayList<>();
        for (int i = 0; i < listaCoberturas.size(); i++) {
            if (listaCoberturas.get(i).getId_user() == id_user) {
                cober.add(listaCoberturas.get(i));
            }
        }
        return cober;
    }
    public void guardarAciertoCobertura(){
        recuperarCoberturas(idCoberturaUser);
        int aciertos;
        int fallos;
        PreparedStatement psAcierto = SingletonSQL.insertar("UPDATE cobertura SET aciertos = ? WHERE id_ods = ? AND id_user = ? AND  fallos = ?");

        List<Cobertura> cobs = recuperarCoberturas(idCoberturaUser);
        for(int i = 0; i<cobs.size(); i++){
            if(cobs.get(i).getId_ods() == this.numODS){
                fallos = cobs.get(i).getFallos();
                aciertos = cobs.get(i).getAciertos() +1;
                cobs.get(i).setAciertos(aciertos);
                cobs.get(i).setFallos(fallos);

                try {
                    psAcierto.setInt(1,aciertos);
                    psAcierto.setInt(2,this.numODS);
                    psAcierto.setInt(3,usuario.getIdUser());
                    psAcierto.setInt(4,fallos);
                    psAcierto.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void guardarFalloCobertura(){
        recuperarCoberturas(idCoberturaUser);
        int fallos;
        int aciertos;
        PreparedStatement psFallo = SingletonSQL.insertar("UPDATE cobertura SET fallos = ? WHERE id_ods = ? AND id_user = ? AND aciertos = ?");
        List<Cobertura> cobs = recuperarCoberturas(idCoberturaUser);
        for(int i = 0; i<cobs.size(); i++){
            if(cobs.get(i).getId_ods() == this.numODS){
                fallos = cobs.get(i).getFallos() +1;
                aciertos = cobs.get(i).getAciertos();
                cobs.get(i).setFallos(fallos);
                cobs.get(i).setAciertos(aciertos);
                try {
                    psFallo.setInt(1,fallos);
                    psFallo.setInt(2,this.numODS);
                    psFallo.setInt(3,usuario.getIdUser());
                    psFallo.setInt(4,aciertos);
                    psFallo.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    @SuppressLint("ResourceAsColor")
    public void comprobarCorrecta(View view) {
        buttonPistas.setClickable(false);
        buttonAbandonar.setVisibility(View.INVISIBLE);
        buttonAbandonar.setClickable(false);
        textViewTiempoC.setVisibility(View.VISIBLE);
        textViewTiempoCons.setVisibility(View.VISIBLE);
        soundBackground.stop();
        soundCountdown10s.stop();
        countDownTimer.cancel();
        timerConsolidar();
        puntosPregunta = 0;
        puntosPregunta = Integer.parseInt(textViewPuntosXPreg.getText().toString());

        if (numPregunta == 10) {
            wonStat++;

            usuario.setGanadas(wonStat);
            userdao.actualizar(usuario);

            soundVictoria.start();
            buttonSiguiente.setText("Acabar");
        }



        if (botonResp1.isPressed() && respuestasPreg.get(0).esCorrecta) {
            if(numPregunta < 10) soundAcierto.start();
            botonResp1.setBackgroundColor(0xFF008F39);
            aciertoStat++;

            usuario.setAciertos(aciertoStat);
            userdao.actualizar(usuario);

            guardarAciertoCobertura();
            disableButonsAnswers();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    respuestaCorrecta();
                }
            }, 5000);

        } else if (botonResp2.isPressed() && respuestasPreg.get(1).esCorrecta) {
            if(numPregunta < 10) soundAcierto.start();
            botonResp2.setBackgroundColor(0xFF008F39);
            aciertoStat++;

            usuario.setAciertos(aciertoStat);
            userdao.actualizar(usuario);

            guardarAciertoCobertura();
            disableButonsAnswers();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    respuestaCorrecta();
                }
            }, 5000);

        } else if (botonResp3.isPressed() && respuestasPreg.get(2).esCorrecta) {
            if(numPregunta < 10) soundAcierto.start();
            botonResp3.setBackgroundColor(0xFF008F39);
            aciertoStat++;

            usuario.setAciertos(aciertoStat);
            userdao.actualizar(usuario);

            guardarAciertoCobertura();
            disableButonsAnswers();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    respuestaCorrecta();
                }
            }, 5000);

        } else if (botonResp4.isPressed() && respuestasPreg.get(3).esCorrecta) {
            if(numPregunta < 10) soundAcierto.start();
            botonResp4.setBackgroundColor(0xFF008F39);
            aciertoStat++;

            usuario.setAciertos(aciertoStat);
            userdao.actualizar(usuario);

            guardarAciertoCobertura();
            disableButonsAnswers();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    respuestaCorrecta();
                }
            }, 5000);

        } else {
            falloStat++;

            usuario.setFallos(falloStat);
            userdao.actualizar(usuario);

            guardarFalloCobertura();
            disableButonsAnswers();
            soundFallo.start();
            int preg;
            for (preg = 0; preg < 4; preg++) {
                if (respuestasPreg.get(preg).esCorrecta) {
                    break;
                }
            }
            switch (preg){
                case 0:
                    if (botonResp2.isPressed()){botonResp2.setBackgroundColor(0xFFFF0000);}
                    else if (botonResp3.isPressed()){botonResp3.setBackgroundColor(0xFFFF0000);}
                    else if (botonResp4.isPressed()){botonResp4.setBackgroundColor(0xFFFF0000);}
                    botonResp1.setBackgroundColor(0xFF008F39);
                    Handler handler0 = new Handler();
                    handler0.postDelayed(new Runnable() {
                        public void run() {
                            vidas--;
                            if (puntosAcum >= puntosPregunta * 2 || PtosConsolidados >= puntosPregunta *2) {
                                puntosAcum -= puntosPregunta * 2;
                                PtosConsolidados -= puntosPregunta*2;
                            } else {
                                puntosAcum = 0;
                                PtosConsolidados = 0;
                            }
                            handlerRespIncorrecta();
                        }
                    }, 5000);
                    break;
                case 1:
                    if (botonResp1.isPressed()){botonResp1.setBackgroundColor(0xFFFF0000);}
                    else if (botonResp3.isPressed()){botonResp3.setBackgroundColor(0xFFFF0000);}
                    else if (botonResp4.isPressed()){botonResp4.setBackgroundColor(0xFFFF0000);}
                    botonResp2.setBackgroundColor(0xFF008F39);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        public void run() {
                            vidas--;
                            if (puntosAcum >= puntosPregunta * 2 || PtosConsolidados >= puntosPregunta *2) {
                                puntosAcum -= puntosPregunta * 2;
                                PtosConsolidados -= puntosPregunta*2;
                            } else {
                                puntosAcum = 0;
                                PtosConsolidados = 0;
                            }
                            handlerRespIncorrecta();

                        }
                    }, 5000);
                    break;
                case 2:
                    if (botonResp1.isPressed()){botonResp1.setBackgroundColor(0xFFFF0000);}
                    else if (botonResp2.isPressed()){botonResp2.setBackgroundColor(0xFFFF0000);}
                    else if (botonResp4.isPressed()){botonResp4.setBackgroundColor(0xFFFF0000);}
                    botonResp3.setBackgroundColor(0xFF008F39);
                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        public void run() {
                            vidas--;
                            if (puntosAcum >= puntosPregunta * 2 || PtosConsolidados >= puntosPregunta *2) {
                                puntosAcum -= puntosPregunta * 2;
                                PtosConsolidados -= puntosPregunta*2;
                            } else {
                                puntosAcum = 0;
                                PtosConsolidados = 0;
                            }
                            handlerRespIncorrecta();
                        }
                    }, 5000);
                    break;
                case 3:
                    if (botonResp1.isPressed()){botonResp1.setBackgroundColor(0xFFFF0000);}
                    else if (botonResp2.isPressed()){botonResp2.setBackgroundColor(0xFFFF0000);}
                    else if (botonResp3.isPressed()){botonResp3.setBackgroundColor(0xFFFF0000);}
                    botonResp4.setBackgroundColor(0xFF008F39);
                    Handler handler3 = new Handler();
                    handler3.postDelayed(new Runnable() {
                        public void run() {
                            vidas--;
                            if (puntosAcum >= puntosPregunta * 2 || PtosConsolidados >= puntosPregunta *2) {
                                puntosAcum -= puntosPregunta * 2;
                                PtosConsolidados -= puntosPregunta*2;
                            } else{
                                puntosAcum = 0;
                                PtosConsolidados = 0;
                            }
                            handlerRespIncorrecta();
                        }
                    }, 5000);
                    break;
            }

        }

    }
    public void botonAbandonar(View view){
        abandonedStat++;

        usuario.setAbandonadas(abandonedStat);
        userdao.actualizar(usuario);

        countDownTimer.cancel();
        soundBackground.stop();
        puntosAcumTotales = PtosConsolidados + usuario.getPuntosAcumTotales();
        usuario.setPuntosAcumTotales(puntosAcumTotales);
        userdao.actualizar(usuario);
        Intent abandonarpartida = new Intent(this, AbandonarPartida.class);
        abandonarpartida.putExtra("pntsFin", PtosConsolidados);
        abandonarpartida.putExtra("user", usuario);
        abandonarpartida.putExtra("muted", appMuted);
        startActivity(abandonarpartida);
        this.finish();
    }
    public void disableButonsAnswers(){
        muteButton.setClickable(false);
        botonResp1.setClickable(false);
        botonResp2.setClickable(false);
        botonResp3.setClickable(false);
        botonResp4.setClickable(false);
    }
    public void silenciarRetoPregunta(View view){
        if (appMuted){
            appMuted = false;
            muteButton.setImageResource(R.drawable.audio_muted);
            soundCountdown10s.setVolume(0,0);
            soundVictoria.setVolume(0,0);
            soundFallo.setVolume(0,0);
            soundDerrota.setVolume(0,0);
            soundBackground.setVolume(0,0);
            soundAcierto.setVolume(0,0);
        }else{
            appMuted = true;
            muteButton.setImageResource(R.drawable.audio_on);
            soundCountdown10s.setVolume(1,1);
            soundVictoria.setVolume(1,1);
            soundFallo.setVolume(1,1);
            soundDerrota.setVolume(1,1);
            soundBackground.setVolume(1,1);
            soundAcierto.setVolume(1,1);
        }
    }

    public AlertDialog openDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(RetoPregunta.this);
        builder.setTitle("¿Comprar pista?")
                .setMessage("Si aciertas obtendrás " + puntosPregunta / 2 + " puntos")
                .setPositiveButton("No comprar", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {
                                //nil
                            }
                        });
                    }
                }).setNegativeButton("Comprar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(usuario.getPuntosAcumTotales() >= (puntosPregunta / 2)) {
                            buttonPistas.setClickable(false);
                            int preg;
                            for (preg = 0; preg < 4; preg++) {
                                if (respuestasPreg.get(preg).esCorrecta) {
                                    int randomizer = (int) (Math.random() * 3) + 1;
                                    switch (preg) {
                                        case 0://la 1 es correcta
                                            randomized1(randomizer);
                                            break;
                                        case 1://la 2 es correcta
                                            randomized2(randomizer);
                                            break;
                                        case 2://la 3 es correcta
                                            randomized3(randomizer);
                                            break;
                                        case 3://la 4 es correcta
                                            randomized4(randomizer);
                                            break;
                                    }
                                }
                            }
                            pistaPressed = true;
                            buttonPistas.setBackgroundColor(0xFFA7A7A7);
                        }
                    }
                });
        return builder.create();
    }
    public void botonPistas(View view){
        openDialog().show();
    }
    public void randomized1(int rand){
        switch(rand){
            case 1:
                botonResp2.setClickable(false);
                botonResp2.setBackgroundColor(0xFFA7A7A7);
                botonResp3.setClickable(false);
                botonResp3.setBackgroundColor(0xFFA7A7A7);
                break;
            case 2:
                botonResp3.setClickable(false);
                botonResp3.setBackgroundColor(0xFFA7A7A7);
                botonResp4.setClickable(false);
                botonResp4.setBackgroundColor(0xFFA7A7A7);
                break;
            case 3:
                botonResp2.setClickable(false);
                botonResp2.setBackgroundColor(0xFFA7A7A7);
                botonResp4.setClickable(false);
                botonResp4.setBackgroundColor(0xFFA7A7A7);
                break;
        }
    }
    public void randomized2(int rand){
        switch(rand){
            case 1:
                botonResp1.setClickable(false);
                botonResp1.setBackgroundColor(0xFFA7A7A7);
                botonResp3.setClickable(false);
                botonResp3.setBackgroundColor(0xFFA7A7A7);
                break;
            case 2:
                botonResp1.setClickable(false);
                botonResp1.setBackgroundColor(0xFFA7A7A7);
                botonResp4.setClickable(false);
                botonResp4.setBackgroundColor(0xFFA7A7A7);
                break;
            case 3:
                botonResp3.setClickable(false);
                botonResp3.setBackgroundColor(0xFFA7A7A7);
                botonResp4.setClickable(false);
                botonResp4.setBackgroundColor(0xFFA7A7A7);
                break;
        }
    }
    public void randomized3(int rand){
        switch(rand){
            case 1:
                botonResp1.setClickable(false);
                botonResp1.setBackgroundColor(0xFFA7A7A7);
                botonResp2.setClickable(false);
                botonResp2.setBackgroundColor(0xFFA7A7A7);
                break;
            case 2:
                botonResp1.setClickable(false);
                botonResp1.setBackgroundColor(0xFFA7A7A7);
                botonResp4.setClickable(false);
                botonResp4.setBackgroundColor(0xFFA7A7A7);
                break;
            case 3:
                botonResp2.setClickable(false);
                botonResp2.setBackgroundColor(0xFFA7A7A7);
                botonResp4.setClickable(false);
                botonResp4.setBackgroundColor(0xFFA7A7A7);
                break;
        }
    }
    public void randomized4(int rand){
        switch(rand){
            case 1:
                botonResp1.setClickable(false);
                botonResp1.setBackgroundColor(0xFFA7A7A7);
                botonResp2.setClickable(false);
                botonResp2.setBackgroundColor(0xFFA7A7A7);
                break;
            case 2:
                botonResp2.setClickable(false);
                botonResp2.setBackgroundColor(0xFFA7A7A7);
                botonResp3.setClickable(false);
                botonResp3.setBackgroundColor(0xFFA7A7A7);
                break;
            case 3:
                botonResp1.setClickable(false);
                botonResp1.setBackgroundColor(0xFFA7A7A7);
                botonResp3.setClickable(false);
                botonResp3.setBackgroundColor(0xFFA7A7A7);
                break;
        }
    }
    @Override
    public void onBackPressed() {}
}
