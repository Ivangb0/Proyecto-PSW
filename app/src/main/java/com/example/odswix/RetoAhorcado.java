package com.example.odswix;

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

import androidx.appcompat.app.AppCompatActivity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import BusinessLogic.Ahorcado;
import BusinessLogic.Cobertura;
import BusinessLogic.User;
import ClasesDecorator.Dibujo;
import ClasesDecorator.DibujoBrazoDer;
import ClasesDecorator.DibujoBrazoIzq;
import ClasesDecorator.DibujoCabeza;
import ClasesDecorator.DibujoCuerda;
import ClasesDecorator.DibujoEstandar;
import ClasesDecorator.DibujoMastilHorizontal;
import ClasesDecorator.DibujoMastilVertical;
import ClasesDecorator.DibujoOjos;
import ClasesDecorator.DibujoPiernaDer;
import ClasesDecorator.DibujoPiernaIzq;
import ClasesDecorator.DibujoTronco;
import ClasesObserver.GestorEstadisticas;
import Persistence.CoberturaDAO;
import Persistence.UserDAO;


public class RetoAhorcado extends AppCompatActivity {
    private Ahorcado ahorcado;

    private int numPregunta = 0;
    private int vidas = 0;
    private int puntosAcum = 0;
    private ImageViewAhorcado imageView;
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
    int durationCons = 17;
    private User usuario;
    private int puntosAcumTotales = 0;
    private int puntosPregunta;
    private int PtosConsolidados = 0;
    private String tipo = null;
    private int porcentaje = 0;
    boolean appMuted;
    ImageButton muteButton;
    Sound sound = new Sound();

    TextView textViewNumPreg3;
    TextView textViewDificultadAho;
    TextView textViewVidas3;

    ImageView imagenOdsAhorcado;
    public static ImageView imageViewAhorcado;
    TextView textViewTiempoAhorc;
    TextView textoDescripAhorcado;
    TextView textViewRespuesta;
    TextView textViewPuntosXPreg3;
    Button buttonSiguiente;
    TextView textViewPuntosAcumAho;
    TextView textViewPtosCon3;
    Button buttonConsolidar;
    Button buttonAbandonar3;
    int idCoberturaUser;
    int numODS;
    boolean pistaPressed = false;
    Button botonLetra;
    TextView textViewTiempoC;

    TextView textView21;
    TextView textViewPtosObtend;
    RelativeLayout contenedorRespuesta;
    RelativeLayout relativeLayoutTeclado;
    TextView textViewPuntAcum3;
    TextView textViewPuntConsol3;
    TextView textViewObtend;
    TextView textViewPtosTots;
    TextView textViewPtosAcums;
    TextView textViewTiempoCons3;
    MediaPlayer soundAcierto; MediaPlayer soundFallo; MediaPlayer soundBackground;
    MediaPlayer soundCountdown10s; MediaPlayer soundVictoria; MediaPlayer soundDerrota;
    List<Cobertura> listaCoberturas = new ArrayList<Cobertura>();


    //textviews que no se modifican
    TextView textView11;
    TextView textView39;
    TextView textView42;
    TextView textView3;
    TextView textView44;
    UserDAO userdao = new UserDAO();
    String respuesta;
    char charPulsado;
    String respuestaOculta;

    private GestorEstadisticas gestorEstadisticas;

    public RetoAhorcado(){}

