package com.example.odswix;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import BusinessLogic.Cobertura;
import BusinessLogic.Frase;
import BusinessLogic.Phrase;
import BusinessLogic.User;
import Persistence.CoberturaDAO;
import Persistence.UserDAO;

public class RetoFrasePrueba extends AppCompatActivity implements View.OnDragListener, View.OnTouchListener {
    private Frase tipoFrase = null;
    private String frase = "";
    private String comprFrase;
    private String modFrase;
    private String respuesta = "";
    GridLayout gridLayoutHuecos;
    GridLayout gridLayoutLetras;
    private int numPregunta = 0;
    private int vidas = 0;
    private int puntosAcum = 0;
    boolean consolidado;

    int aciertoStat;
    int falloStat;
    int wonStat;
    int lostStat;
    int abandonedStat;
    int tiempoStat;

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
    boolean appMuted; ImageButton muteButton;
    Sound sound = new Sound();
    boolean pistaPressed = false;

    UserDAO userdao = new UserDAO();



    RelativeLayout contenedorRespuesta; TextView textView21; TextView textView27;
    TextView textView28; TextView textView25; TextView enunciado; TextView textView35;
    Button buttonPistas; ConstraintLayout idLayout; Button buttonComprobar;
    TextView textoPregunta; TextView textoDificultad; TextView textViewNumPreg;
    TextView textViewPuntosXPreg; TextView textView37; Button buttonSiguiente;
    TextView textViewPuntAcum; TextView textView36; TextView textViewTiempo;
    TextView textView34; TextView textView32; TextView textViewVidas; TextView incorrectText;
    Button buttonConsolidar; TextView textViewObtend; TextView textViewPtosObtend;
    TextView textViewPtosTots; TextView textViewPtosAcums; ScrollView letras;
    TextView textViewTiempoC; TextView textViewTiempoCons; ScrollView huecos;
    TextView textViewPuntConsol; TextView textViewPtosCon; Button buttonAbandonar;
    MediaPlayer soundAcierto; MediaPlayer soundFallo; MediaPlayer soundBackground;
    MediaPlayer soundCountdown10s; MediaPlayer soundVictoria; MediaPlayer soundDerrota;

