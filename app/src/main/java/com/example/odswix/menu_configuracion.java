package com.example.odswix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class menu_configuracion extends AppCompatActivity {
    SeekBar seekBar;
    TextView txtVol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_configuracion);

        seekBar = findViewById(R.id.seekBarVol);
        txtVol = findViewById(R.id.textViewVol);

        seekBar.setMax(10);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //IMPLEMENTAR EL VOLUMEN EN FUNCION A LA i QUE ES EL NIVEL DEL SEEKBAR
                txtVol.setText("Volumen: " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void cerrarConfig(){
        finish();
    }
    public void idiomaIngles(){
        //IMPLEMENTAR METODO PARA CAMBIAR EL IDIOMA DE LA APP A INGLES
    }

    public void idiomaEspañol(){
        //IMPLEMENTAR METODO PARA CAMBIAR EL IDIOMA DE LA APP A ESPAÑOL
    }

    public void cerrarApp(){
        finishAffinity();
    }
}