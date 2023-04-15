package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BusinessLogic.User;

public class IniciarSesion extends AppCompatActivity {

    User usuario = null;
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
        EditText textUser = findViewById(R.id.txt_username);
        EditText textPass = findViewById(R.id.txt_password);
        TextView msgError = findViewById(R.id.txt_error);
        String bduser = textUser.getText().toString();
        String pass = textPass.getText().toString();
        List<String> sqlUsernames = new ArrayList<>();
        List<String> sqlPasswords = new ArrayList<>();
        int i;
        boolean correcto = false;

        try {
            //Hacer consulta SQL
            ResultSet rsUsuario = SingletonSQL.consultar("SELECT username FROM user");

            //Introducir datos de la DB en un array
            while (rsUsuario.next()) {
                sqlUsernames.add(rsUsuario.getString("username"));
            }

            ResultSet rsPass = SingletonSQL.consultar("SELECT password FROM user");

            while (rsPass.next()) {
                sqlPasswords.add(rsPass.getString("password"));
            }

            boolean existe = false;
            for (i = 0; i < sqlUsernames.size(); i++) {
                if (bduser.equals(sqlUsernames.get(i))) {
                    existe = true;
                    break;
                }
            }


            if (!existe) {
                msgError.setText("No existe el usuario introducido");
                msgError.setVisibility(View.VISIBLE);
            } else {

                msgError.setVisibility(View.INVISIBLE);


                if(pass.equals(sqlUsernames.get(i))){
                    correcto = true;
                } else {
                        msgError.setText("Credenciales introducidas incorrectas");
                        msgError.setVisibility(View.VISIBLE);
                        }
            }


            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        return correcto;
    }


    //Método para iniciar sesión (lo que se ejecuta cuando le das al boton iniciar sesion)
    public void iniciarSesion(View view) {

        try {
            EditText textUser = findViewById(R.id.txt_username);
            String bduser = textUser.getText().toString();

            List<Integer> sqlId = new ArrayList<>();
            List<String> sqlUsernames = new ArrayList<>();
            List<String> sqlEmails = new ArrayList<>();
            List<String> sqlPasswords = new ArrayList<>();
            List<Integer> sqlPuntos = new ArrayList<>();
            List<Integer> sqlTrofeos = new ArrayList<>();
            List<Integer> sqlMedallas = new ArrayList<>();
            int i;

            //Lista usernames
            ResultSet rsUsernames = SingletonSQL.consultar("SELECT username FROM user");
            while (rsUsernames.next()) {
                sqlUsernames.add(rsUsernames.getString("username"));
            }

            //Lista id
            ResultSet rsId = SingletonSQL.consultar("SELECT id_user FROM user");
            while (rsId.next()) {
                sqlId.add(rsId.getInt("id_user"));
            }

            //Lista email
            ResultSet rsEmail = SingletonSQL.consultar("SELECT email FROM user");
            while (rsEmail.next()) {
                sqlEmails.add(rsEmail.getString("email"));
            }

            //Lista passwords
            ResultSet rsPassword = SingletonSQL.consultar("SELECT password FROM user");
            while (rsPassword.next()) {
                sqlPasswords.add(rsPassword.getString("password"));
            }

            //Lista puntos acumulados totales
            ResultSet rsPuntos = SingletonSQL.consultar("SELECT puntosAcumTotales FROM user");
            while (rsPuntos.next()) {
                sqlPuntos.add(rsPuntos.getInt("puntosAcumTotales"));
            }

            //Lista trofeos
            ResultSet rsTrofeos = SingletonSQL.consultar("SELECT trofeos FROM user");
            while (rsTrofeos.next()) {
                sqlTrofeos.add(rsTrofeos.getInt("trofeos"));
            }

            //Lista medallas
            ResultSet rsMedallas = SingletonSQL.consultar("SELECT medallas FROM user");
            while (rsMedallas.next()) {
                sqlMedallas.add(rsMedallas.getInt("medallas"));
            }

            if (isEmptyTest() && usernameTest()) {

                    for (i = 0; i < sqlUsernames.size(); i++) {
                        if (bduser.equals(sqlUsernames.get(i))) {
                            break;
                        }
                    }

                    int id = sqlId.get(i);
                    String username = sqlUsernames.get(i);
                    String password = sqlPasswords.get(i);
                    String email = sqlEmails.get(i);
                    int puntos = sqlPuntos.get(i);
                    int trofeos = sqlTrofeos.get(i);
                    int medallas = sqlMedallas.get(i);

                        usuario = new User(id,username,password,email,puntos,trofeos,medallas);

                        Intent jugarPartida = new Intent(this, JugarPartida.class);
                        jugarPartida.putExtra("user", usuario);
                        startActivity(jugarPartida);
                    }

            } catch (SQLException e){
                e.printStackTrace();
            }
        }

    //Método para ir a la pantalla de registrarse
    public void registrarse(View view) {
        Intent registrarse = new Intent(this, Registrarse.class);
        startActivity(registrarse);
    }

}