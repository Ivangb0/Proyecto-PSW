package com.example.odswix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import BusinessLogic.User;

public class menu_configuracion extends AppCompatActivity {
    TextView txtVol;
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
        appMuted = (Boolean) intent.getSerializableExtra("muted");

        txtVol = findViewById(R.id.textViewVol);
        muteButton = (ImageButton) findViewById(R.id.imageMute);
        muteButton.setImageResource(R.drawable.audio_muted);

        Toast.makeText(this,"" + appMuted, Toast.LENGTH_LONG);
    }
    public void silenciarConfig(View view){
        if (appMuted){
            appMuted = false;
            muteButton.setImageResource(R.drawable.audio_muted);

        }else{
            appMuted = true;
            muteButton.setImageResource(R.drawable.audio_on);
        }
        Toast.makeText(this,"" + appMuted, Toast.LENGTH_LONG);
    }
    public void cerrarConfig(View view){
        Intent intent = new Intent(this, JugarPartida.class);
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
}