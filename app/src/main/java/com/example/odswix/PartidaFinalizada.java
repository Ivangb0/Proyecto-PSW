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

public class PartidaFinalizada extends AppCompatActivity {
    CatalogoRetos menu;
    Button buttonContinuarAlMenu;
    TextView textView29;
    TextView expRestante;
    public User usuario = null;
    int experiencia, nivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.partidafinalizada);

        buttonContinuarAlMenu = (Button) findViewById(R.id.buttonContinuarAlMenu);
        textView29 = (TextView) findViewById(R.id.textView29);
        expRestante = (TextView) findViewById(R.id.expRestante);
        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");
        textView29.setText(String.valueOf((int) intent.getSerializableExtra("pntsFin")) + " puntos");
        experiencia = usuario.getExperiencia();
        nivel = usuario.getNivel();
        sumarExp();
        expRestante();

    }

    public void irAlMenu(View view) {
        Intent jugarPartida = new Intent(this, JugarPartida.class);
        jugarPartida.putExtra("user", usuario);
        startActivity(jugarPartida);
        this.finish();
    }

    public void sumarExp() {

        usuario.setExperiencia(experiencia + 20);
        if (experiencia == 100 && nivel < 3) {
            usuario.setExperiencia(0);
            menu.actualizarEstado();
            usuario.setNivel(nivel + 1);
        }
    }
    public void expRestante(){
        if(nivel < 3) {
            String textExpRest = "Experiencia restante para llegar \n al nivel " + (nivel + 1) + " : " + (100 - experiencia) + " puntos";
            expRestante.setText(textExpRest);
        }
    }

    @Override
    public void onBackPressed() {}
}
