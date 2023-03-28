package com.example.odswix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.*;

public class Registrarse extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarusuario);

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch(ClassNotFoundException e){
            this.setTitle(".....Class com.mysql.jdbc.Driver not found!");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            this.setTitle("Illegal access");
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            this.setTitle("instantiation exc eption");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

         TextView Erruser = findViewById(R.id.Euser);
         TextView Erremail = findViewById(R.id.Eemail);
         TextView Errpassword = findViewById(R.id.Epassword);

        Erruser.setVisibility(View.VISIBLE);

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
            ResultSet rs = stmt.executeQuery("SELECT username FROM user");
            String sql = "";
            while (rs.next()) {
                sql = rs.getString(1);
            }
            EditText textUsuario= (EditText) findViewById(R.id.usuario);

                textUsuario.setText(sql);
            


        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }
}