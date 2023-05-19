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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ClasesFactory.ViewFactory;

public class PruebaFactory extends AppCompatActivity implements View.OnDragListener, View.OnTouchListener {

    GridLayout gridLayoutHuecos;
    private String frase = "HOLA MUNDO EJEMPLO";
    private String comprFrase = frase.replace(" ", "");;
    private String modFrase;
    private String respuesta = "";
    private int porcentaje = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.retoahorcado);

        Intent intent = getIntent();


        prepHuecos();
        setHuecos();
        setImages();
    }
    private void prepHuecos(){
        modFrase = frase;
        Random random = new Random();
        for(int i = 0; i < porcentaje;) {
            int r = random.nextInt(comprFrase.length());
            String letra = String.valueOf(modFrase.charAt(r));
            if(!letra.equals("#") && !letra.equals(" ")) {
                modFrase = modFrase.replaceFirst(letra, "#");
                i++;
            }
        }
    }
    private void setHuecos(){
        ScrollView layout = findViewById(R.id.huecos);
        gridLayoutHuecos = new GridLayout(this);
        gridLayoutHuecos.setColumnCount(9);
        ViewFactory viewFactory = new ViewFactory();
        for (int i = 0; i < frase.length(); i++) {
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.height = 100;
            layoutParams.width = 100;
            layoutParams.setMargins(8, 0, 8, 8);

            String letraMod = String.valueOf(modFrase.charAt(i));
            char letra = frase.charAt(i);

            if(letraMod.equals("#")){
                Button button = viewFactory.createButton(this, Character.toString(letra));
                button.setClickable(false);
                button.setText(Character.toString(letra));
                gridLayoutHuecos.addView(button, layoutParams);
            } else {

                ImageButton ibutton = viewFactory.createImageButton(this);
                ibutton.setClickable(false);

                ibutton.setOnDragListener(this);

                gridLayoutHuecos.addView(ibutton, layoutParams);
                if (Character.isWhitespace(letra)) {
                    ibutton.setVisibility(View.INVISIBLE);
                } else {

                    ibutton.setBackgroundResource(R.drawable.hueco);
                }
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

                if(gridLayout1 == gridLayout2){
                    return false;
                }

                int indexLetra = gridLayout1.indexOfChild(letraButton);
                int indexHueco = gridLayout2.indexOfChild(huecoButton);

                gridLayout1.removeView(letraButton);
                gridLayout2.removeView(huecoButton);

                gridLayout1.addView(huecoButton, indexLetra);
                gridLayout2.addView(letraButton, indexHueco);

                return true;

            default:
                break;
        }
        return false;
    }
    private void setImages(){
        //cambiarImagenODS();
        ScrollView layout = findViewById(R.id.letras);
        GridLayout gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(9);
        ViewFactory viewFactory = new ViewFactory();

        String desFrase = desordenarFrase();
        for (int i = 0; i < desFrase.length(); i++) {
            char letra = desFrase.charAt(i);
            if(!String.valueOf(letra).equals("#")) {
                if (!Character.isWhitespace(letra)) {
                    Button button = viewFactory.createButton(this, Character.toString(letra));
                    button.setClickable(false);
                    //button.setText(Character.toString(letra));
                    button.setBackgroundColor(0xE0F33F);

                    button.setOnTouchListener(this);

                    GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                    layoutParams.height = 100;
                    layoutParams.width = 100;
                    layoutParams.setMargins(8, 0, 8, 8);

                    gridLayout.addView(button, layoutParams);
                }
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
        char[] cadena = modFrase.toCharArray();
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
