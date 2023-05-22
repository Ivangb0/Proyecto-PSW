package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import BusinessLogic.User;

public class Armario extends AppCompatActivity {
    User usuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.armario);

        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");

        setData(usuario);
    }

    private void setData(User user){
        if(usuario.ganadas < 5){ user.setMedallas(0); }
        else if(usuario.ganadas < 10 && usuario.ganadas > 0){ user.setMedallas(1); }
        else if(usuario.ganadas < 15 && usuario.ganadas > 0){ user.setMedallas(2); }
        else if(usuario.ganadas < 20 && usuario.ganadas > 0){ user.setMedallas(3); }
        else if(usuario.ganadas < 25 && usuario.ganadas > 0){ user.setMedallas(4); }
        else if(usuario.ganadas < 30 && usuario.ganadas > 0){ user.setMedallas(5); }
        else if(usuario.ganadas < 35 && usuario.ganadas > 0){ user.setMedallas(6); }
        else if(usuario.ganadas < 40 && usuario.ganadas > 0){ user.setMedallas(7); }
        else if(usuario.ganadas < 45 && usuario.ganadas > 0){ user.setMedallas(8); }
        else if(usuario.ganadas < 50 && usuario.ganadas > 0){ user.setMedallas(9); }
        else if(usuario.ganadas < 55 && usuario.ganadas > 0){ user.setMedallas(10); }
        else if(usuario.ganadas < 60 && usuario.ganadas > 0){ user.setMedallas(11); }
        else if(usuario.ganadas < 65 && usuario.ganadas > 0){ user.setMedallas(12); }

        TextView medallas = findViewById(R.id.medallas);
        medallas.setText(String.valueOf(user.getMedallas()));
    }

    public void volver (View view){
        Intent perfil = new Intent(this, Perfil.class);
        perfil.putExtra("user", usuario);
        finishAfterTransition();
        startActivity(perfil);
    }
    @Override
    public void onBackPressed() {}
}
