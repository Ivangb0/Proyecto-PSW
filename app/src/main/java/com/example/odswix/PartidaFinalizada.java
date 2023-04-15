package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PartidaFinalizada extends AppCompatActivity {

    Button buttonContinuarAlMenu;
    TextView textView29;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partidafinalizada);

        buttonContinuarAlMenu = (Button) findViewById(R.id.buttonContinuarAlMenu);
        textView29 = (TextView) findViewById(R.id.textView29);
        Intent intent = this.getIntent();
        textView29.setText(Integer.valueOf((int)intent.getSerializableExtra("pntsFin")));


    }

    public void irAlMenu(View view){
        Intent jugarPartida = new Intent(this, JugarPartida.class);
        startActivity(jugarPartida);
        this.finish();
    }
}
