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

    public static User usuario = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
    }

    public void onBackPressed() {}

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
        List<String> sqlUsernames = getListStringsDB("username");
        List<String> sqlPasswords = getListStringsDB("password");
        int i;
        boolean correcto = false;

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

            if(pass.equals(sqlPasswords.get(i))){
                correcto = true;
            } else {
                msgError.setText("Credenciales introducidas incorrectas");
                msgError.setVisibility(View.VISIBLE);
            }
        }
        return correcto;
    }

    //Método para iniciar sesión (lo que se ejecuta cuando le das al boton iniciar sesion)
    public void iniciarSesion(View view) {
        EditText textUser = findViewById(R.id.txt_username);
        String bduser = textUser.getText().toString();
        int i;

        if (isEmptyTest() && usernameTest()) {
            List<String> sqlUsernames = getListStringsDB("username");

            for (i = 0; i < sqlUsernames.size(); i++) {
                    if (bduser.equals(sqlUsernames.get(i))) { break; }
            }

            int id = getListIntegersDB("id_user").get(i);
            String username = sqlUsernames.get(i);
            String password = getListStringsDB("password").get(i);
            String email = getListStringsDB("email").get(i);
            int puntos = getListIntegersDB("puntosAcumTotales").get(i);
            int trofeos = getListIntegersDB("trofeos").get(i);
            int medallas = getListIntegersDB("medallas").get(i);

            usuario = new User(id,username,email,password,puntos,trofeos,medallas);

            Intent jugarPartida = new Intent(this, JugarPartida.class);
            jugarPartida.putExtra("user", usuario);
            startActivity(jugarPartida);
        }
    }

    public void registrarse(View view) {
        Intent registrarse = new Intent(this, Registrarse.class);
        startActivity(registrarse);
    }

    public List<String> getListStringsDB(String columnName) {
        List<String> sql = null;
        try {
            sql = new ArrayList<String>();
            String consulta = "SELECT * FROM user ORDER BY id_user";
            ResultSet rs = SingletonSQL.consultar(consulta);
            while (rs.next()) {
                sql.add(rs.getString(columnName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sql;
    }

    public List<Integer> getListIntegersDB(String columnName) {
        List<Integer> sql = null;
        try {
            sql = new ArrayList<Integer>();
            String consulta = "SELECT * FROM user ORDER BY id_user";
            ResultSet rs = SingletonSQL.consultar(consulta);
            while (rs.next()) {
                sql.add(rs.getInt(columnName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sql;
    }
}