package com.example.odswix;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import BusinessLogic.Answer;
import BusinessLogic.Question;
import BusinessLogic.User;
import Persistence.AnswerDAO;
import Persistence.QuestionDAO;
import Persistence.UserDAO;


public class RetoPregunta extends AppCompatActivity{

    int duration = 30;
    int durationCons = 15;
    int vidas = 2;
    CountDownTimer countDownTimer;
    public static int puntosAcum = 0;

    int answeredQuestions = 0;

    int answeredQuestionsFacil = 0;
    int answeredQuestionsMedio = 0;
    int answeredQuestionsDificil = 0;

    String preguntaRandom = "";

    //Uso de las instancias creadas para guardar las preguntas y respuestas de la BD en listas
    List<Question> listaPreguntas;
    List<Answer> listaRespuestas;

    List<Question> listaPreguntasFacil;
    List<Question> listaPreguntasMedio;
    List<Question> listaPreguntasDificil;



    //Lista de respuestas de la pregunta actual pregActual
    List<Answer> respuestasPreg = new ArrayList<Answer>();
    Question pregActual;


    Button botonResp1;
    Button botonResp2;
    Button botonResp3;
    Button botonResp4;
    RelativeLayout contenedorRespuesta;
    TextView textView21;
    TextView textView20;
    TextView textView26;
    TextView textView25;
    TextView textView5;
    TextView textView24;
    Button buttonPistas;
    ImageButton buttonPausa;
    ConstraintLayout idLayout;
    TextView textoPregunta;
    TextView textoDificultad;
    TextView textViewNumPreg;
    int contadorPreguntas = 1;
    TextView textViewPuntosXPreg;
    TextView textView23;
    Button buttonSiguiente;
    TextView textViewPuntAcum;
    TextView textView6;
    TextView textViewTiempo;
    TextView textView30;
    TextView textView33;
    TextView textViewVidas;
    Button buttonConsolidar;
    User usuario;
    int puntosAcumTotales = 0;
    int puntosPregunta = 0;
    int PtosConsolidados = 0;
    TextView textViewObtend;
    TextView textViewPtosObtend;
    TextView textViewPtosTots;
    TextView textViewPtosAcums;
    CountDownTimer countDownTimerCons;
    TextView textViewTiempoC;
    TextView textViewTiempoCons;
    UserDAO userdao;
    ImageView imageViewODS;

