package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.User;

public class CatalogoRetos extends AppCompatActivity {
    private User usuario = null;
    private String tipo = null;
    boolean init;
    boolean appMuted;

    public static Button botonPregunta;
    public static Button botonFrase;
    public static Button botonMixto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        botonPregunta = (Button) findViewById(R.id.button9);
        botonFrase = (Button) findViewById(R.id.button17);
        botonMixto = (Button) findViewById(R.id.button20);

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.catalogo_retos);
        init = true;
        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");

        appMuted = (boolean) intent.getSerializableExtra("muted");
        Toast.makeText(this,"" + appMuted, Toast.LENGTH_LONG).show();
    }
    public void mixta(View view){
        Intent intent = new Intent(this, Gestor.class);
        tipo = "mixta";
        intent.putExtra("tipo",tipo);
        intent.putExtra("init", init);
        intent.putExtra("user", usuario);
        finishAfterTransition();
        startActivity(intent);
    }
    public void pregunta(View view){
        Intent intent = new Intent(this, Gestor.class);
        tipo = "pregunta";
        intent.putExtra("tipo",tipo);
        intent.putExtra("init", init);
        intent.putExtra("user", usuario);
        intent.putExtra("muted", appMuted);
        finishAfterTransition();
        startActivity(intent);
    }
    public void frase(View view){
        Intent intent = new Intent(this, Gestor.class);
        tipo = "frase";
        intent.putExtra("tipo",tipo);
        intent.putExtra("init", init);
        intent.putExtra("user", usuario);
        finishAfterTransition();
        startActivity(intent);
    }
    public void cancelar(View view){
        Intent intent = new Intent(this, JugarPartida.class);
        intent.putExtra("user", usuario);
        finishAfterTransition();
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {}
}
