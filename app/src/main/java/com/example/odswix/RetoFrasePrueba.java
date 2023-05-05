package com.example.odswix;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RetoFrasePrueba extends AppCompatActivity implements View.OnDragListener, View.OnTouchListener {
    private String frase = "HOLA MUNDO";
    private String respuesta = "";
    GridLayout gridLayoutHuecos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.retofrase);

        TextView ejemplo = findViewById(R.id.respuesta);
        String incompletePhrase = frase.replaceAll("[A-Z]", "_ ");
        ejemplo.setText(incompletePhrase);
        setHuecos();
        setImages();

    }
    private void setHuecos(){
        ScrollView layout = findViewById(R.id.huecos);
        gridLayoutHuecos = new GridLayout(this);
        gridLayoutHuecos.setColumnCount(9);

        for (int i = 0; i < frase.length(); i++) {
            char letra = frase.charAt(i);
                ImageButton ibutton = new ImageButton(this);
                ibutton.setClickable(false);

                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.height = 100;
                layoutParams.width = 100;
                layoutParams.setMargins(8, 0, 8, 8);

            gridLayoutHuecos.addView(ibutton, layoutParams);
            if (Character.isWhitespace(letra)) {
                ibutton.setVisibility(View.INVISIBLE);
            }
            else {
                ibutton.setOnDragListener(this);
                ibutton.setBackgroundResource(R.drawable.hueco);
            }
        }
        layout.addView(gridLayoutHuecos);
    }
    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                // Verificar si el objeto arrastrado es una letra
                if (dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    return true;
                }
                return false;
            case DragEvent.ACTION_DRAG_ENTERED:
                // Cambiar el fondo del hueco para indicar que es una posible ubicacion
                view.setBackgroundResource(R.drawable.hueco_selected);
                return true;
            case DragEvent.ACTION_DRAG_LOCATION:
                return true;
            case DragEvent.ACTION_DRAG_EXITED:
                view.setBackgroundResource(R.drawable.hueco);
                return true;
            case DragEvent.ACTION_DROP:
                View letraView = (View) dragEvent.getLocalState();
                Button letraButton = (Button) letraView;
                ImageButton huecoButton = (ImageButton) view;

                GridLayout gridLayout1 = (GridLayout)letraButton.getParent();
                GridLayout gridLayout2 = (GridLayout)huecoButton.getParent();

                int indexLetra = gridLayout1.indexOfChild(letraButton);
                int indexHueco = gridLayout2.indexOfChild(huecoButton);

                gridLayout1.removeView(letraButton);
                gridLayout2.removeView(huecoButton);

                gridLayout1.addView(huecoButton, indexLetra);
                gridLayout2.addView(letraButton, indexHueco);

                /* Almacenar la letra en la respuesta y actualizar la vista del hueco
                View letraView = (View) dragEvent.getLocalState();
                Button letraButton = (Button) letraView;
                ImageButton huecoButton = (ImageButton) view;
                if (respuesta.equals("")) respuesta = letra;
                else respuesta += letra;
                String letra = letraButton.getText().toString();

                TextView ejemplo = findViewById(R.id.respuesta);
                ejemplo.setText(respuesta);*/

                // Establecer las nuevas posiciones del botón

                return true;

            default:
                break;
        }
        return false;
    }
    private void setImages(){
        ScrollView layout = findViewById(R.id.letras);
        GridLayout gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(9);

        String desFrase = desordenarFrase();
        for (int i = 0; i < desFrase.length(); i++) {
            char letra = desFrase.charAt(i);
            if (!Character.isWhitespace(letra)) {
                Button button = new Button(this);
                button.setClickable(false);
                button.setText(Character.toString(letra));

                button.setOnTouchListener(this);

                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.height=100;
                layoutParams.width=100;
                layoutParams.setMargins(8, 0, 8, 8);

                gridLayout.addView(button, layoutParams);
            }
        }
        layout.addView(gridLayout);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent){
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData clipData = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(clipData, shadowBuilder, view, 0);
            return true;
        }
        return false;
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
    public void comprobar(View view) {
        Intent intent = new Intent(this, Registrarse.class);
        TextView texto = findViewById(R.id.respuesta);
        texto.setText("");
        respuesta = "";
        for(int i = 0; i < gridLayoutHuecos.getChildCount(); i++) {
            View button = gridLayoutHuecos.getChildAt(i);
            if(button instanceof Button) {
                respuesta += ((Button) gridLayoutHuecos.getChildAt(i)).getText();
                texto.setText(respuesta);
            }
            else {
                texto.setText("error");
            }

        }

        //startActivity(intent);
    }
    @Override
    public void onBackPressed() {}
}
