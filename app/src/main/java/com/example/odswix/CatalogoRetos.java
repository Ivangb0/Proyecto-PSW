package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.User;

public class CatalogoRetos extends AppCompatActivity {
    private User usuario = null;
    private String tipo = null;
    boolean init;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogo_retos);
        init = true;
        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");
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
