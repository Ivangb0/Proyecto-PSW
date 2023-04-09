package com.example.odswix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BusinessLogic.User;

public class IniciarSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);


    }
    public void onBackPressed() {
        // Código para evitar que se cierre la actividad al pulsar el botón de Atrás
    }

    public boolean isEmptyTest(){
        TextView msgError = findViewById(R.id.txt_error);
        EditText name = findViewById(R.id.txt_username);
        EditText pass = findViewById(R.id.txt_password);

        boolean todoOK = false;

        if(name.getText().toString().isEmpty() ||
                pass.getText().toString().isEmpty()){
            msgError.setText("Hay algún campo vacío, revise las credenciales");
            msgError.setVisibility(View.VISIBLE);
        } else {
            todoOK = true;
            msgError.setVisibility(View.INVISIBLE);
        }
        return todoOK;
    }
    //Método para iniciar sesión (lo que se ejecuta cuando le das al boton iniciar sesion)
    public void iniciarSesion(View view){

        try {
            if(isEmptyTest()){
                Intent intent = new Intent(this, JugarPartida.class);
                intent.putExtra("user", usuario);
                startActivity(intent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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