    int numFallos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gestorEstadisticas = new GestorEstadisticas();

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.retoahorcado);

        Intent intent = getIntent();

        imageView = findViewById(R.id.imageViewAhorcado);

        tipo = (String) intent.getSerializableExtra("tipo");

        ahorcado = (Ahorcado) intent.getSerializableExtra("cuestion");
        numPregunta = (int) intent.getSerializableExtra("numPregunta");
        vidas = (int) intent.getSerializableExtra("vidas");
        consolidado = (boolean) intent.getSerializableExtra("consolidado");
        puntosAcum = (int) intent.getSerializableExtra("pntsAcum");
        PtosConsolidados = (int) intent.getSerializableExtra("pntsCons");
        duration = ahorcado.getTimer();
        usuario = (User) intent.getSerializableExtra("user");
        appMuted = intent.getBooleanExtra("muted", false);
        asignarStats();

        textViewNumPreg3 = (TextView) findViewById(R.id.textViewNumPreg3);
        textViewDificultadAho = (TextView) findViewById(R.id.textViewDificultadAho);
        textViewVidas3 = (TextView) findViewById(R.id.textViewVidas3);
        imagenOdsAhorcado = (ImageView) findViewById(R.id.imagenOdsAhorcado);
        imageViewAhorcado = (ImageView) findViewById(R.id.imageViewAhorcado);
        textViewTiempoAhorc = (TextView) findViewById(R.id.textViewTiempoAhorc);
        textoDescripAhorcado = (TextView) findViewById(R.id.textoDescripAhorcado);
        textViewRespuesta = (TextView) findViewById(R.id.textViewRespuesta);
        textViewPuntosXPreg3 = (TextView) findViewById(R.id.textViewPuntosXPreg3);
        buttonSiguiente = (Button) findViewById(R.id.buttonSiguiente);
        textViewPuntosAcumAho = (TextView) findViewById(R.id.textViewPuntosAcumAho);
        textViewPtosCon3 = (TextView) findViewById(R.id.textViewPtosCon3);
        buttonConsolidar = (Button) findViewById(R.id.buttonConsolidar);
        buttonAbandonar3 = (Button) findViewById(R.id.buttonAbandonar3);
        textView21 = (TextView) findViewById(R.id.textView21);
        textViewPtosObtend = (TextView) findViewById(R.id.textViewPtosObtend);
        contenedorRespuesta = (RelativeLayout) findViewById(R.id.contenedorRespuesta);
        relativeLayoutTeclado = (RelativeLayout) findViewById(R.id.relativeLayoutTeclado);
        textViewPuntAcum3 = (TextView) findViewById(R.id.textViewPuntAcum3);
        textViewPuntConsol3 = (TextView) findViewById(R.id.textViewPuntConsol3);
        textView11 = (TextView) findViewById(R.id.textView11);
        textView39 = (TextView) findViewById(R.id.textView39);
        textView42 = (TextView) findViewById(R.id.textView42);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView44 = (TextView) findViewById(R.id.textView44);
        textViewObtend = (TextView) findViewById(R.id.textViewObtend);
        textViewPtosTots = (TextView) findViewById(R.id.textViewPtosTots);
        textViewPtosAcums = (TextView) findViewById(R.id.textViewPtosAcums);
        textViewTiempoCons3 = (TextView) findViewById(R.id.textViewTiempoCons3);
        textViewTiempoC = (TextView) findViewById(R.id.textViewTiempoC);
        buttonAbandonar3.setVisibility(View.INVISIBLE);
        buttonAbandonar3.setClickable(false);

        textViewNumPreg3.setText(String.valueOf(numPregunta));
        if(ahorcado.getDificultad().equals("Facil")){
            textViewPuntosXPreg3.setText("100");
        }
        else if(ahorcado.getDificultad().equals("Medio")) {
            textViewPuntosXPreg3.setText("200");
        } else if (ahorcado.getDificultad().equals("Dificil")) {
            textViewPuntosXPreg3.setText("300");
        }
        buttonSiguiente.setText("Siguiente");
        textViewPuntosAcumAho.setText(String.valueOf(puntosAcum));
        textViewTiempoAhorc.setText(String.valueOf(ahorcado.getTimer()));
        textViewVidas3.setText(String.valueOf(vidas));
        textViewDificultadAho.setText(ahorcado.getDificultad());
        textViewPtosCon3.setText(String.valueOf(PtosConsolidados));
        checkConsolidar(consolidado);
        textoDescripAhorcado.setText(ahorcado.getEnunciado());
        idCoberturaUser = usuario.getIdUser();
        textViewPuntosAcumAho.setText(String.valueOf(puntosAcum));
        textViewPtosCon3.setText(String.valueOf(PtosConsolidados));

        respuesta = ahorcado.getRespuesta();
        textViewRespuesta.setText(ocultarRespuesta());
        ocultarRespuesta();
        numFallos = 0;


        soundAcierto = sound.getSoundAcierto(this);
        soundFallo = sound.getSoundFallo(this);
        soundBackground = sound.getSoundBackground(this);
        soundCountdown10s = sound.getSoundCountdown10s(this);
        soundVictoria = sound.getSoundVictoria(this);
        soundDerrota = sound.getSoundDerrota(this);

        soundBackground.start();
        muteButton = (ImageButton) findViewById(R.id.imageMute4);

        if(appMuted) {
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

        reiniciarTimer();
        cambiarImagenODS();

        }

    public String ocultarRespuesta(){
        respuestaOculta = respuesta;
        for(int i = 0; i< respuesta.length(); i++){
            char letra = respuesta.charAt(i);
            if(letra != ' ') {
                respuestaOculta = respuestaOculta.replace(letra, '_');
            }
        }
        return respuestaOculta;
    }

    public void cambiarFotoAhorcado(){
        Dibujo dibujo = new DibujoEstandar(imageView);
        dibujo.dibujar();
        switch (numFallos) {
            case 1:
                Dibujo dibujoMastilVertical = new DibujoMastilVertical(dibujo, imageView);
                dibujoMastilVertical.dibujar();
                break;
            case 2:
                Dibujo dibujoMastilHorizontal = new DibujoMastilHorizontal(dibujo, imageView);
                dibujoMastilHorizontal.dibujar();
                break;
            case 3:
                Dibujo dibujoCuerda = new DibujoCuerda(dibujo, imageView);
                dibujoCuerda.dibujar();
                break;
            case 4:
                Dibujo dibujoCabeza = new DibujoCabeza(dibujo, imageView);
                dibujoCabeza.dibujar();
                break;
            case 5:
                Dibujo dibujoTronco = new DibujoTronco(dibujo, imageView);
                dibujoTronco.dibujar();
                break;
            case 6:
                Dibujo dibujoBrazoIzq = new DibujoBrazoIzq(dibujo, imageView);
                dibujoBrazoIzq.dibujar();
                break;
            case 7:
                Dibujo dibujoBrazoDer = new DibujoBrazoDer(dibujo, imageView);
                dibujoBrazoDer.dibujar();
                break;
            case 8:
                Dibujo dibujoPiernaIzq = new DibujoPiernaIzq(dibujo, imageView);
                dibujoPiernaIzq.dibujar();
                break;
            case 9:
                Dibujo dibujoPiernaDer = new DibujoPiernaDer(dibujo, imageView);
                dibujoPiernaDer.dibujar();
                break;
            case 10:
                Dibujo dibujoOjos = new DibujoOjos(dibujo, imageView);
                dibujoOjos.dibujar();
                break;
            default:
                break;
        }
    }

    public char letraPulsada(View view){
        if(view instanceof Button) {
            botonLetra = (Button) view;
            charPulsado = botonLetra.getText().charAt(0);
        }
        comprobarLetra();
        return charPulsado;
    }

    private void mostrarSiguiente(){
        contenedorRespuesta.setVisibility(View.VISIBLE);
        //disableButonsAnswers();
    }
    public void handlerRespIncorrecta(){
        soundBackground.stop();
        buttonSiguiente.setText("Vuelve a intentarlo");
        textView21.setText("Respuesta incorrecta.");
        puntosCuandoCorrecta();
        textViewPtosAcums.setText(String.valueOf(puntosPregunta));
        textViewPtosObtend.setText("0");
        buttonConsolidar.setVisibility(View.INVISIBLE);
        buttonConsolidar.setClickable(false);
        esconderTodo();
        mostrarSiguiente();
        checkVidasACero();
    }
