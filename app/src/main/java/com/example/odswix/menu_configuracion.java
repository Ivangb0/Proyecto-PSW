package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.User;

public class menu_configuracion extends AppCompatActivity {
    private User usuario = null;
    boolean appMuted;
    ImageButton muteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu_configuracion);
        Intent intent = getIntent();

        muteButton = (ImageButton) findViewById(R.id.imageMute);

        appMuted = (boolean) intent.getBooleanExtra("muted", false);
        usuario = (User) intent.getSerializableExtra("user");

        if (appMuted){
            muteButton.setImageResource(R.drawable.audio_muted);
        }else{
            muteButton.setImageResource(R.drawable.audio_on);
        }
    }
    public void silenciarConfig(View view){
        if (appMuted){
            appMuted = false;
            muteButton.setImageResource(R.drawable.audio_on);

        }else{
            appMuted = true;
            muteButton.setImageResource(R.drawable.audio_muted);
        }
    }
    public void cerrarConfig(View view){
        Intent intent = new Intent(this, JugarPartida.class);
        intent.putExtra("user", usuario);
        intent.putExtra("muted", appMuted);
        finishAfterTransition();
        startActivity(intent);
    }
    public void idiomaIngles(View view){
        //IMPLEMENTAR METODO PARA CAMBIAR EL IDIOMA DE LA APP A INGLES
    }

    public void idiomaEspañol(View view){
        //IMPLEMENTAR METODO PARA CAMBIAR EL IDIOMA DE LA APP A ESPAÑOL
    }

    public void cerrarApp(View view){
        finishAffinity();
    }
    
    @Override
    public void onBackPressed() {}
}