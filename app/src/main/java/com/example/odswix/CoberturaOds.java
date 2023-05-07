package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import Persistence.CoberturaDAO;

public class CoberturaOds extends AppCompatActivity {
    private List<Double> listaAciertos;
    private List<Double> listaFallos;
    TextView[] textViewPerc;
    ProgressBar[] progressBarPerc;
    double porcentajeAciertos;
    CoberturaDAO coberturaDAOPreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coberturaods);

        coberturaDAOPreg = new CoberturaDAO();
        textViewPerc = new TextView[17];
        progressBarPerc = new ProgressBar[17];

        progressBarPerc[0] = (ProgressBar) findViewById(R.id.progressBarPerc1);
        textViewPerc[0] = (TextView) findViewById(R.id.textViewPerc1);
        progressBarPerc[1] = (ProgressBar) findViewById(R.id.progressBarPerc2);
        textViewPerc[1] = (TextView) findViewById(R.id.textViewPerc2);
        progressBarPerc[2] = (ProgressBar) findViewById(R.id.progressBarPerc3);
        textViewPerc[2] = (TextView) findViewById(R.id.textViewPerc3);
        progressBarPerc[3] = (ProgressBar) findViewById(R.id.progressBarPerc4);
        textViewPerc[3] = (TextView) findViewById(R.id.textViewPerc4);
        progressBarPerc[4] = (ProgressBar) findViewById(R.id.progressBarPerc5);
        textViewPerc[4] = (TextView) findViewById(R.id.textViewPerc5);
        progressBarPerc[5] = (ProgressBar) findViewById(R.id.progressBarPerc6);
        textViewPerc[5] = (TextView) findViewById(R.id.textViewPerc6);
        progressBarPerc[6] = (ProgressBar) findViewById(R.id.progressBarPerc7);
        textViewPerc[6] = (TextView) findViewById(R.id.textViewPerc7);
        progressBarPerc[7] = (ProgressBar) findViewById(R.id.progressBarPerc8);
        textViewPerc[7] = (TextView) findViewById(R.id.textViewPerc8);
        progressBarPerc[8] = (ProgressBar) findViewById(R.id.progressBarPerc9);
        textViewPerc[8] = (TextView) findViewById(R.id.textViewPerc9);
        progressBarPerc[9] = (ProgressBar) findViewById(R.id.progressBarPerc10);
        textViewPerc[9] = (TextView) findViewById(R.id.textViewPerc10);
        progressBarPerc[10] = (ProgressBar) findViewById(R.id.progressBarPerc11);
        textViewPerc[10] = (TextView) findViewById(R.id.textViewPerc11);
        progressBarPerc[11] = (ProgressBar) findViewById(R.id.progressBarPerc12);
        textViewPerc[11] = (TextView) findViewById(R.id.textViewPerc12);
        progressBarPerc[12] = (ProgressBar) findViewById(R.id.progressBarPerc13);
        textViewPerc[12] = (TextView) findViewById(R.id.textViewPerc13);
        progressBarPerc[13] = (ProgressBar) findViewById(R.id.progressBarPerc14);
        textViewPerc[13] = (TextView) findViewById(R.id.textViewPerc14);
        progressBarPerc[14] = (ProgressBar) findViewById(R.id.progressBarPerc15);
        textViewPerc[14] = (TextView) findViewById(R.id.textViewPerc15);
        progressBarPerc[15] = (ProgressBar) findViewById(R.id.progressBarPerc16);
        textViewPerc[15] = (TextView) findViewById(R.id.textViewPerc16);
        progressBarPerc[16] = (ProgressBar) findViewById(R.id.progressBarPerc17);
        textViewPerc[16] = (TextView) findViewById(R.id.textViewPerc17);
        setProgresos();
    }

    public void setProgresos() {
        listaAciertos = coberturaDAOPreg.getListaAciertos();
        listaFallos = coberturaDAOPreg.getListaFallos();
        for (int i = 0; i < progressBarPerc.length; i++) {
            porcentajeAciertos = Math.round(listaAciertos.get(i) / (listaAciertos.get(i) + listaFallos.get(i)) * 100);
            progressBarPerc[i].setProgress( (int) porcentajeAciertos);
            textViewPerc[i].setText(String.valueOf(porcentajeAciertos) + "%");
        }
    }

    public void volverDeCobertura(View view){
        Intent salirCobertura = new Intent(this, JugarPartida.class);
        finishAfterTransition();
        startActivity(salirCobertura);
    }
}