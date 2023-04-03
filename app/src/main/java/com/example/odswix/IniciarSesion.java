package com.example.odswix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class IniciarSesion extends AppCompatActivity {

    private EditText name;
    private EditText pass;
    private TextView errorUsername;

    private static final String URL = "jdbc:mysql://wixserver.mysql.database.azure.com:3306/wixdatabase?useSSL=true";
    private static final String USERNAME = "KogMaw";
    private static final String PASSWORD = "WIXAdmin1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        name = (EditText) findViewById(R.id.txt_username);
        pass = (EditText) findViewById(R.id.txt_password);
        errorUsername = (TextView) findViewById(R.id.txt_error);

    }
    public void onBackPressed() {
        // Código para evitar que se cierre la actividad al pulsar el botón de Atrás
    }

    //Método para iniciar sesión (lo que se ejecuta cuando le das al boton iniciar sesion)
    public void iniciarSesion(View view){

        String username = name.getText().toString();
        String password = pass.getText().toString();
        String error;
        int i = loginCorrect(username, password);
        if(i == 0) {
            Intent iniciarSes = new Intent(this, Perfil.class);
            startActivity(iniciarSes);
        }else if(i == 1){
            error = "Contraseña incorrecta.";
            errorUsername.setText(error);
        }else if(i == 2){
            error = "Usuario no encontrado.";
            errorUsername.setText(error);
        }
    }

    //Método para ir a la pantalla de registrarse
    public void registrarse (View view){
        Intent registrarse = new Intent(this, Registrarse.class);
        startActivity(registrarse);
    }

    //Método para validar el inicio de sesión y si es erróneo saber el motivo
    public int loginCorrect(String username, String password) {

        try {
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            Statement stmt = con.createStatement();
            ResultSet DBusername = stmt.executeQuery("SELECT username FROM user WHERE username = " + username);
            String DBname = "";
            while (DBusername.next()) {
                DBname = DBusername.getString(1);
            }

            ResultSet DBpassword = stmt.executeQuery("SELECT password FROM user WHERE username = " + username);
            String DBpass = "";
            while (DBpassword.next()) {
                DBpass = DBpassword.getString(1);
            }



            if (username == DBname) {
                if (password == DBpass) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 2;
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}