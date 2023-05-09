package com.example.odswix;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.User;

public class JugarPartida extends AppCompatActivity {
    private User usuario = null;
    boolean appMuted = false;
    Sound sound = new Sound();
MediaPlayer soundBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.jugarpartida);
        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");
        appMuted = intent.getBooleanExtra("muted",false);
        soundBackground = sound.getSoundBackground(this);

        soundBackground.start();
        soundBackground.setLooping(true);

        if(appMuted){
            soundBackground.setVolume(0,0);
        }else{
            soundBackground.setVolume(1,1);
        }

    }
    public void jugar (View view){
        Intent intent = new Intent(this, CatalogoRetos.class);
        intent.putExtra("user", usuario);
        intent.putExtra("muted", appMuted);
        finishAfterTransition();
        startActivity(intent);
    }
    public void configuracion (View view){
        Intent intent = new Intent(this, menu_configuracion.class);
        intent.putExtra("user", usuario);
        intent.putExtra("muted", appMuted);
        startActivity(intent);
    }
    public void perfil (View view){
        Intent perfil = new Intent(this, Perfil.class);
        perfil.putExtra("user", usuario);
        finishAfterTransition();
        startActivity(perfil);
    }
    public void cerrarSes (View view){
        Intent cerrarSes = new Intent(this, IniciarSesion.class);
        finishAfterTransition();
        startActivity(cerrarSes);
    }

    public void clickCobertura(View view){
        Intent irCobertura = new Intent(this, CoberturaOds.class);
        finishAfterTransition();
        startActivity(irCobertura);
    }
    @Override
    public void onBackPressed() {}
}


