package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.User;
import ClasesStrategy.ContextoStrategy;
import ClasesStrategy.RetoMixtoStrategy;
import ClasesStrategy.RetoPreguntaStrategy;

public class CatalogoRetos extends AppCompatActivity {
    private User usuario = null;
    private String tipo = null;
    boolean init;
    boolean appMuted;

    ContextoStrategy context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.catalogo_retos);
        init = true;
        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");

        appMuted = (boolean) intent.getBooleanExtra("muted",false);
    }

    public void mixta(View view){
        context = new ContextoStrategy(new RetoMixtoStrategy());
        context.elegirTipo(this,"mixto",usuario, appMuted);
        this.finish();
    }
    public void pregunta(View view){
        context = new ContextoStrategy(new RetoPreguntaStrategy());
        context.elegirTipo(this,"pregunta",usuario, appMuted);
        this.finish();
    }
    public void frase(View view){
        context = new ContextoStrategy(new RetoMixtoStrategy());
        context.elegirTipo(this,"frase",usuario, appMuted);
        this.finish();
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
