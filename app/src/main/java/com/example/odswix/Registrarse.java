package com.example.odswix;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Registrarse extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarusuario);

        //Prueba: paso de datos
        Bundle datos = getIntent().getExtras();

        String text = datos.getString("ejemplo");
        TextView texto = findViewById(R.id.contraseña);
        texto.setText(text);
    }

    public void volver(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void registro(View vista){
        EditText textUsuario= (EditText) findViewById(R.id.usuario);
        EditText textEmail= (EditText) findViewById(R.id.email);
        textEmail.setText("Primera prueba");
    }
}