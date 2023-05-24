package com.example.odswix;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import ClasesDecorator.BaseImage;
import ClasesDecorator.Image;
import ClasesDecorator.ImageDecorator;
import Persistence.CoberturaDAO;
import Persistence.UserDAO;


public class RetoAhorcado extends AppCompatActivity {
    private Ahorcado ahorcado;

    private Ahorcado tipoAhorcado = null;

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

    TextView textView21;
    TextView textViewPtosObtend;
    RelativeLayout contenedorRespuesta;
    RelativeLayout relativeLayoutTeclado;
    Button buttonPistas3;
    TextView textViewPuntAcum3;
    TextView textViewPuntConsol3;
    TextView textViewObtend;
    TextView textViewPtosTots;
    TextView textViewPtosAcums;
    TextView textViewTiempoCons;
    MediaPlayer soundAcierto; MediaPlayer soundFallo; MediaPlayer soundBackground;
    MediaPlayer soundCountdown10s; MediaPlayer soundVictoria; MediaPlayer soundDerrota;
    List<Cobertura> listaCoberturas = new ArrayList<Cobertura>();


    //textviews que no tienen importancia
    TextView textView11;
    TextView textView39;
    TextView textView42;
    TextView textView3;
    TextView textView44;
    UserDAO userdao = new UserDAO();
    String respuesta;
    char charPulsado;
    String respuestaOculta;
    String numImagenAhorcado;


    int numFallos;
    Image baseImage;
    Image decoratedImage2;
    Image decoratedImage3;
    Image decoratedImage4;
    Image decoratedImage5;
    Image decoratedImage6;
    Image decoratedImage7;
    Image decoratedImage8;
    Image decoratedImage9;
    Image decoratedImage10;
    Image decoratedImage11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.retoahorcado);

        Intent intent = getIntent();

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
        buttonPistas3 = (Button) findViewById(R.id.buttonPistas3);
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
        textViewTiempoCons = (TextView) findViewById(R.id.textViewTiempoCons);
        buttonAbandonar3.setVisibility(View.INVISIBLE);
        buttonAbandonar3.setClickable(false);


        textViewNumPreg3.setText(String.valueOf(numPregunta));
        if(ahorcado.getDificultad().equals("Facil")){
            textViewPuntosXPreg3.setText("100");
            //aqui en retofrase hay algo de porcentaje
        }
        else if(ahorcado.getDificultad().equals("Medio")) {
            textViewPuntosXPreg3.setText("200");
            //aqui tambien
        } else if (ahorcado.getDificultad().equals("Dificil")) {
            textViewPuntosXPreg3.setText("300");
            //aqui tambien
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

        //mecanica del juego
        respuesta = ahorcado.getRespuesta();
        textViewRespuesta.setText(ocultarRespuesta());
        ocultarRespuesta();


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
        //prepHuecos();
        //setHuecos();
        //setImages();
        reiniciarTimer();
        cambiarImagenODS();

        }

        /*
        posibles metodos a implementar en retoAhorcado

        metodo que coja la respuesta de la BD y la divida en un array o alguna estructura parecida estilo [H,O,L,A]
        metodo que ponga el array en su textview pero con _ en lugar de letras
        metodo que compruebe si la letra que pertenece al boton clickado esta en el array, haciendo un listener para todos los botones
            metodo que cambie el "_" por la letra cuando sea correcta y que ponga el boton de color verde
            metodo que se llame cuando se clicke una errónea, que ponga el boton en rojo y cambie la imagen por ahorcadon+1


        */

    public String ocultarRespuesta(){
        respuestaOculta = respuesta;

        //StringBuilder respuestaOcultaBuilder = new StringBuilder();

        System.out.println("ESTTAMOS OCULTANDO CON Ç____________");
        for(int i = 0; i< respuesta.length(); i++){
            char letra = respuesta.charAt(i);
            if(letra != ' ') {
                respuestaOculta = respuestaOculta.replace(letra, '_');
            }
        }
        return respuestaOculta;
    }

    //metodo del decorator
    public void cambiarFotoAhorcado(){
        baseImage = new BaseImage(R.drawable.ahorcado1);
        decoratedImage2 = new ImageDecorator(baseImage, R.drawable.ahorcado2);

        decoratedImage3 = new ImageDecorator(decoratedImage2, R.drawable.ahorcado3);
        decoratedImage4 = new ImageDecorator(decoratedImage3, R.drawable.ahorcado4);
        decoratedImage5 = new ImageDecorator(decoratedImage4, R.drawable.ahorcado5);
        decoratedImage6 = new ImageDecorator(decoratedImage5, R.drawable.ahorcado6);
        decoratedImage7 = new ImageDecorator(decoratedImage6, R.drawable.ahorcado7);
        decoratedImage8 = new ImageDecorator(decoratedImage7, R.drawable.ahorcado8);
        decoratedImage9 = new ImageDecorator(decoratedImage8, R.drawable.ahorcado9);
        decoratedImage10 = new ImageDecorator(decoratedImage9, R.drawable.ahorcado10);
        decoratedImage11 = new ImageDecorator(decoratedImage10, R.drawable.ahorcado11);

        if(numFallos == 1) {
            decoratedImage2.display();
        }
        else if(numFallos == 2){
            decoratedImage3.display();
        }
        else if(numFallos == 3){
            decoratedImage4.display();
        }
        else if(numFallos == 4){
            decoratedImage5.display();
        }
        else if(numFallos == 5){
            decoratedImage6.display();
        }
        else if(numFallos == 6){
            decoratedImage7.display();
        }
        else if(numFallos == 7){
            decoratedImage8.display();
        }
        else if(numFallos == 8){
            decoratedImage9.display();
        }
        else if(numFallos == 9){
            decoratedImage10.display();
        }
        else if(numFallos == 10){
            decoratedImage11.display();
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

    public void comprobarLetra(){
        if(respuesta.contains(String.valueOf(charPulsado))){
            botonLetra.setBackgroundColor(0xFF008F39);
            botonLetra.setClickable(false);
            for(int i = 0; i < respuesta.length(); i++){
                if(respuesta.charAt(i) == charPulsado){
                    respuestaOculta.replace("_", String.valueOf(charPulsado));
                }
            }
        }
        else{
            botonLetra.setBackgroundColor(0xFFFF0000);
            botonLetra.setClickable(false);
            cambiarFotoAhorcado();
            //numImagenAhorcado = imageViewAhorcado.
            //imageViewAhorcado.setImageDrawable(R.drawable.ahorcado);
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
        // numODS = pregunta.getQuestion().getOds();
        int pictureID = getResources().getIdentifier("ods" + numODS, "drawable", getPackageName());
        Drawable picture = getResources().getDrawable(pictureID);
        imagenOdsAhorcado.setImageDrawable(picture);
    }

    //en frase se llamaba en el metodo comprobar si estaba bien puesta la frase
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
        buttonPistas3.setVisibility(View.INVISIBLE);
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
        if(pistaPressed){textViewPtosObtend.setText(String.valueOf(puntosPregunta/2));}
        else{textViewPtosObtend.setText(String.valueOf(puntosPregunta));}
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
        startActivity(intent);
        ocultarRespuesta();
        this.finish();
    }


    //se llamara cuando se complete la palabra
    public void respuestaCorrecta() {
        aciertoStat++;

        usuario.setAciertos(aciertoStat);
        userdao.actualizar(usuario);

        numPregunta++;
        if(pistaPressed) {
            puntosAcum += (puntosPregunta / 2);
        } else{ puntosAcum += puntosPregunta; }
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
