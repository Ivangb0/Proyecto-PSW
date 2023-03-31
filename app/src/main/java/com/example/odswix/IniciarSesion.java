package com.example.odswix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Presentation.JugarPartida;
import kotlin.text.Regex;

public class IniciarSesion extends AppCompatActivity {

    private EditText name;
    private EditText pass;
    private TextView errorUsername;
    String url = "jdbc:mysql://wixserver.mysql.database.azure.com:3306/wixdatabase?useSSL=true";
    String user = "KogMaw";
    String password = "WIXAdmin1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        name = (EditText) findViewById(R.id.txt_username);
        pass = (EditText) findViewById(R.id.txt_password);
        errorUsername = (TextView) findViewById(R.id.txt_error);

    }

    //Método para iniciar sesión
    public void iniciarSesion(View view){

        if(loginCorrect() == 0) {
            Intent iniciarSes = new Intent(this, Perfil.class);
            startActivity(iniciarSes);
        }else if(loginCorrect() == 1){
            errorUsername.setText("Contraseña incorrecta.");
        }else if(loginCorrect() == 2){
            errorUsername.setText("Usuario no encontrado.");
        }
    }

    //Método para ir a la pantalla de registrarse
    public void registrarse (View view){
        Intent registrarse = new Intent(this, Registrarse.class);
        startActivity(registrarse);
    }

    //Método para validar el inicio de sesión y si es erróneo saber el motivo
    public int loginCorrect() {

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            Statement stmt = con.createStatement();
            ResultSet DBusername = stmt.executeQuery("SELECT username FROM user WHERE username = " + name);
            String DBname = "";
            while (DBusername.next()) {
                DBname = DBusername.getString(1);
            }

            ResultSet DBpassword = stmt.executeQuery("SELECT password FROM user WHERE username = " + name);
            String DBpass = "";
            while (DBpassword.next()) {
                DBpass = DBpassword.getString(1);
            }

            String username = name.getText().toString();
            String password = pass.getText().toString();

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