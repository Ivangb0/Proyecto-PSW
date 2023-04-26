package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.User;

public class Armario extends AppCompatActivity {
    User usuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.armario);

        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");

        setData(usuario);
    }

    private void setData(User user){
        TextView medallas = findViewById(R.id.medallas);
        medallas.setText(String.valueOf(user.getMedallas()));
        TextView trofeos = findViewById(R.id.trofeos);
        trofeos.setText(String.valueOf(user.getTrofeos()));
    }

    public void volver (View view){
        Intent perfil = new Intent(this, Perfil.class);
        perfil.putExtra("user", usuario);
        finishAfterTransition();
        startActivity(perfil);
    }
    @Override
    public void onBackPressed() {}
}
