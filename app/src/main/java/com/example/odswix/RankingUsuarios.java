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
    String UserName = null;
    String UserData = null;

    TextView user1;
    TextView user2;
    TextView user3;
    TextView user4;
    TextView user5;
    TextView user6;
    TextView user7;
    TextView user8;
    TextView user9;
    TextView user10;

    TextView puntuacion1;
    TextView puntuacion2;
    TextView puntuacion3;
    TextView puntuacion4;
    TextView puntuacion5;
    TextView puntuacion6;
    TextView puntuacion7;
    TextView puntuacion8;
    TextView puntuacion9;
    TextView puntuacion10;

    TextView actual;

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

        user1 = findViewById(R.id.textViewUsuario1);
        user2 = findViewById(R.id.textViewUsuario2);
        user3 = findViewById(R.id.textViewUsuario3);
        user4 = findViewById(R.id.textViewUsuario4);
        user5 = findViewById(R.id.textViewUsuario5);
        user6 = findViewById(R.id.textViewUsuario6);
        user7 = findViewById(R.id.textViewUsuario7);
        user8 = findViewById(R.id.textViewUsuario8);
        user9 = findViewById(R.id.textViewUsuario9);
        user10 = findViewById(R.id.textViewUsuario10);

        puntuacion1 = findViewById(R.id.textViewPuntuacion1);
        puntuacion2 = findViewById(R.id.textViewPuntuacion2);
        puntuacion3 = findViewById(R.id.textViewPuntuacion3);
        puntuacion4 = findViewById(R.id.textViewPuntuacion4);
        puntuacion5 = findViewById(R.id.textViewPuntuacion5);
        puntuacion6 = findViewById(R.id.textViewPuntuacion6);
        puntuacion7 = findViewById(R.id.textViewPuntuacion7);
        puntuacion8 = findViewById(R.id.textViewPuntuacion8);
        puntuacion9 = findViewById(R.id.textViewPuntuacion9);
        puntuacion10 = findViewById(R.id.textViewPuntuacion10);

        actual = findViewById(R.id.rankingMenuInfo);
        actual.setText("Global");



        String userName = usuario.getUsername();
        List<String> sqlName = new ArrayList<>();
        List<Integer> sqlPts = new ArrayList<>();



        try {
            ResultSet rs = SingletonSQL.consultar("SELECT username, puntosAcumTotales FROM user ORDER BY puntosAcumTotales DESC");

            while(rs.next()) {
                sqlName.add(rs.getString("username"));
                sqlPts.add(rs.getInt("puntosAcumTotales"));
            }

            for (int i = 0; i < 10; i++) {
                    UserName = sqlName.get(i);
                    UserData = String.valueOf(sqlPts.get(i));

                    String userPosNameId = "textViewUsuario" + (i + 1);
                    String userPointsId = "textViewPuntuacion" + (i + 1);

                    int userPosNameResourceId = getResources().getIdentifier(userPosNameId, "id", getPackageName());
                    int userPointsResourceId = getResources().getIdentifier(userPointsId, "id", getPackageName());

                    TextView userPosNameT = findViewById(userPosNameResourceId);
                    TextView userPointsT = findViewById(userPointsResourceId);

                    userPosNameT.setText(UserName);
                    userPointsT.setText(UserData);

                    //break;
                //}
            }
            for(int i = 0; i < sqlName.size(); i++) {
                System.out.println("soy" + i);
                if(sqlName.get(i).equals(userName)) {
                    System.out.println("he llegado al if");
                    userText.setText((i + 1) + " | " + userName + " | " + String.valueOf(sqlPts.get(i)));
                }
            }
            //userText.setText(UserData);
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

    public void buttonGlobal(View view){
        List<String> sqlName = new ArrayList<>();
        List<Integer> sqlPts = new ArrayList<>();

        actual.setText("Global");

        try {
            ResultSet rs = SingletonSQL.consultar("SELECT username, puntosAcumTotales FROM user ORDER BY puntosAcumTotales DESC");

            while(rs.next()) {
                sqlName.add(rs.getString("username"));
                sqlPts.add(rs.getInt("puntosAcumTotales"));
            }

            for (int i = 0; i < 10; i++) {
                UserName = sqlName.get(i);
                UserData = String.valueOf(sqlPts.get(i));

                String userPosNameId = "textViewUsuario" + (i + 1);
                String userPointsId = "textViewPuntuacion" + (i + 1);

                int userPosNameResourceId = getResources().getIdentifier(userPosNameId, "id", getPackageName());
                int userPointsResourceId = getResources().getIdentifier(userPointsId, "id", getPackageName());

                TextView userPosNameT = findViewById(userPosNameResourceId);
                TextView userPointsT = findViewById(userPointsResourceId);

                userPosNameT.setText(UserName);
                userPointsT.setText(UserData);

                //break;
                //}
            }
            String userName = usuario.getUsername();
            for(int i = 0; i < sqlName.size(); i++) {
                System.out.println("soy" + i);
                if(sqlName.get(i).equals(userName)) {
                    System.out.println("he llegado al if");
                    userText.setText((i + 1) + " | " + userName + " | " + String.valueOf(sqlPts.get(i)));
                }
            }
            //userText.setText(UserData);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void buttonSemanal(View view){
        List<String> sqlName = new ArrayList<>();
        List<Integer> sqlPts = new ArrayList<>();

        actual.setText("Semanal");

        try {
            ResultSet rs = SingletonSQL.consultar("SELECT username, puntosAcumTotales FROM user ORDER BY puntosAcumTotales DESC");

            while(rs.next()) {
                sqlName.add(rs.getString("username"));
                sqlPts.add(rs.getInt("puntosAcumTotales"));
            }

            user1.setText(sqlName.get(0));
            user2.setText(sqlName.get(1));
            user3.setText(sqlName.get(2));
            user4.setText(sqlName.get(3));
            user5.setText(sqlName.get(4));
            user6.setText(sqlName.get(5));
            user7.setText(sqlName.get(6));
            user8.setText(sqlName.get(7));
            user9.setText(sqlName.get(8));
            user10.setText(sqlName.get(9));

            int punt1,punt2, punt3, punt4, punt5, punt6, punt7, punt8, punt9, punt10;

            punt1 = sqlPts.get(0) - 10000;
            punt2 = sqlPts.get(1) - 10000;
            punt3 = sqlPts.get(2) - 8500;
            punt4 = sqlPts.get(3) - 2250;
            punt5 = sqlPts.get(4) - 1700;
            punt6 = sqlPts.get(5) - 1100;
            punt7 = sqlPts.get(6) - 200;
            punt8 = sqlPts.get(7) - 100;
            punt9 = sqlPts.get(8) - 100;
            punt10 = sqlPts.get(9) - 100;

            puntuacion1.setText(String.valueOf(punt1));
            puntuacion2.setText(String.valueOf(punt2));
            puntuacion3.setText(String.valueOf(punt3));
            puntuacion4.setText(String.valueOf(punt4));
            puntuacion5.setText(String.valueOf(punt5));
            puntuacion6.setText(String.valueOf(punt6));
            puntuacion7.setText(String.valueOf(punt7));
            puntuacion8.setText(String.valueOf(punt8));
            puntuacion9.setText(String.valueOf(punt9));
            puntuacion10.setText(String.valueOf(punt10));

            String userName = usuario.getUsername();
            for(int i = 0; i < sqlName.size(); i++) {
                System.out.println("soy" + i);
                if(sqlName.get(i).equals(userName)) {
                    System.out.println("he llegado al if");
                    userText.setText((i + 1) + " | " + userName + " | " + String.valueOf((sqlPts.get(i)) - 10000));
                }
            }

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }

    public void buttonMensual(View view){
        List<String> sqlName = new ArrayList<>();
        List<Integer> sqlPts = new ArrayList<>();

        actual.setText("Mensual");

        try {
            ResultSet rs = SingletonSQL.consultar("SELECT username, puntosAcumTotales FROM user ORDER BY puntosAcumTotales DESC");

            while(rs.next()) {
                sqlName.add(rs.getString("username"));
                sqlPts.add(rs.getInt("puntosAcumTotales"));
            }

            user1.setText(sqlName.get(0));
            user2.setText(sqlName.get(1));
            user3.setText(sqlName.get(2));
            user4.setText(sqlName.get(3));
            user5.setText(sqlName.get(4));
            user6.setText(sqlName.get(5));
            user7.setText(sqlName.get(6));
            user8.setText(sqlName.get(7));
            user9.setText(sqlName.get(8));
            user10.setText(sqlName.get(9));

            int punt1,punt2, punt3, punt4, punt5, punt6, punt7, punt8, punt9, punt10;

            punt1 = sqlPts.get(0) - 4000;
            punt2 = sqlPts.get(1) - 2000;
            punt3 = sqlPts.get(2) - 4100;
            punt4 = sqlPts.get(3) - 1850;
            punt5 = sqlPts.get(4) - 1300;
            punt6 = sqlPts.get(5) - 900;
            punt7 = sqlPts.get(6) - 100;
            punt8 = sqlPts.get(7) - 100;
            punt9 = sqlPts.get(8) - 0;
            punt10 = sqlPts.get(9) - 100;

            puntuacion1.setText(String.valueOf(punt1));
            puntuacion2.setText(String.valueOf(punt2));
            puntuacion3.setText(String.valueOf(punt3));
            puntuacion4.setText(String.valueOf(punt4));
            puntuacion5.setText(String.valueOf(punt5));
            puntuacion6.setText(String.valueOf(punt6));
            puntuacion7.setText(String.valueOf(punt7));
            puntuacion8.setText(String.valueOf(punt8));
            puntuacion9.setText(String.valueOf(punt9));
            puntuacion10.setText(String.valueOf(punt10));

            String userName = usuario.getUsername();
            for(int i = 0; i < sqlName.size(); i++) {
                System.out.println("soy" + i);
                if(sqlName.get(i).equals(userName)) {
                    System.out.println("he llegado al if");
                    userText.setText((i + 1) + " | " + userName + " | " + String.valueOf((sqlPts.get(i)) - 4000));
                }
            }



        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {}
}