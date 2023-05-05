package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import BusinessLogic.Cobertura;
import BusinessLogic.Question;
import BusinessLogic.User;
import Persistence.CoberturaDAO;

public class CoberturaOds extends AppCompatActivity {
    ScrollView scrollViewODS;
    TextView textViewCobertura;
    ImageView imageViewODS1;
    ProgressBar progressBarPerc1;
    TextView textViewPerc1;
    ImageView imageViewODS2;
    ProgressBar progressBarPerc2;
    TextView textViewPerc2;
    ImageView imageViewODS3;
    ProgressBar progressBarPerc3;
    TextView textViewPerc3;
    ImageView imageViewODS4;
    ProgressBar progressBarPerc4;
    TextView textViewPerc4;
    ImageView imageViewODS5;
    ProgressBar progressBarPerc5;
    TextView textViewPerc5;
    ImageView imageViewODS6;
    ProgressBar progressBarPerc6;
    TextView textViewPerc6;
    ImageView imageViewODS7;
    ProgressBar progressBarPerc7;
    TextView textViewPerc7;
    ImageView imageViewODS8;
    ProgressBar progressBarPerc8;
    TextView textViewPerc8;
    ImageView imageViewODS9;
    ProgressBar progressBarPerc9;
    TextView textViewPerc9;
    ImageView imageViewODS10;
    ProgressBar progressBarPerc10;
    TextView textViewPerc10;
    ImageView imageViewODS11;
    ProgressBar progressBarPerc11;
    TextView textViewPerc11;
    ImageView imageViewODS12;
    ProgressBar progressBarPerc12;
    TextView textViewPerc12;
    ImageView imageViewODS13;
    ProgressBar progressBarPerc13;
    TextView textViewPerc13;
    ImageView imageViewODS14;
    ProgressBar progressBarPerc14;
    TextView textViewPerc14;
    ImageView imageViewODS15;
    ProgressBar progressBarPerc15;
    TextView textViewPerc15;
    ImageView imageViewODS16;
    ProgressBar progressBarPerc16;
    TextView textViewPerc16;
    ImageView imageViewODS17;
    ProgressBar progressBarPerc17;
    TextView textViewPerc17;
    Button buttonODSVolver;

    ProgressBar[] progressBarPerc = new ProgressBar[20];
    private User usuario;

    private Cobertura cobertura;
    private List<Cobertura> listaAciertos;
    private List<Cobertura> listaFallos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.coberturaods);

        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");

        scrollViewODS = (ScrollView) findViewById(R.id.scrollViewODS);
        textViewCobertura = (TextView) findViewById(R.id.textViewCobertura);
        imageViewODS1 = (ImageView) findViewById(R.id.imageViewODS1);
        progressBarPerc[0] = (ProgressBar) findViewById(R.id.progressBarPerc1);
        textViewPerc1 = (TextView) findViewById(R.id.textViewPerc1);
        imageViewODS2 = (ImageView) findViewById(R.id.imageViewODS2);
        progressBarPerc[1] = (ProgressBar) findViewById(R.id.progressBarPerc2);
        textViewPerc2 = (TextView) findViewById(R.id.textViewPerc2);
        imageViewODS3 = (ImageView) findViewById(R.id.imageViewODS3);
        progressBarPerc[2] = (ProgressBar) findViewById(R.id.progressBarPerc3);
        textViewPerc3 = (TextView) findViewById(R.id.textViewPerc3);
        imageViewODS4 = (ImageView) findViewById(R.id.imageViewODS4);
        progressBarPerc[3] = (ProgressBar) findViewById(R.id.progressBarPerc4);
        textViewPerc4 = (TextView) findViewById(R.id.textViewPerc4);
        imageViewODS5 = (ImageView) findViewById(R.id.imageViewODS5);
        progressBarPerc[4] = (ProgressBar) findViewById(R.id.progressBarPerc5);
        textViewPerc5 = (TextView) findViewById(R.id.textViewPerc5);
        imageViewODS6 = (ImageView) findViewById(R.id.imageViewODS6);
        progressBarPerc[5] = (ProgressBar) findViewById(R.id.progressBarPerc6);
        textViewPerc6 = (TextView) findViewById(R.id.textViewPerc6);
        imageViewODS7 = (ImageView) findViewById(R.id.imageViewODS7);
        progressBarPerc[6] = (ProgressBar) findViewById(R.id.progressBarPerc7);
        textViewPerc7 = (TextView) findViewById(R.id.textViewPerc7);
        imageViewODS8 = (ImageView) findViewById(R.id.imageViewODS8);
        progressBarPerc[7] = (ProgressBar) findViewById(R.id.progressBarPerc8);
        textViewPerc8 = (TextView) findViewById(R.id.textViewPerc8);
        imageViewODS9 = (ImageView) findViewById(R.id.imageViewODS9);
        progressBarPerc[8] = (ProgressBar) findViewById(R.id.progressBarPerc9);
        textViewPerc9 = (TextView) findViewById(R.id.textViewPerc9);
        imageViewODS10 = (ImageView) findViewById(R.id.imageViewODS10);
        progressBarPerc[9] = (ProgressBar) findViewById(R.id.progressBarPerc10);
        textViewPerc10 = (TextView) findViewById(R.id.textViewPerc10);
        imageViewODS11 = (ImageView) findViewById(R.id.imageViewODS11);
        progressBarPerc[10] = (ProgressBar) findViewById(R.id.progressBarPerc11);
        textViewPerc11 = (TextView) findViewById(R.id.textViewPerc11);
        imageViewODS12 = (ImageView) findViewById(R.id.imageViewODS12);
        progressBarPerc[11] = (ProgressBar) findViewById(R.id.progressBarPerc12);
        textViewPerc12 = (TextView) findViewById(R.id.textViewPerc12);
        imageViewODS13 = (ImageView) findViewById(R.id.imageViewODS13);
        progressBarPerc[12] = (ProgressBar) findViewById(R.id.progressBarPerc13);
        textViewPerc13 = (TextView) findViewById(R.id.textViewPerc13);
        imageViewODS14 = (ImageView) findViewById(R.id.imageViewODS14);
        progressBarPerc[13] = (ProgressBar) findViewById(R.id.progressBarPerc14);
        textViewPerc14 = (TextView) findViewById(R.id.textViewPerc14);
        imageViewODS15 = (ImageView) findViewById(R.id.imageViewODS15);
        progressBarPerc[14] = (ProgressBar) findViewById(R.id.progressBarPerc15);
        textViewPerc15 = (TextView) findViewById(R.id.textViewPerc15);
        imageViewODS16 = (ImageView) findViewById(R.id.imageViewODS16);
        progressBarPerc[15] = (ProgressBar) findViewById(R.id.progressBarPerc16);
        textViewPerc16 = (TextView) findViewById(R.id.textViewPerc16);
        imageViewODS17 = (ImageView) findViewById(R.id.imageViewODS17);
        progressBarPerc[16] = (ProgressBar) findViewById(R.id.progressBarPerc17);
        textViewPerc17 = (TextView) findViewById(R.id.textViewPerc17);
        buttonODSVolver = (Button) findViewById(R.id.buttonODSVolver);


    }

    public void setProgresos() {
        CoberturaDAO coberturaDAO = new CoberturaDAO();
        //listaAciertos = coberturaDAO.getAciertos();
        listaFallos = coberturaDAO.obtenerTodos();
        for (int i = 0; i <= progressBarPerc.length; i++) {
            progressBarPerc[i].setProgress(20);

        }
    }
}