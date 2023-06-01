package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;
import java.util.List;

import BusinessLogic.User;
import Persistence.CoberturaDAO;

import android.content.Intent;
import android.net.Uri;

public class CoberturaOds extends AppCompatActivity {
    private List<Double> listaAciertos;
    private List<Double> listaFallos;
    TextView[] textViewPerc;
    ProgressBar[] progressBarPerc;
    double porcentajeAciertos;
    CoberturaDAO coberturaDAOPreg;
    private User user;

    ImageView[] imageODS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coberturaods);
        Intent intent = getIntent();
        coberturaDAOPreg = new CoberturaDAO();
        textViewPerc = new TextView[17];
        progressBarPerc = new ProgressBar[17];
        user = (User) intent.getSerializableExtra("user");

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

        imageODS = new ImageView[17];

        /*for(int i = 0; i<18; i++){
            int resourceId = getResources().getIdentifier("imageViewODS" + i, "id", getPackageName());
            imageODS[i] = (ImageView) findViewById(resourceId);
        }*/

        imageODS[0] = (ImageView) findViewById(R.id.imageViewODS1);
        imageODS[1] = (ImageView) findViewById(R.id.imageViewODS2);
        imageODS[2] = (ImageView) findViewById(R.id.imageViewODS3);
        imageODS[3] = (ImageView) findViewById(R.id.imageViewODS4);
        imageODS[4] = (ImageView) findViewById(R.id.imageViewODS5);
        imageODS[5] = (ImageView) findViewById(R.id.imageViewODS6);
        imageODS[6] = (ImageView) findViewById(R.id.imageViewODS7);
        imageODS[7] = (ImageView) findViewById(R.id.imageViewODS8);
        imageODS[8] = (ImageView) findViewById(R.id.imageViewODS9);
        imageODS[9] = (ImageView) findViewById(R.id.imageViewODS10);
        imageODS[10] = (ImageView) findViewById(R.id.imageViewODS11);
        imageODS[11] = (ImageView) findViewById(R.id.imageViewODS12);
        imageODS[12] = (ImageView) findViewById(R.id.imageViewODS13);
        imageODS[13] = (ImageView) findViewById(R.id.imageViewODS14);
        imageODS[14] = (ImageView) findViewById(R.id.imageViewODS15);
        imageODS[15] = (ImageView) findViewById(R.id.imageViewODS16);
        imageODS[16] = (ImageView) findViewById(R.id.imageViewODS17);
    }

    public void setProgresos() {
        listaAciertos = coberturaDAOPreg.getListaAciertos();
        listaFallos = coberturaDAOPreg.getListaFallos();
        for (int i = 0; i < progressBarPerc.length; i++) {
            porcentajeAciertos = Math.round(listaAciertos.get(i) / (listaAciertos.get(i) + listaFallos.get(i)) * 100);
            progressBarPerc[i].setProgress((int) porcentajeAciertos);
            textViewPerc[i].setText(String.valueOf(porcentajeAciertos) + "%");
        }
    }

    public void volverDeCobertura(View view) {
        Intent salirCobertura = new Intent(this, JugarPartida.class);
        salirCobertura.putExtra("user", user);
        finishAfterTransition();
        startActivity(salirCobertura);

    }

    private void openOdsURL(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
    public void clickOds1(View view){
        openOdsURL("https://www.un.org/sustainabledevelopment/es/poverty/");
    }

    public void clickOds2(View view){
        openOdsURL("https://www.un.org/sustainabledevelopment/es/hunger/");
        }
    public void clickOds3(View view){
        openOdsURL("https://www.un.org/sustainabledevelopment/es/health/");
    }
    public void clickOds4(View view){
        openOdsURL("https://www.un.org/sustainabledevelopment/es/education/");
    }
    public void clickOds5(View view){
        openOdsURL("https://www.un.org/sustainabledevelopment/es/gender-equality/");
    }
    public void clickOds6(View view){
        openOdsURL("https://www.un.org/sustainabledevelopment/es/water-and-sanitation/");
    }
    public void clickOds7(View view){
        openOdsURL("https://www.un.org/sustainabledevelopment/es/energy/");
    }
    public void clickOds8(View view){
        openOdsURL("https://www.un.org/sustainabledevelopment/es/economic-growth/");
    }
    public void clickOds9(View view){
        openOdsURL("https://www.un.org/sustainabledevelopment/es/infrastructure/");
    }
    public void clickOds10(View view){
        openOdsURL("https://www.un.org/sustainabledevelopment/es/inequality/");
    }
    public void clickOds11(View view){
        openOdsURL("https://www.un.org/sustainabledevelopment/es/cities/");
    }
    public void clickOds12(View view){
        openOdsURL("https://www.un.org/sustainabledevelopment/es/sustainable-consumption-production/");
    }
    public void clickOds13(View view){
        openOdsURL("https://www.un.org/sustainabledevelopment/es/climate-change-2/");
    }
    public void clickOds14(View view){
        openOdsURL("https://www.un.org/sustainabledevelopment/es/oceans/");
    }
    public void clickOds15(View view){
        openOdsURL("https://www.un.org/sustainabledevelopment/es/biodiversity/");
    }
    public void clickOds16(View view){
        openOdsURL("https://www.un.org/sustainabledevelopment/es/peace-justice/");
    }
    public void clickOds17(View view){
        openOdsURL("https://www.un.org/sustainabledevelopment/es/globalpartnerships/");
    }
}