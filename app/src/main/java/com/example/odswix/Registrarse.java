package com.example.odswix;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Registrarse extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarusuario);
    }


    public void registro(View vista){
        EditText textUsuario= (EditText) findViewById(R.id.usuario);
        EditText textEmail= (EditText) findViewById(R.id.email);
        textEmail.setText("Primera prueba");
    }
}