    MediaPlayer song;
    TextView textViewPuntConsol;
    TextView textViewPtosCon;
    Button buttonAbandonar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retopregunta);
        reiniciarTimer();

        //Instancias de clases AnswerDAO y QuestionDAO
        AnswerDAO answers = new AnswerDAO();
        QuestionDAO questions = new QuestionDAO();
        userdao = new UserDAO();

        listaPreguntas = questions.obtenerTodos();
        listaRespuestas = answers.obtenerTodos();

        listaPreguntasFacil = filtrarDificultad("Facil");
        listaPreguntasMedio = filtrarDificultad("Medio");
        listaPreguntasDificil = filtrarDificultad("Dificil");

        Collections.shuffle(listaPreguntasFacil, new Random());
        Collections.shuffle(listaPreguntasMedio, new Random());
        Collections.shuffle(listaPreguntasDificil, new Random());


        //asignamos a los componentes del xml variables con el texto
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
        song = MediaPlayer.create(getApplicationContext(),R.raw.goofy);
        song.start();
        pregActual = listaPreguntasFacil.get(0);
        respuestasPreg = recuperarRespuestas(listaPreguntasFacil.get(0).getIdPregunta());
        System.out.print(respuestasPreg);
        cambiarImagenODS();
        buttonAbandonar.setVisibility(View.INVISIBLE);
        buttonAbandonar.setClickable(false);


        textoPregunta.setText(pregActual.getEnunciado());
        textoDificultad.setText(pregActual.getDificultad());
        botonResp1.setText(respuestasPreg.get(0).getRespuesta());
        botonResp2.setText(respuestasPreg.get(1).getRespuesta());
        botonResp3.setText(respuestasPreg.get(2).getRespuesta());
        botonResp4.setText(respuestasPreg.get(3).getRespuesta());
        textViewNumPreg.setText("1");
        textViewPuntosXPreg.setText("100");
        buttonSiguiente.setText("Siguiente");
        textViewPuntAcum.setText("0");
        textViewTiempo.setText("30");
        textViewVidas.setText(String.valueOf(vidas));
        textView6.setText("Puntuación acumulada:");
        textViewPtosCon.setText("0");

        usuario = userdao.obtener(15);
        puntosAcumTotales = usuario.getPuntosAcumTotales();
    }

    public List<Question> filtrarDificultad(String dificultad){
        List<Question> res = new ArrayList<>();
        for(int i = 0; i<listaPreguntas.size();i++){
            if(listaPreguntas.get(i).getDificultad().equals(dificultad)){
                res.add(listaPreguntas.get(i));
            }
        }
        return res;
    }

    public List<Answer> recuperarRespuestas (int id_pregunta){
        List<Answer> resp = new ArrayList<Answer>();
        for(int i = 0; i < listaRespuestas.size();i++){
            if(listaRespuestas.get(i).getPregunta_id() == id_pregunta){
                resp.add(listaRespuestas.get(i));
            }
        }
        return resp;
    }

    public void siguientePregunta(View view){
        buttonSiguiente.setText("Siguiente");
        textViewTiempoC.setVisibility(View.INVISIBLE);
        textViewTiempoCons.setVisibility(View.INVISIBLE);
        countDownTimerCons.cancel();
        countDownTimer.cancel();
        reiniciarTimer();



        if(vidas == 0){
            this.finish();
        }

        if(answeredQuestions < 3){
            contenedorRespuesta.setVisibility(View.INVISIBLE);
            mostrarTodo();
            pregActual = listaPreguntasFacil.get(++answeredQuestionsFacil);
            preguntaRandom = pregActual.getEnunciado();

        }
        else if(answeredQuestions < 6){
            textViewPuntosXPreg.setText("200");
            contenedorRespuesta.setVisibility(View.INVISIBLE);
            mostrarTodo();
            pregActual = listaPreguntasMedio.get(answeredQuestionsMedio++);
            preguntaRandom = pregActual.getEnunciado();
        }
        else if(answeredQuestions < 10){
            textViewPuntosXPreg.setText("300");
            contenedorRespuesta.setVisibility(View.INVISIBLE);
            mostrarTodo();
            pregActual = listaPreguntasDificil.get(answeredQuestionsDificil++);
            preguntaRandom = pregActual.getEnunciado();
        }
        else if(answeredQuestions == 10){
            //resultado final del reto
            puntosAcumTotales += puntosAcum;
            usuario.setPuntosAcumTotales(puntosAcumTotales);
            userdao.actualizar(usuario);
            esconderTodo();
            Intent partidaFinalizada = new Intent(this, PartidaFinalizada.class);
            startActivity(partidaFinalizada);
            this.finish();
        }
        cambiarImagenODS();
        respuestasPreg = recuperarRespuestas(pregActual.getIdPregunta());
        textoPregunta.setText(pregActual.getEnunciado());
        textoDificultad.setText(pregActual.getDificultad());
        botonResp1.setText(respuestasPreg.get(0).getRespuesta());
        botonResp2.setText(respuestasPreg.get(1).getRespuesta());
        botonResp3.setText(respuestasPreg.get(2).getRespuesta());
        botonResp4.setText(respuestasPreg.get(3).getRespuesta());
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
            }
            @Override
            public void onFinish() {
                duration = 30;
                vidas--;
                esconderTodo();
                if(puntosAcum >= puntosPregunta*2) puntosAcum -= puntosPregunta*2;
                else puntosAcum = 0;
                textView21.setText("Se acabó el tiempo.");
                contenedorRespuesta.setVisibility(View.VISIBLE);
                botonResp1.setClickable(false);
                botonResp2.setClickable(false);
                botonResp3.setClickable(false);
                botonResp4.setClickable(false);
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

    }

    public void mostrarTodo(){
        textView20.setVisibility(View.VISIBLE);
        botonResp1.setVisibility(View.VISIBLE);
        botonResp2.setVisibility(View.VISIBLE);
        botonResp3.setVisibility(View.VISIBLE);
        botonResp4.setVisibility(View.VISIBLE);
        buttonPausa.setVisibility(View.VISIBLE);
        buttonPistas.setVisibility(View.VISIBLE);
        textView5.setVisibility(View.VISIBLE);
        textView24.setVisibility(View.VISIBLE);
        textView25.setVisibility(View.VISIBLE);
        textView26.setVisibility(View.VISIBLE);
        textViewNumPreg.setVisibility(View.VISIBLE);
        textViewPuntosXPreg.setVisibility(View.VISIBLE);
        textView23.setVisibility(View.VISIBLE);
        textView6.setVisibility(View.VISIBLE);
        textViewPuntAcum.setVisibility(View.VISIBLE);
        textViewTiempo.setVisibility(View.VISIBLE);
        textView30.setVisibility(View.VISIBLE);
        textViewVidas.setVisibility(View.VISIBLE);
        textView33.setVisibility(View.VISIBLE);
        imageViewODS.setVisibility(View.VISIBLE);
        textViewPuntConsol.setVisibility(View.VISIBLE);
        textViewPtosCon.setVisibility(View.VISIBLE);
        botonResp1.setClickable(true);
        botonResp2.setClickable(true);
        botonResp3.setClickable(true);
        botonResp4.setClickable(true);

    }

    public void pressConsolidar(View view){
        buttonConsolidar.setVisibility(View.INVISIBLE);
        buttonConsolidar.setClickable(false);
        textView5.setText("");

        buttonSiguiente.performClick();
        PtosConsolidados = puntosAcum;
        textViewPtosCon.setText(String.valueOf(PtosConsolidados));
        buttonAbandonar.setVisibility(View.VISIBLE);
        buttonAbandonar.setClickable(true);
    }

    public void cambiarImagenODS(){
        int numODS = pregActual.getOds();
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

    public void comprobarCorrecta(View view) {
        textViewTiempoC.setVisibility(View.VISIBLE);
        textViewTiempoCons.setVisibility(View.VISIBLE);
        timerConsolidar();
        puntosPregunta = 0;
        if(answeredQuestions == 9) {
            buttonSiguiente.setText("Acabar");
        }

        String stringPuntosAcum = "";
        if(pregActual.getDificultad().equals("Facil")) {
            puntosPregunta = 100;
        }
        else if(pregActual.getDificultad().equals("Medio")) {
            puntosPregunta = 200;
        }
        else if(pregActual.getDificultad().equals("Dificil")) {
            puntosPregunta = 300;
        }

        if (findViewById(R.id.buttonResp1).isPressed() && respuestasPreg.get(0).esCorrecta) {
            answeredQuestions++;
            ++contadorPreguntas;
            String stringContador = String.valueOf(contadorPreguntas);
            textViewNumPreg.setText(stringContador);
            puntosAcum += puntosPregunta;
            textView21.setText("Respuesta correcta.");
            puntosCuandoCorrecta();
            esconderTodo();
            buttonSiguiente.setText("Siguiente");

            contenedorRespuesta.setVisibility(View.VISIBLE);
            botonResp1.setClickable(false);
            botonResp2.setClickable(false);
            botonResp3.setClickable(false);
            botonResp4.setClickable(false);

        } else if (findViewById(R.id.buttonResp2).isPressed() && respuestasPreg.get(1).esCorrecta) {
            answeredQuestions++;
            ++contadorPreguntas;
            String stringContador = String.valueOf(contadorPreguntas);
            textViewNumPreg.setText(stringContador);
            puntosAcum += puntosPregunta;
            textView21.setText("Respuesta correcta.");
            puntosCuandoCorrecta();
            esconderTodo();
            buttonSiguiente.setText("Siguiente");

            contenedorRespuesta.setVisibility(View.VISIBLE);
            botonResp1.setClickable(false);
            botonResp2.setClickable(false);
            botonResp3.setClickable(false);
            botonResp4.setClickable(false);

        }
        else if (findViewById(R.id.buttonResp3).isPressed() && respuestasPreg.get(2).esCorrecta) {
            answeredQuestions++;
            ++contadorPreguntas;
            String stringContador = String.valueOf(contadorPreguntas);
            textViewNumPreg.setText(stringContador);

            puntosAcum += puntosPregunta;
            textView21.setText("Respuesta correcta.");
            puntosCuandoCorrecta();
            esconderTodo();
            buttonSiguiente.setText("Siguiente");

            contenedorRespuesta.setVisibility(View.VISIBLE);
            botonResp1.setClickable(false);
            botonResp2.setClickable(false);
            botonResp3.setClickable(false);
            botonResp4.setClickable(false);
        }
        else if (findViewById(R.id.buttonResp4).isPressed() && respuestasPreg.get(3).esCorrecta) {
            answeredQuestions++;
            ++contadorPreguntas;
            String stringContador = String.valueOf(contadorPreguntas);
            textViewNumPreg.setText(stringContador);

            puntosAcum += puntosPregunta;
            textView21.setText("Respuesta correcta.");
            puntosCuandoCorrecta();
            esconderTodo();
            buttonSiguiente.setText("Siguiente");
            contenedorRespuesta.setVisibility(View.VISIBLE);
            botonResp1.setClickable(false);
            botonResp2.setClickable(false);
            botonResp3.setClickable(false);
            botonResp4.setClickable(false);
        }
        else {
            vidas--;
            if(puntosAcum >= puntosPregunta*2) puntosAcum -= puntosPregunta*2;
            else puntosAcum = 0;
            buttonSiguiente.setText("Vuelve a intentarlo");
            textView21.setText("Respuesta incorrecta.");
            puntosCuandoCorrecta();
            textViewPtosObtend.setText("0");

            buttonConsolidar.setVisibility(View.INVISIBLE);
            buttonConsolidar.setClickable(false);
            esconderTodo();

            contenedorRespuesta.setVisibility(View.VISIBLE);
            botonResp1.setClickable(false);
            botonResp2.setClickable(false);
            botonResp3.setClickable(false);
            botonResp4.setClickable(false);
        }
        stringPuntosAcum = String.valueOf(puntosAcum);
        textViewPuntAcum.setText(stringPuntosAcum);
        textViewVidas.setText(String.valueOf(vidas));
        if(vidas == 0){
            textView21.setText("Game Over.");
            esconderTodo();
            contenedorRespuesta.setVisibility(View.VISIBLE);
            botonResp1.setClickable(false);
            botonResp2.setClickable(false);
            botonResp3.setClickable(false);
            botonResp4.setClickable(false);
            buttonSiguiente.setText("Volver al menu");

        }
    }
    public void botonAbandonar(View view){
        puntosAcumTotales += PtosConsolidados;
        usuario.setPuntosAcumTotales(puntosAcumTotales);
        userdao.actualizar(usuario);
        esconderTodo();
        Intent abandonarpartida = new Intent(this, AbandonarPartida.class);
        startActivity(abandonarpartida);
        this.finish();
    }

    public void botonPausar(){

    }


    public void onBackPressed() {
        // Código para evitar que se cierre la actividad al pulsar el botón de Atrás
    }
}
