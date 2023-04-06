package com.example.odswix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import BusinessLogic.User;


public class Perfil extends Activity{
    User usuario = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");

        setData(usuario);
    }
    public void setData(User user){
        TextView username = findViewById(R.id.nombreUser);
        username.setText(user.getUsername());
        TextView email = findViewById(R.id.emailUser);
        email.setText(user.getEmail());
        TextView password = findViewById(R.id.contraseñaUser);
        password.setText(user.getPassword());
        TextView score = findViewById(R.id.puntosUser);
        score.setText(String.valueOf(user.getPuntosAcum()));
    }
    public void volver (View view){
        Intent perfil = new Intent(this, JugarPartida.class);
        startActivity(perfil);
    }
}
