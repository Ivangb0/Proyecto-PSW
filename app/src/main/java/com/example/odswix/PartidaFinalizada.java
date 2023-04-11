package com.example.odswix;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PartidaFinalizada extends AppCompatActivity {

    Button buttonContinuarAlMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partidafinalizada);

        buttonContinuarAlMenu = (Button) findViewById(R.id.buttonContinuarAlMenu);

    }

    public void irAlMenu(View view){
        setContentView(R.layout.jugarpartida);
    }
}
