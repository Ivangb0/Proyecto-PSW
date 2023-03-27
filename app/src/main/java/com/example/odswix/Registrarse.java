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
import java.sql.SQLException;
import java.sql.Statement;

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
        EditText textEmail= (EditText) findViewById(R.id.email);
        textEmail.setText("Primera prueba");

        //Prueba de conexión a la base de datos
        String url = "jdbc:mysql://wixserver.mysql.database.azure.com:3306/wixdatabase?useSSL=true";
        String user = "KogMaw";
        String password = "WIXAdmin1";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *");


            String sql = rs.getString(0);
            EditText textUsuario= (EditText) findViewById(R.id.usuario);
            textUsuario.setText("Error");
            if(sql.isEmpty()){
                textUsuario.setText("Error");
            } else {
                textUsuario.setText(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        EditText textUsuario= (EditText) findViewById(R.id.usuario);
        textUsuario.setText("Error");
    }
}