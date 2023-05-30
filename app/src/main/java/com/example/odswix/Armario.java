package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.User;

public class Armario extends AppCompatActivity {
    User usuario = null;
    TextView tv_medallas;
    Boolean appMuted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.armario);

        tv_medallas = findViewById(R.id.medallas);

        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");
        appMuted = intent.getBooleanExtra("muted", false);
        setData();
    }

    private void setData(){
        int ganadas = usuario.ganadas;
        int medallas = ganadas / 5;
        tv_medallas.setText(String.valueOf(medallas));
    }

    public void volver (View view){
        Intent perfil = new Intent(this, Perfil.class);
        perfil.putExtra("user", usuario);
        perfil.putExtra("muted", appMuted);
        finishAfterTransition();
        startActivity(perfil);
    }
    @Override
    public void onBackPressed() {}
}
