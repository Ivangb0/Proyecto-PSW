package com.example.odswix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import kotlin.text.Regex;

public class IniciarSesion extends AppCompatActivity {

    private EditText name;
    private EditText pass;
    private TextView errorUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        name = (EditText) findViewById(R.id.txt_username);
        pass = (EditText) findViewById(R.id.txt_password);
        errorUsername = (TextView) findViewById(R.id.txt_error);
    }

    //Método para iniciar sesión
    /*public void iniciarSesion(View view){
        //-------------------------------------------------------------------
        //IMPORTANTE, CUNADO TENGAMOS LA PANTALLA DEL JUEGO CAMBIAR EL INTENT
        //-------------------------------------------------------------------
        if(loginCorrect = 0) {
            Intent iniciarSes = new Intent(this, Registrarse.class);
            startActivity(iniciarSes);
        }else if(loginCorrect = 1){
            String error = errorUsername.getText().toString();
            error = "Contraseña incorrecta.";
        }else if(loginCorrect = 2){
            String error = errorUsername.getText().toString();
            error = "Usuario no encontrado.";
        }
    }*/

    //Método para ir a la pantalla de registrarse
    public void registrarse (View view){
        Intent registrarse = new Intent(this, Registrarse.class);
        startActivity(registrarse);
    }

    //Método para validar el inicio de sesión y si es erróneo saber el motivo
    /*public int loginCorrect(){
        String username = name.getText().toString();
        String password = pass.getText().toString();
        if (username = usernameDB){
            if(password = passwordBD){
                return 0;
            }else{
                return 1;
            }
        }else {
            return 2;
        }
    }*/
}