package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IniciarSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);


    }

    public void onBackPressed() {
        // Código para evitar que se cierre la actividad al pulsar el botón de Atrás
    }

    public boolean isEmptyTest() {
        TextView msgError = findViewById(R.id.txt_error);
        EditText name = findViewById(R.id.txt_username);
        EditText pass = findViewById(R.id.txt_password);

        boolean todoOK = false;

        if (name.getText().toString().isEmpty() ||
                pass.getText().toString().isEmpty()) {
            msgError.setText("Hay algún campo vacío, revise las credenciales");
            msgError.setVisibility(View.VISIBLE);
        } else {
            todoOK = true;
            msgError.setVisibility(View.INVISIBLE);
        }
        return todoOK;
    }

    public boolean usernameTest() {
        EditText textUser = findViewById(R.id.usuario);
        EditText textPass = findViewById(R.id.txt_password);
        String bduser = textUser.getText().toString();
        String pass = textPass.getText().toString();
        List<String> sql = new ArrayList<>();
        String password;
        boolean correcto = false;

        try {
            ResultSet rsUser = SingletonSQL.consultar("SELECT username FROM user");

            while (rsUser.next()) {
                sql.add(rsUser.getString("username"));
            }

            boolean existe = false;
            for (int i = 0; i < sql.size(); i++) {
                if (bduser.equals(sql.get(i))) {
                    existe = true;
                    break;
                }
            }

            TextView msgError = findViewById(R.id.Euser);
            if (!existe) {
                msgError.setText("No existe el usuario introducido");
                msgError.setVisibility(View.VISIBLE);
            } else {
                //correcto = true;
                msgError.setVisibility(View.INVISIBLE);
                ResultSet rsPass = SingletonSQL.consultar("SELECT password FROM user WHERE username == " + bduser);

                while (rsPass.next()) {
                    password = rsPass.getString("password");
                    if (pass == password) {
                        correcto = true;
                    } else {
                        msgError.setText("Credenciales introducidas incorrectas");
                        msgError.setVisibility(View.VISIBLE);
                    }
                }
            }

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return correcto;
    }


    //Método para iniciar sesión (lo que se ejecuta cuando le das al boton iniciar sesion)
    public void iniciarSesion(View view) {
        if (isEmptyTest() && usernameTest()) {
            Intent jugarPartida = new Intent(this, JugarPartida.class);
            startActivity(jugarPartida);
        }
    }

    //Método para ir a la pantalla de registrarse
    public void registrarse(View view) {
        Intent registrarse = new Intent(this, Registrarse.class);
        startActivity(registrarse);
    }

}