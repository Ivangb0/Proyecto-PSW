package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import BusinessLogic.User;

public class RankingUsuarios extends AppCompatActivity {

    User usuario = null;
    TextView userText;
    String UserData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.ranking_usuarios);

        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");
        userText = findViewById(R.id.idUsuario);


        String userName = usuario.getUsername();
        List<String> sqlName = new ArrayList<>();
        List<Integer> sqlPts = new ArrayList<>();

        try {
            ResultSet rs = SingletonSQL.consultar("SELECT username, puntosAcumTotales FROM user ORDER BY puntosAcumTotales DESC");

            while(rs.next()) {
                sqlName.add(rs.getString("username"));
                sqlPts.add(rs.getInt("puntosAcumTotales"));
            }

            for(int i = 0; i < 10; i++){
                sqlName.get(i); // Nombre de usuario
                sqlPts.get(i); // Puntos del usuario
            } // Recuerda asignarle un i+1 a la posición
            //Muestralo como quiera señor yo ya he hecho mi parte <3


            for (int i = 0; i < sqlName.size(); i++) {
                if (userName.equals(sqlName.get(i))) {
                    UserData = String.valueOf(i+1) + " | " + userName + " | " + String.valueOf(usuario.getPuntosAcumTotales());
                    break; //Aunque te diag que no es necesario, deja los String.valueOf, o te dará error
                } //                BORRA LOS COMENTARIOS CUANDO TERMINES ******
            }
            userText.setText(UserData);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void volver (View view){
        Intent intent = new Intent(this, Perfil.class);
        intent.putExtra("user", usuario);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onBackPressed() {}
}