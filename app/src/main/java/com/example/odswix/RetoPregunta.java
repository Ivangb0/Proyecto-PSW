package com.example.odswix;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import BusinessLogic.Answer;
import BusinessLogic.Question;
import Persistence.AnswerDAO;
import Persistence.QuestionDAO;


public class RetoPregunta extends AppCompatActivity{

    int puntos = 0;

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

    //barra de progreso de tiempo
    ProgressBar progressBar;
    Handler handler;
    Runnable runnable;
    int progress = 0;
    int[] colors = {Color.GREEN, Color.GREEN, Color.GREEN, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.RED, Color.RED, Color.RED};

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retopregunta);


        //Instancias de clases AnswerDAO y QuestionDAO
        AnswerDAO answers = new AnswerDAO();
        QuestionDAO questions = new QuestionDAO();

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
        textView25 = (TextView) findViewById(R.id.textView25);
        textView26 = (TextView) findViewById(R.id.textView26);
        contenedorRespuesta =  (RelativeLayout) findViewById(R.id.contenedorRespuesta);
        textView21 = (TextView) findViewById(R.id.textView21);
        idLayout = (ConstraintLayout) findViewById(R.id.idLayout);
        textViewNumPreg = (TextView) findViewById(R.id.textViewNumPreg);
        textViewPuntosXPreg = (TextView) findViewById(R.id.textViewPuntosXPreg);
        textView23 = (TextView) findViewById(R.id.textView23);

        pregActual = listaPreguntasFacil.get(0);
        respuestasPreg = recuperarRespuestas(listaPreguntasFacil.get(0).getIdPregunta());
        System.out.print(respuestasPreg);
        textoPregunta.setText(pregActual.getEnunciado());
        textoDificultad.setText(pregActual.getDificultad());
        botonResp1.setText(respuestasPreg.get(0).getRespuesta());
        botonResp2.setText(respuestasPreg.get(1).getRespuesta());
        botonResp3.setText(respuestasPreg.get(2).getRespuesta());
        botonResp4.setText(respuestasPreg.get(3).getRespuesta());
        textViewNumPreg.setText("1");
        textViewPuntosXPreg.setText("100");



        //parte de la progressbar
        progressBar = findViewById(R.id.progressBar);

        handler = new Handler();
        runnable = new Runnable(){
            @Override
            public void run(){
                if(progress < 30){
                    progress++;
                    progressBar.setProgress(progress);
                    if(progress % 10 == 0){
                        progressBar.getProgressDrawable().setColorFilter(colors[progress / 10 -1], PorterDuff.Mode.SRC_IN);
                    }
                    handler.postDelayed(this, 1000);
                }
                else {
                    if(puntos >= 100) puntos -= 100;
                    textView21.setText("Se acabó el tiempo.");
                    contenedorRespuesta.setVisibility(View.VISIBLE);
                    botonResp1.setClickable(false);
                    botonResp2.setClickable(false);
                    botonResp3.setClickable(false);
                    botonResp4.setClickable(false);
                }
            }
        };


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



        ++contadorPreguntas;
        String stringContador = String.valueOf(contadorPreguntas);
        textViewNumPreg.setText(stringContador);


        if(answeredQuestions < 3){
            contenedorRespuesta.setVisibility(View.INVISIBLE);
            mostrarTodo();
            pregActual = listaPreguntasFacil.get(answeredQuestionsFacil++);
            answeredQuestions++;
            preguntaRandom = pregActual.getEnunciado();
        }
        else if(answeredQuestions < 6){
            textViewPuntosXPreg.setText("200");
            contenedorRespuesta.setVisibility(View.INVISIBLE);
            mostrarTodo();
            pregActual = listaPreguntasMedio.get(answeredQuestionsMedio++);
            answeredQuestions++;
            preguntaRandom = pregActual.getEnunciado();
        }
        else if(answeredQuestions < 9){
            textViewPuntosXPreg.setText("300");
            contenedorRespuesta.setVisibility(View.INVISIBLE);
            mostrarTodo();
            pregActual = listaPreguntasDificil.get(answeredQuestionsDificil++);
            answeredQuestions++;
            preguntaRandom = pregActual.getEnunciado();
        }
        else if(answeredQuestions == 9){
            //resultado final del reto
            esconderTodo();
            setContentView(R.layout.partidafinalizada);
        }

        respuestasPreg = recuperarRespuestas(pregActual.getIdPregunta());
        textoPregunta.setText(pregActual.getEnunciado());
        textoDificultad.setText(pregActual.getDificultad());
        botonResp1.setText(respuestasPreg.get(0).getRespuesta());
        botonResp2.setText(respuestasPreg.get(1).getRespuesta());
        botonResp3.setText(respuestasPreg.get(2).getRespuesta());
        botonResp4.setText(respuestasPreg.get(3).getRespuesta());
    }

    public void esconderTodo(){
        textView20.setVisibility(View.INVISIBLE);
        botonResp1.setVisibility(View.INVISIBLE);
        botonResp2.setVisibility(View.INVISIBLE);
        botonResp3.setVisibility(View.INVISIBLE);
        botonResp4.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        buttonPausa.setVisibility(View.INVISIBLE);
        buttonPistas.setVisibility(View.INVISIBLE);
        textView5.setVisibility(View.INVISIBLE);
        textView24.setVisibility(View.INVISIBLE);
        textView25.setVisibility(View.INVISIBLE);
        textView26.setVisibility(View.INVISIBLE);
        textViewNumPreg.setVisibility(View.INVISIBLE);
        textViewPuntosXPreg.setVisibility(View.INVISIBLE);
        textView23.setVisibility(View.INVISIBLE);
    }
    public void mostrarTodo(){
        textView20.setVisibility(View.VISIBLE);
        botonResp1.setVisibility(View.VISIBLE);
        botonResp2.setVisibility(View.VISIBLE);
        botonResp3.setVisibility(View.VISIBLE);
        botonResp4.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        buttonPausa.setVisibility(View.VISIBLE);
        buttonPistas.setVisibility(View.VISIBLE);
        textView5.setVisibility(View.VISIBLE);
        textView24.setVisibility(View.VISIBLE);
        textView25.setVisibility(View.VISIBLE);
        textView26.setVisibility(View.VISIBLE);
        textViewNumPreg.setVisibility(View.VISIBLE);
        textViewPuntosXPreg.setVisibility(View.VISIBLE);
        textView23.setVisibility(View.VISIBLE);
        botonResp1.setClickable(true);
        botonResp2.setClickable(true);
        botonResp3.setClickable(true);
        botonResp4.setClickable(true);
    }
    public void comprobarCorrecta(View view) {


        if (findViewById(R.id.buttonResp1).isPressed() && respuestasPreg.get(0).esCorrecta) {
            puntos += 100;
            textView21.setText("Respuesta correcta.");
            esconderTodo();
            contenedorRespuesta.setVisibility(View.VISIBLE);
            botonResp1.setClickable(false);
            botonResp2.setClickable(false);
            botonResp3.setClickable(false);
            botonResp4.setClickable(false);
        } else if (findViewById(R.id.buttonResp2).isPressed() && respuestasPreg.get(1).esCorrecta) {
            puntos += 100;
            textView21.setText("Respuesta correcta.");
            esconderTodo();
            contenedorRespuesta.setVisibility(View.VISIBLE);
            botonResp1.setClickable(false);
            botonResp2.setClickable(false);
            botonResp3.setClickable(false);
            botonResp4.setClickable(false);
        }
        else if (findViewById(R.id.buttonResp3).isPressed() && respuestasPreg.get(2).esCorrecta) {
            puntos += 100;
            textView21.setText("Respuesta correcta.");
            esconderTodo();
            contenedorRespuesta.setVisibility(View.VISIBLE);
            botonResp1.setClickable(false);
            botonResp2.setClickable(false);
            botonResp3.setClickable(false);
            botonResp4.setClickable(false);
        }
        else if (findViewById(R.id.buttonResp4).isPressed() && respuestasPreg.get(3).esCorrecta) {
            puntos += 100;
            textView21.setText("Respuesta correcta.");
            esconderTodo();
            contenedorRespuesta.setVisibility(View.VISIBLE);
            botonResp1.setClickable(false);
            botonResp2.setClickable(false);
            botonResp3.setClickable(false);
            botonResp4.setClickable(false);
        }
        else {
            if(puntos >= 100) puntos -= 100;
            textView21.setText("Respuesta incorrecta.");
            esconderTodo();
            contenedorRespuesta.setVisibility(View.VISIBLE);
            botonResp1.setClickable(false);
            botonResp2.setClickable(false);
            botonResp3.setClickable(false);
            botonResp4.setClickable(false);
        }
    }

    public void botonPausar(){


    }

    public void onBackPressed() {
        // Código para evitar que se cierre la actividad al pulsar el botón de Atrás
    }

}
