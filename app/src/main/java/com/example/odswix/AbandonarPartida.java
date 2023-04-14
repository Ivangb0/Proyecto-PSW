package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AbandonarPartida extends AppCompatActivity{
    TextView textView29;
    TextView textViewNotBad;
    TextView textView40;
    Button buttonContinuarAlMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abandonarpartida);

        buttonContinuarAlMenu = (Button) findViewById(R.id.buttonContinuarAlMenu);
        textView29 = (TextView) findViewById(R.id.textView29);
        textView40 = (TextView) findViewById(R.id.textView40);
        textView29.setText(String.valueOf(RetoPregunta.puntosAcum));

    }

    public void irAlMenu(View view){
        Intent jugarPartida = new Intent(this, JugarPartida.class);
        startActivity(jugarPartida);
        this.finish();
    }
}
