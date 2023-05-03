package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BusinessLogic.User;

public class Registrarse extends AppCompatActivity {
    User usuario = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarusuario);

    }
    public void iniciarSes(View view){
        Intent intent = new Intent(this, IniciarSesion.class);
        startActivity(intent);
    }
    public boolean emptyTest(){
        TextView Eempty = findViewById(R.id.Eempty);
        EditText textUser= findViewById(R.id.usuario);
        TextView textEmail = findViewById(R.id.email);
        TextView textPass = findViewById(R.id.contraseña);
        TextView textPass2 = findViewById(R.id.confirmContr);
        boolean correcto = false;

        if(textUser.getText().toString().isEmpty() ||
                textEmail.getText().toString().isEmpty() ||
                textPass.getText().toString().isEmpty() ||
                textPass2.getText().toString().isEmpty()){
            Eempty.setVisibility(View.VISIBLE);
        } else {
            correcto = true;
            Eempty.setVisibility(View.INVISIBLE);
        }
        return correcto;
    }
    public boolean userTest(){
        EditText textUser= findViewById(R.id.usuario);
        String bduser = textUser.getText().toString();
        List<String> sql = new ArrayList<>();
        boolean correcto = false;

        try {
            ResultSet rs = SingletonSQL.consultar("SELECT username FROM user");

            while(rs.next()) {
                sql.add(rs.getString("username"));
            }

            boolean encontrado = false;
            for (int i = 0; i < sql.size(); i++) {
                if (bduser.equals(sql.get(i))) {
                    encontrado = true;
                    break;
                }
            }

            TextView Erruser = findViewById(R.id.Euser);
            if(encontrado){
                Erruser.setVisibility(View.VISIBLE);
            }
            else{
                correcto = true;
                Erruser.setVisibility(View.INVISIBLE);
            }

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return correcto;
    }
    public boolean emailTest(){
        EditText textEmail = findViewById(R.id.email);
        String bdemail = textEmail.getText().toString();
        List<String> sql = new ArrayList<>();
        boolean correcto = false;

        try {
            ResultSet rs = SingletonSQL.consultar("SELECT email FROM user");

            while(rs.next()) {
                sql.add(rs.getString("email"));
            }

            boolean encontrado = false;
            for (int i = 0; i < sql.size(); i++) {
                if (bdemail.equals(sql.get(i))) {
                    encontrado = true;
                    break;
                }
            }

            TextView Erremail = findViewById(R.id.Eemail);
            if(encontrado){
                Erremail.setVisibility(View.VISIBLE);
            }
            else{
                correcto = true;
                Erremail.setVisibility(View.INVISIBLE);
            }

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return correcto;
    }
    private boolean passwordTest(){
        EditText password = findViewById(R.id.contraseña);
        EditText password2 = findViewById(R.id.confirmContr);
        TextView Errpassword = findViewById(R.id.Epassword);

        boolean correcto = false;

        if(!password.getText().toString().equals(password2.getText().toString()) ){
            Errpassword.setVisibility(View.VISIBLE);
        } else {
            correcto = true;
            Errpassword.setVisibility(View.INVISIBLE);
        }
        return correcto;
    }

    public void registro(View vista){
        try {
            if(emptyTest() && userTest() && emailTest() && passwordTest()) {
                PreparedStatement ps = SingletonSQL.insertar("INSERT INTO user (id_user, username, email, password, puntosAcumTotales, trofeos, medallas) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)");

                ResultSet rs = SingletonSQL.consultar("SELECT MAX(id_user) FROM user");
                int id = 0;
                while (rs.next()) {
                    id = rs.getInt("MAX(id_user)");
                }
                EditText textUser = findViewById(R.id.usuario);
                EditText textEmail = findViewById(R.id.email);
                EditText textPassword = findViewById(R.id.contraseña);
                ps.setInt(1, id + 1);
                ps.setString(2, textUser.getText().toString());
                ps.setString(3, textEmail.getText().toString());
                ps.setString(4, textPassword.getText().toString());
                ps.setInt(5, 0);
                ps.setInt(6, 0);
                ps.setInt(7, 0);

                ps.executeUpdate();

                usuario = new User(id + 1, textUser.getText().toString(),
                        textEmail.getText().toString(), textPassword.getText().toString(),
                        0, 0, 0);

                Intent intent = new Intent(this, JugarPartida.class);
                intent.putExtra("user", usuario);
                startActivity(intent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onBackPressed() {}
}