public void comprobarLetra(){

    String tvr = textViewRespuesta.getText().toString();
    String result = "";
    char[] respuestaArray = tvr.toCharArray();
    if(respuesta.contains(String.valueOf(charPulsado))){
        botonLetra.setBackgroundColor(0xFF008F39);
        botonLetra.setClickable(false);
        for(int i = 0; i < respuesta.length(); i++){

            if(respuesta.charAt(i) == charPulsado){
                System.out.println("GE ENTREADO EN LE FI IF IF");
                respuestaArray[i] = charPulsado;
            }
        }
        result = new String(respuestaArray);
        System.out.println(result);
        textViewRespuesta.setText(result);
        textViewRespuesta.requestLayout();

            if(result.equals(respuesta)){
                //acabamos
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        timerConsolidar();
                        countDownTimer.cancel();
                        respuestaCorrecta();
                        guardarAciertoCobertura();

                    }
                }, 1000);

            }
    }

        else{

            if(numFallos == 9) {
                vidas--;
                handlerRespIncorrecta();
                buttonAbandonar3.setVisibility(View.INVISIBLE);
                buttonAbandonar3.setClickable(false);
                textViewTiempoC.setVisibility(View.VISIBLE);
                textViewTiempoCons3.setVisibility(View.VISIBLE);
                soundCountdown10s.stop();
                countDownTimer.cancel();
                timerConsolidar();
                puntosPregunta = Integer.parseInt(textViewPuntosXPreg3.getText().toString());

                System.out.println("HASTA AQUI HEMOS LLEGADO");
                falloStat++;
                gestorEstadisticas.notificarEstadisticasActualizadas();
                usuario.setFallos(falloStat);
                userdao.actualizar(usuario);

                guardarFalloCobertura();
                soundFallo.start();

                if (puntosAcum >= puntosPregunta * 2 || PtosConsolidados >= puntosPregunta *2) {
                    puntosAcum -= puntosPregunta * 2;
                    PtosConsolidados -= puntosPregunta*2;
                } else {
                    puntosAcum = 0;
                    PtosConsolidados = 0;
                }
            }
            else{
                botonLetra.setBackgroundColor(0xFFFF0000);
                numFallos++;
                botonLetra.setClickable(false);
                cambiarFotoAhorcado();
            }
        }
    }

    public void reiniciarTimer() {
        countDownTimer = new CountDownTimer(duration * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String time = String.format("%2d", TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished));
                        textViewTiempoAhorc.setText(time);
                    }

                });
                if(millisUntilFinished <= 11000){
                    soundBackground.stop();
                    soundCountdown10s.start();
                }

            }
            @Override
            public void onFinish() {
                duration = 30;
                vidas--;
                falloStat++;

                usuario.setFallos(falloStat);
                userdao.actualizar(usuario);

                esconderTodo();
                if(puntosAcum >= puntosPregunta*2) puntosAcum -= puntosPregunta*2;
                else puntosAcum = 0;
                textView21.setText("Se acabó el tiempo.");
                puntosCuandoCorrecta();
                textViewPtosObtend.setText("0");

                buttonConsolidar.setVisibility(View.INVISIBLE);
                buttonConsolidar.setClickable(false);
                contenedorRespuesta.setVisibility(View.VISIBLE);
                checkVidasACero();
                esconderTodo();
                timerConsolidar();
                guardarFalloCobertura();
            }
        }.start();
    }

    public void cambiarImagenODS() {
        numODS = ahorcado.getHanged().getOds();
        int pictureID = getResources().getIdentifier("ods" + numODS, "drawable", getPackageName());
        Drawable picture = getResources().getDrawable(pictureID);
        imagenOdsAhorcado.setImageDrawable(picture);
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

    private List<Cobertura> recuperarCoberturas (int id_user) {
        CoberturaDAO coberturas = new CoberturaDAO();
        listaCoberturas = coberturas.obtenerTodos();
        List<Cobertura> cober = new ArrayList<Cobertura>();
        for (int i = 0; i < listaCoberturas.size(); i++) {
            if (listaCoberturas.get(i).getId_user() == id_user) {
                cober.add(listaCoberturas.get(i));
            }
        }
        return cober;
    }

    public void timerConsolidar() {
        countDownTimerCons = new CountDownTimer(durationCons * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String time = String.format("%2d", TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished));
                        textViewTiempoCons3.setText(time);
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


    private void checkConsolidar(Boolean consolidar){
        if(consolidar){
            buttonConsolidar.setVisibility(View.INVISIBLE);
            buttonConsolidar.setClickable(false);
            buttonAbandonar3.setVisibility(View.VISIBLE);
            buttonAbandonar3.setClickable(true);
        }
    }

    public void esconderTodo(){
        relativeLayoutTeclado.setVisibility(View.INVISIBLE);
        textViewPuntAcum3.setVisibility(View.INVISIBLE);
        textViewPuntosAcumAho.setVisibility(View.INVISIBLE);
        textViewPuntConsol3.setVisibility(View.INVISIBLE);
        textViewPtosCon3.setVisibility(View.INVISIBLE);
        textViewRespuesta.setVisibility(View.INVISIBLE);
        imagenOdsAhorcado.setVisibility(View.INVISIBLE);
        muteButton.setVisibility(View.INVISIBLE);
        textoDescripAhorcado.setVisibility(View.INVISIBLE);
        imageViewAhorcado.setVisibility(View.INVISIBLE);
        buttonAbandonar3.setVisibility(View.INVISIBLE);
        textViewVidas3.setVisibility(View.INVISIBLE);
        textView11.setVisibility(View.INVISIBLE);
        textViewDificultadAho.setVisibility(View.INVISIBLE);
        textView39.setVisibility(View.INVISIBLE);
        textViewTiempoAhorc.setVisibility(View.INVISIBLE);
        textView42.setVisibility(View.INVISIBLE);
        textViewPuntosXPreg3.setVisibility(View.INVISIBLE);
        textView3.setVisibility(View.INVISIBLE);
        textViewNumPreg3.setVisibility(View.INVISIBLE);
        textView44.setVisibility(View.INVISIBLE);
    }

    public void puntosCuandoCorrecta(){
        textViewObtend.setVisibility(View.VISIBLE);
        System.out.println("ESTOS SON LOS PUNTOSPREGUNTA" + puntosPregunta);
        System.out.println("PUNTOS XPREG3 " + Integer.parseInt(textViewPuntosXPreg3.getText().toString()));
        if(pistaPressed){textViewPtosObtend.setText(String.valueOf(Integer.parseInt(textViewPuntosXPreg3.getText().toString())/2));}
        else{textViewPtosObtend.setText(String.valueOf(Integer.parseInt(textViewPuntosXPreg3.getText().toString())));}
        textViewPtosObtend.setVisibility(View.VISIBLE);
        textViewPtosTots.setVisibility(View.VISIBLE);
        textViewPtosAcums.setText(String.valueOf(puntosAcum));
        textViewPtosAcums.setVisibility(View.VISIBLE);
    }

    public void silenciarRetoFrase(View view) {
        if (appMuted) {
            appMuted = false;
            muteButton.setImageResource(R.drawable.audio_muted);
            soundCountdown10s.setVolume(0, 0);
            soundVictoria.setVolume(0, 0);
            soundFallo.setVolume(0, 0);
            soundDerrota.setVolume(0, 0);
            soundBackground.setVolume(0, 0);
            soundAcierto.setVolume(0, 0);
        } else {
            appMuted = true;
            muteButton.setImageResource(R.drawable.audio_on);
            soundCountdown10s.setVolume(1, 1);
            soundVictoria.setVolume(1, 1);
            soundFallo.setVolume(1, 1);
            soundDerrota.setVolume(1, 1);
            soundBackground.setVolume(1, 1);
            soundAcierto.setVolume(1, 1);
        }
    }

    public void botonAbandonar(View view){
        countDownTimer.cancel();
        soundBackground.stop();
        abandonedStat++;

        gestorEstadisticas.notificarEstadisticasActualizadas();
        usuario.setAbandonadas(abandonedStat);
        userdao.actualizar(usuario);

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

    public void checkVidasACero(){
        if (vidas == 0) {
            lostStat++;

            gestorEstadisticas.notificarEstadisticasActualizadas();
            usuario.setPerdidas(lostStat);
            userdao.actualizar(usuario);

            textView21.setText("Game Over.");
            esconderTodo();
            contenedorRespuesta.setVisibility(View.VISIBLE);
            buttonSiguiente.setText("Volver al menu");
            soundDerrota.start();
            soundBackground.stop();
        }
    }

    public void pressConsolidar(View view){
        PtosConsolidados = puntosAcum;
        consolidado = true;
        buttonSiguiente.performClick();
    }
    public void asignarStats() {
        aciertoStat = usuario.getAciertos();
        falloStat = usuario.getFallos();
        wonStat = usuario.getGanadas();
        lostStat = usuario.getPerdidas();
        abandonedStat = usuario.getAbandonadas();
        tiempoStat = usuario.getTiempoUso();
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
        startActivity(intent);

    }


    public void respuestaCorrecta() {
        aciertoStat++;
        usuario.setAciertos(aciertoStat);
        userdao.actualizar(usuario);

        numPregunta++;
        if(pistaPressed) {
            puntosAcum += (Integer.parseInt(textViewPuntosXPreg3.getText().toString()) / 2);
        } else{ puntosAcum += Integer.parseInt(textViewPuntosXPreg3.getText().toString()); }
        textView21.setText("Respuesta correcta.");
        soundAcierto.start();
        soundBackground.stop();
        puntosCuandoCorrecta();
        esconderTodo();
        buttonSiguiente.setText("Siguiente");
        contenedorRespuesta.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {}
}
