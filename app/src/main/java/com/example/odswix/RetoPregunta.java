package com.example.odswix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Array;
import java.sql.SQLException;

import BusinessLogic.Answer;
import BusinessLogic.Question;
import Persistence.AnswerDAO;
import Persistence.QuestionDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RetoPregunta extends AppCompatActivity{



    //Uso de las instancias creadas para guardar las preguntas y respuestas de la BD en listas
    List<Question> listaPreguntas;
    List<Answer> listaRespuestas;

    //Lista de respuestas de la pregunta actual pregActual
    List<Answer> respuestasPreg = new ArrayList<Answer>();
    Question pregActual;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retopregunta);

        //Instancias de clases AnswerDAO y QuestionDAO
        AnswerDAO answers = new AnswerDAO();
        QuestionDAO questions = new QuestionDAO();

        listaPreguntas = questions.obtenerTodos();
        listaRespuestas = answers.obtenerTodos();

        System.out.print(questions.obtenerTodos());
        //guardamos una pregunta aleatoria
        Random random = new Random();
        int indiceRandom = random.nextInt(listaPreguntas.size());
        pregActual = listaPreguntas.get(indiceRandom);
        String preguntaRandom = (pregActual.getEnunciado());

        //asignamos a los componentes del xml variables con el texto
        TextView textoPregunta = findViewById(R.id.textView5);
        TextView textoDificultad = findViewById(R.id.textView20);
        Button botonResp1 = (Button) findViewById(R.id.buttonResp1);
        Button botonResp2 = (Button) findViewById(R.id.buttonResp2);
        Button botonResp3 = (Button) findViewById(R.id.buttonResp3);
        Button botonResp4 = (Button) findViewById(R.id.buttonResp4);

        respuestasPreg = recuperarRespuestas(pregActual.getIdPregunta());
        System.out.print(respuestasPreg);
        textoPregunta.setText(preguntaRandom);
        textoDificultad.setText(pregActual.getDificultad());
        botonResp1.setText(respuestasPreg.get(0).getRespuesta());
        botonResp2.setText(respuestasPreg.get(1).getRespuesta());
        botonResp3.setText(respuestasPreg.get(2).getRespuesta());
        botonResp4.setText(respuestasPreg.get(3).getRespuesta());
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


    public void botonPausar(){;}

    public void onBackPressed() {
        // Código para evitar que se cierre la actividad al pulsar el botón de Atrás
    }

}