    List<Cobertura> listaCoberturas = new ArrayList<Cobertura>();
    int idCoberturaUser;
    int numODS;
    ImageView imageViewODSFrase;
    private Phrase phrase = null;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.retofrase);



        Intent intent = getIntent();

        tipo = (String) intent.getSerializableExtra("tipo");

        tipoFrase = (Frase) intent.getSerializableExtra("cuestion");
        numPregunta = (int) intent.getSerializableExtra("numPregunta");
        vidas = (int) intent.getSerializableExtra("vidas");
        consolidado = (boolean) intent.getSerializableExtra("consolidado");
        puntosAcum = (int) intent.getSerializableExtra("pntsAcum");;
        frase = tipoFrase.getRespuesta();
        PtosConsolidados = (int) intent.getSerializableExtra("pntsCons");;
        duration = tipoFrase.getTimer();
        usuario = (User) intent.getSerializableExtra("user");
        appMuted = intent.getBooleanExtra("muted", false);
        asignarStats();


        textView28 = (TextView) findViewById(R.id.textView28);
        textView27 = (TextView) findViewById(R.id.textView27);
        textViewNumPreg = (TextView) findViewById(R.id.textViewNumPreg2);
        textView35 = (TextView) findViewById(R.id.textView35);
        textView37 = (TextView) findViewById(R.id.textView37);
        textView32 = (TextView) findViewById(R.id.textView32);
        textViewPuntosXPreg = (TextView) findViewById(R.id.textViewPuntosXPreg2);
        textView34 = (TextView) findViewById(R.id.textView34);
        textViewTiempo = (TextView) findViewById(R.id.textTiempo);
        textViewVidas = (TextView) findViewById(R.id.textViewVidas2);
        buttonAbandonar = (Button) findViewById(R.id.buttonAbandonar2);
        enunciado = (TextView) findViewById(R.id.enunciado);
        letras = (ScrollView) findViewById(R.id.letras);
        huecos = (ScrollView) findViewById(R.id.huecos);
        buttonPistas = (Button) findViewById(R.id.buttonPistas2);
        buttonComprobar = (Button) findViewById(R.id.button10);
        textView36 = (TextView) findViewById(R.id.textView36);
        textViewPuntConsol = (TextView) findViewById(R.id.textViewPuntConsol2);
        textViewPuntAcum = (TextView) findViewById(R.id.textViewPuntAcum2);
        textViewPtosCon = (TextView) findViewById(R.id.textViewPtosCon2);
        imageViewODSFrase = (ImageView) findViewById(R.id.imageViewODS);
        incorrectText = (TextView) findViewById(R.id.incorrectText);


        contenedorRespuesta =  (RelativeLayout) findViewById(R.id.contenedorRespuesta);
        textViewPtosTots = (TextView) findViewById(R.id.textViewPtosTots);
        textViewPtosAcums = (TextView) findViewById(R.id.textViewPtosAcums);
        textViewObtend = (TextView) findViewById(R.id.textViewObtend);
        textViewPtosObtend = (TextView) findViewById(R.id.textViewPtosObtend);
        textView21 = (TextView) findViewById(R.id.textView21);
        buttonConsolidar = (Button) findViewById(R.id.buttonConsolidar);
        buttonSiguiente = (Button) findViewById(R.id.buttonSiguiente);
        textViewTiempoC = (TextView) findViewById(R.id.textViewTiempoC);
        textViewTiempoCons = (TextView) findViewById(R.id.textViewTiempoCons3);
        incorrectText.setVisibility(View.INVISIBLE);

        buttonAbandonar.setVisibility(View.INVISIBLE);
        buttonAbandonar.setClickable(false);

        comprFrase = frase.replace(" ", "");

        textViewNumPreg.setText(String.valueOf(numPregunta));
        if (tipoFrase.getDificultad().equals("Facil")) {
            textViewPuntosXPreg.setText("100");
            porcentaje = Math.round(comprFrase.length() * 30/100);
        } else if (tipoFrase.getDificultad().equals("Medio")) {
            textViewPuntosXPreg.setText("200");
            porcentaje = Math.round(comprFrase.length() * 20/100);
        } else if (tipoFrase.getDificultad().equals("Dificil")) {
            textViewPuntosXPreg.setText("300");
            porcentaje = Math.round(comprFrase.length() * 10/100);
        }
        buttonSiguiente.setText("Siguiente");
        textViewPuntAcum.setText(String.valueOf(puntosAcum));
        textViewTiempo.setText(String.valueOf(tipoFrase.getTimer()));
        textViewVidas.setText(String.valueOf(vidas));
        textView27.setText(tipoFrase.getDificultad());
        textViewPtosCon.setText(String.valueOf(PtosConsolidados));
        checkConsolidar(consolidado);
        enunciado.setText(tipoFrase.getEnunciado());
        idCoberturaUser =  usuario.getIdUser();

        soundAcierto = sound.getSoundAcierto(this);
        soundFallo = sound.getSoundFallo(this);
        soundBackground = sound.getSoundBackground(this);
        soundCountdown10s = sound.getSoundCountdown10s(this);
        soundVictoria = sound.getSoundVictoria(this);
        soundDerrota = sound.getSoundDerrota(this);

        soundBackground.start();
        muteButton = (ImageButton) findViewById(R.id.imageMute3);

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
        prepHuecos();
        setHuecos();
        setImages();
        reiniciarTimer();

    }

    public void asignarStats() {
        aciertoStat = usuario.getAciertos();
        falloStat = usuario.getFallos();
        wonStat = usuario.getGanadas();
        lostStat = usuario.getPerdidas();
        abandonedStat = usuario.getAbandonadas();
        tiempoStat = usuario.getTiempoUso();
    }
        private void prepHuecos(){
        modFrase = frase;
        Random random = new Random();
        for(int i = 0; i < porcentaje;) {
            int r = random.nextInt(comprFrase.length());
            String letra = String.valueOf(modFrase.charAt(r));
            if(!letra.equals("#") && !letra.equals(" ")) {
                modFrase = modFrase.replaceFirst(letra, "#");
                i++;
            }
        }
    }
    private void checkConsolidar(Boolean consolidar){
        if(consolidar){
            buttonConsolidar.setVisibility(View.INVISIBLE);
            buttonConsolidar.setClickable(false);
            buttonAbandonar.setVisibility(View.VISIBLE);
            buttonAbandonar.setClickable(true);
        }
    }
    private void setHuecos(){
        ScrollView layout = findViewById(R.id.huecos);
        gridLayoutHuecos = new GridLayout(this);
        gridLayoutHuecos.setColumnCount(9);
        for (int i = 0; i < frase.length(); i++) {

            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.height = 100;
            layoutParams.width = 100;
            layoutParams.setMargins(8, 0, 8, 8);

            String letraMod = String.valueOf(modFrase.charAt(i));
            char letra = frase.charAt(i);

            if(letraMod.equals("#")){
                Button button = new Button(this);
                button.setClickable(false);
                button.setText(Character.toString(letra));
                gridLayoutHuecos.addView(button, layoutParams);
            } else {

                ImageButton ibutton = new ImageButton(this);
                ibutton.setClickable(false);

                ibutton.setOnDragListener(this);

                gridLayoutHuecos.addView(ibutton, layoutParams);
                if (Character.isWhitespace(letra)) {
                    ibutton.setVisibility(View.INVISIBLE);
                } else {

                    ibutton.setBackgroundResource(R.drawable.hueco);
                }
            }
        }
        layout.addView(gridLayoutHuecos);
    }
    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                // Verificar si el objeto arrastrado es una letra
                if (dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    return true;
                }
                return false;
            case DragEvent.ACTION_DRAG_ENTERED:
                // Cambiar el fondo del hueco para indicar que es una posible ubicacion
                view.setBackgroundResource(R.drawable.hueco_selected);
                return true;
            case DragEvent.ACTION_DRAG_LOCATION:
                return true;
            case DragEvent.ACTION_DRAG_EXITED:
                view.setBackgroundResource(R.drawable.hueco);
                return true;
            case DragEvent.ACTION_DROP:
                View letraView = (View) dragEvent.getLocalState();
                Button letraButton = (Button) letraView;
                ImageButton huecoButton = (ImageButton) view;

                GridLayout gridLayout1 = (GridLayout)letraButton.getParent();
                GridLayout gridLayout2 = (GridLayout)huecoButton.getParent();

                if(gridLayout1 == gridLayout2){
                    return false;
                }

                int indexLetra = gridLayout1.indexOfChild(letraButton);
                int indexHueco = gridLayout2.indexOfChild(huecoButton);

                gridLayout1.removeView(letraButton);
                gridLayout2.removeView(huecoButton);

                gridLayout1.addView(huecoButton, indexLetra);
                gridLayout2.addView(letraButton, indexHueco);

                return true;

            default:
                break;
        }
        return false;
    }
    private void setImages(){
        cambiarImagenODS();
        ScrollView layout = findViewById(R.id.letras);
        gridLayoutLetras = new GridLayout(this);
        gridLayoutLetras.setColumnCount(9);

        String desFrase = desordenarFrase();
        for (int i = 0; i < desFrase.length(); i++) {
            char letra = desFrase.charAt(i);
            if(!String.valueOf(letra).equals("#")) {
                if (!Character.isWhitespace(letra)) {
                    Button button = new Button(this);
                    button.setClickable(false);
                    button.setText(Character.toString(letra));
                    button.setBackgroundColor(0xE0F33F);

                    button.setOnTouchListener(this);

                    GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                    layoutParams.height = 100;
                    layoutParams.width = 100;
                    layoutParams.setMargins(8, 0, 8, 8);

                    gridLayoutLetras.addView(button, layoutParams);
                }
            }
        }
        layout.addView(gridLayoutLetras);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent){
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData clipData = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(clipData, shadowBuilder, view, 0);
            return true;
        }
        return false;
    }
    private String desordenarFrase(){
        char[] cadena = modFrase.toCharArray();
        List<Character> lista = new ArrayList<Character>();

        for (char c : cadena) {
            lista.add(c);
        }
        Collections.shuffle(lista);
        StringBuilder sb = new StringBuilder();
        for (char c : lista) {
            sb.append(c);
        }
        String newFrase = sb.toString();
        return newFrase;
    }
    public void pressConsolidar(View view){
        PtosConsolidados = puntosAcum;
        consolidado = true;
        buttonSiguiente.performClick();
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
        incorrectText.setVisibility(View.INVISIBLE);
        textView28.setVisibility(View.INVISIBLE);
        textView27.setVisibility(View.INVISIBLE);
        textView32.setVisibility(View.INVISIBLE);
        textViewNumPreg.setVisibility(View.INVISIBLE);
        textView35.setVisibility(View.INVISIBLE);
        textView37.setVisibility(View.INVISIBLE);
        textViewPuntosXPreg.setVisibility(View.INVISIBLE);
        textView34.setVisibility(View.INVISIBLE);
        textViewTiempo.setVisibility(View.INVISIBLE);
        textViewVidas.setVisibility(View.INVISIBLE);
        buttonAbandonar.setVisibility(View.INVISIBLE);
        enunciado.setVisibility(View.INVISIBLE);
        letras.setVisibility(View.INVISIBLE);
        huecos.setVisibility(View.INVISIBLE);
        buttonPistas.setVisibility(View.INVISIBLE);
        buttonComprobar.setVisibility(View.INVISIBLE);
        textView36.setVisibility(View.INVISIBLE);
        textViewPuntConsol.setVisibility(View.INVISIBLE);
        textViewPuntAcum.setVisibility(View.INVISIBLE);
        textViewPtosCon.setVisibility(View.INVISIBLE);
        buttonAbandonar.setVisibility(View.INVISIBLE);
        buttonAbandonar.setClickable(false);
        imageViewODSFrase.setVisibility(View.INVISIBLE);
        muteButton.setVisibility(View.INVISIBLE);
    }

    public void comprobar(View view) {
        incorrectText.setVisibility(View.INVISIBLE);
        puntosPregunta = 0;
        puntosPregunta = Integer.parseInt(textViewPuntosXPreg.getText().toString());

        if (numPregunta == 10) {
            wonStat++;

            usuario.setGanadas(wonStat);
            userdao.actualizar(usuario);

            buttonSiguiente.setText("Acabar");
        }

        respuesta = "";
        for(int i = 0; i < gridLayoutHuecos.getChildCount(); i++) {
            View button = gridLayoutHuecos.getChildAt(i);
            if(button instanceof Button) {
                respuesta += ((Button) gridLayoutHuecos.getChildAt(i)).getText();
            }
        }
        if(comprFrase.equals(respuesta)) {
            guardarAciertoCobertura();
            buttonAbandonar.setVisibility(View.INVISIBLE);
            buttonAbandonar.setClickable(false);
            countDownTimer.cancel();
            respuestaCorrecta();
            timerConsolidar();
        } else{
            incorrectText.setVisibility(View.VISIBLE);
        }
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
        this.finish();
    }

    public void cambiarImagenODS() {
        numODS = tipoFrase.getPhrase().getOds();
        // numODS = pregunta.getQuestion().getOds();
        int pictureID = getResources().getIdentifier("ods" + numODS, "drawable", getPackageName());
        Drawable picture = getResources().getDrawable(pictureID);
        imageViewODSFrase.setImageDrawable(picture);
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

    public AlertDialog openDialog(){
        int puntosPista = Integer.parseInt(textViewPuntosXPreg.getText().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(RetoFrasePrueba.this);
        builder.setTitle("¿Comprar pista?")
                .setMessage("Si aciertas obtendrás " + puntosPista / 2 + " puntos")
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
                        //aqui hay que meter el codigo del metodo pistas de abajo, pero se crea un popup nuevo cuando lo cierras
                        if(usuario.getPuntosAcumTotales() >= (puntosPregunta / 2)) {

                            Random random = new Random();
                            int res = random.nextInt(modFrase.length());
                            String letra = String.valueOf(modFrase.charAt(res));

                            if (!letra.equals("#") && !letra.equals(" ")) {
                                ScrollView huecos = findViewById(R.id.huecos);
                                huecos.removeView(gridLayoutHuecos);
                                setHuecos();

                                for (int j = 0; j < frase.length(); j++) {
                                    View child = gridLayoutHuecos.getChildAt(j);
                                    if (letra.equals(Character.toString(frase.charAt(j))) && child instanceof ImageButton) {
                                        gridLayoutHuecos.removeView(child);

                                        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                                        layoutParams.height = 100;
                                        layoutParams.width = 100;
                                        layoutParams.setMargins(8, 0, 8, 8);

                                        Button button = new Button(RetoFrasePrueba.this);
                                        button.setClickable(false);
                                        button.setText(letra);
                                        button.setBackgroundColor(0xCC84C0);
                                        gridLayoutHuecos.addView(button, j, layoutParams);
                                    }
                                }
                                pistaPressed = true;
                                buttonPistas.setClickable(false);
                                buttonPistas.setBackgroundColor(0xFFA7A7A7);
                                setImagesPistas(letra);
                            } else {
                                buttonPistas.performClick();
                            }

                        }
                    }
                });
        return builder.create();
    }

    public void pistas(View view) {
        openDialog().show();

    }
    private void setImagesPistas(String letraPista){
        gridLayoutLetras.removeAllViews();
        String desFrase = desordenarFrase();
        for (int i = 0; i < desFrase.length(); i++) {
            char letra = desFrase.charAt(i);
            if(!String.valueOf(letra).equals("#")) {
                if (!Character.isWhitespace(letra) && !letraPista.equals(Character.toString(letra))) {
                    Button button = new Button(this);
                    button.setClickable(false);
                    button.setText(Character.toString(letra));
                    button.setBackgroundColor(0xE0F33F);

                    button.setOnTouchListener(this);

                    GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                    layoutParams.height = 100;
                    layoutParams.width = 100;
                    layoutParams.setMargins(8, 0, 8, 8);

                    gridLayoutLetras.addView(button, layoutParams);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {}
}
