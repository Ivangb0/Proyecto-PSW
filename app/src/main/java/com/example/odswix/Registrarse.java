package com.example.odswix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Registrarse extends Activity{

    String url = "jdbc:mysql://wixserver.mysql.database.azure.com:3306/wixdatabase?useSSL=true";
    String user = "KogMaw";
    String password = "WIXAdmin1";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarusuario);

    }

    public void iniciarSes(View view){
        Intent intent = new Intent(this, IniciarSesion.class);
        startActivity(intent);
    }

    public void emptyTest(){
        TextView Eempty = findViewById(R.id.Eempty);
        EditText textUser= findViewById(R.id.usuario);
        TextView textEmail = findViewById(R.id.email);
        TextView textPass = findViewById(R.id.contraseña);

        if(textUser.getText().toString().isEmpty()){
            Eempty.setVisibility(View.VISIBLE);
        } else { Eempty.setVisibility(View.INVISIBLE);}

        if(textEmail.getText().toString().isEmpty()){
            Eempty.setVisibility(View.VISIBLE);
        } else { Eempty.setVisibility(View.INVISIBLE);}

        if(textPass.getText().toString().isEmpty()){
            Eempty.setVisibility(View.VISIBLE);
        } else { Eempty.setVisibility(View.INVISIBLE);}

    }

    public void userTest(){
        EditText textUser= findViewById(R.id.usuario);
        String bduser = textUser.getText().toString();
        String sql = "";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username FROM user");

            while (rs.next()) {
                sql = rs.getString(1);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        TextView Erruser = findViewById(R.id.Euser);
        if(sql.equals(bduser)){
            Erruser.setVisibility(View.VISIBLE);
        }
        else{
            Erruser.setVisibility(View.INVISIBLE);
        }
    }
    public void registro(View vista){
        TextView Erruser = findViewById(R.id.Euser);
        TextView Erremail = findViewById(R.id.Eemail);
        TextView Errpassword = findViewById(R.id.Epassword);
        EditText textUser= findViewById(R.id.usuario);
        TextView textEmail = findViewById(R.id.email);
        TextView textPass = findViewById(R.id.contraseña);

        emptyTest(); userTest();

    }

    @Override
    public void onBackPressed() {
        // Código para evitar que se cierre la actividad al pulsar el botón de Atrás
    }
}