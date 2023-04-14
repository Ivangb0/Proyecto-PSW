package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

import BusinessLogic.Answer;
import BusinessLogic.Pregunta;

public class PruebaLayout extends AppCompatActivity {
    private Pregunta pregunta = null;
    private int numPregunta = 0;
    private int vidas = 0;
    private int puntosAcum = 0;
    List<Answer> respuestasPreg = new ArrayList<Answer>();

    Button botonResp1; Button botonResp2; Button botonResp3; Button botonResp4;
    RelativeLayout contenedorRespuesta; TextView textView21; TextView textView20;
    TextView textView26; TextView textView25; TextView textView5; TextView textView24;
    Button buttonPistas; ImageButton buttonPausa; ConstraintLayout idLayout;
    TextView textoPregunta; TextView textoDificultad; TextView textViewNumPreg;
    TextView textViewPuntosXPreg; TextView textView23; Button buttonSiguiente;
    TextView textViewPuntAcum; TextView textView6; TextView textViewTiempo;
    TextView textView30; TextView textView33; TextView textViewVidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retopregunta);

        Intent intent = getIntent();
        pregunta = (Pregunta) intent.getSerializableExtra("cuestion");
        numPregunta = (int) intent.getSerializableExtra("numPregunta");
        vidas = (int) intent.getSerializableExtra("vidas");
        /*if (vidas == 0) {
            Intent fin = new Intent(this, JugarPartida.class);
            finishAfterTransition();
            startActivity(fin);
        }*/
        respuestasPreg = pregunta.getRespuestas();
        puntosAcum = pregunta.getPuntosAcum();

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
        textViewPuntAcum.setText(String.valueOf(pregunta.getPuntosAcum()));
        textViewTiempo.setText(String.valueOf(pregunta.getTimer()));
        textViewVidas.setText(String.valueOf(vidas));

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
        botonResp1.setClickable(true);
        botonResp2.setClickable(true);
        botonResp3.setClickable(true);
        botonResp4.setClickable(true);
    }
    public void comprobarCorrecta(View view) {
        int puntosPregunta = 0;
        if (numPregunta == 10) {
            buttonSiguiente.setText("Acabar");
        }

        puntosPregunta = Integer.parseInt(textViewPuntosXPreg.getText().toString());

        if (findViewById(R.id.buttonResp1).isPressed() && respuestasPreg.get(0).esCorrecta) {
            puntosAcum += puntosPregunta;
            pregunta.setPuntosAcum(puntosAcum);
            textView21.setText("Respuesta correcta.");
            esconderTodo();
            mostrarSiguiente();
        } else if (findViewById(R.id.buttonResp2).isPressed() && respuestasPreg.get(1).esCorrecta) {
            puntosAcum += puntosPregunta;
            pregunta.setPuntosAcum(puntosAcum);
            textView21.setText("Respuesta correcta.");
            esconderTodo();
            mostrarSiguiente();
        } else if (findViewById(R.id.buttonResp3).isPressed() && respuestasPreg.get(2).esCorrecta) {
            puntosAcum += puntosPregunta;
            pregunta.setPuntosAcum(puntosAcum);
            textView21.setText("Respuesta correcta.");
            esconderTodo();
            mostrarSiguiente();
        } else if (findViewById(R.id.buttonResp4).isPressed() && respuestasPreg.get(3).esCorrecta) {
            puntosAcum += puntosPregunta;
            pregunta.setPuntosAcum(puntosAcum);
            textView21.setText("Respuesta correcta.");
            esconderTodo();
            mostrarSiguiente();
        } else {
            vidas--;
            if (puntosAcum >= puntosPregunta * 2) {
                puntosAcum -= puntosPregunta * 2;
                pregunta.setPuntosAcum(puntosAcum);
            }
            textView21.setText("Respuesta incorrecta.");
            esconderTodo();
            mostrarSiguiente();
        }
        if (vidas == 0) {
            textView21.setText("Te has quedado sin vidas.");
            esconderTodo();
            mostrarSiguiente();
            buttonSiguiente.setText("Volver al menu");
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

        numPregunta++;
        Intent intent = intent = new Intent(this, Gestor.class);
        intent.putExtra("numPreg", numPregunta);
        intent.putExtra("puntosAcum", puntosAcum);
        intent.putExtra("vidasPreg", vidas);
        intent.putExtra("init", false);
        finishAfterTransition();
        startActivity(intent);
    }
}
