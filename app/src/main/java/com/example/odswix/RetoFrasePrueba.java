package com.example.odswix;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RetoFrasePrueba extends AppCompatActivity {
    private String frase = "QUE INTRODUCCION MAS EPICA NO EPICA EPICA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retofrase);

        setImages();

    }
    private void setImages(){
        ScrollView layout = findViewById(R.id.ejemplo);
        GridLayout gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(9);

        String desFrase = desordenarFrase();
        for (int i = 0; i < desFrase.length(); i++) {
            char letra = desFrase.charAt(i);
            if (!Character.isWhitespace(letra)) {
                Button button = new Button(this);
                button.setClickable(false);
                button.setText(Character.toString(letra));

                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.height=100;
                layoutParams.width=100;
                layoutParams.setMargins(8, 0, 8, 0);

                gridLayout.addView(button, layoutParams);

                TextView ejemplo = findViewById(R.id.ejemploejemplo);
                String incompletePhrase = frase.replaceAll("[A-Z]", "_ ");
                ejemplo.setText(incompletePhrase);
            }
        }

        layout.addView(gridLayout);
    }
    private String desordenarFrase(){
        char[] cadena = frase.toCharArray();
        List<Character> lista = new ArrayList<Character>();

        for (char c : cadena) {
            lista.add(c);
        }
        Collections.shuffle(lista);
        StringBuilder sb = new StringBuilder();
        for (char c : lista) {
            sb.append(c);
        }
        String newFrase = sb.toString();
        return newFrase;
    }
}
