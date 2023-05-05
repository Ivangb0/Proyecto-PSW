package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.User;

public class AbandonarPartida extends AppCompatActivity{
    TextView textView29;
    public User usuario = null;
    TextView textView40;
    Button buttonContinuarAlMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.abandonarpartida);
        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");
        buttonContinuarAlMenu = (Button) findViewById(R.id.buttonContinuarAlMenu);
        textView29 = (TextView) findViewById(R.id.textView29);
        textView40 = (TextView) findViewById(R.id.textView40);
        textView29.setText(String.valueOf((int)intent.getSerializableExtra("pntsFin")));

    }

    public void irAlMenu(View view){
        Intent jugarPartida = new Intent(this, JugarPartida.class);
        jugarPartida.putExtra("user", usuario);
        startActivity(jugarPartida);
        startActivity(jugarPartida);
        this.finish();
    }
}
