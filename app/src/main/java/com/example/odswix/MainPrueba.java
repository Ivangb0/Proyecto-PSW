package com.example.odswix;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ClasesDecorator.Dibujo;
import ClasesDecorator.DibujoBrazoDer;
import ClasesDecorator.DibujoBrazoIzq;
import ClasesDecorator.DibujoCabeza;
import ClasesDecorator.DibujoCuerda;
import ClasesDecorator.DibujoEstandar;
import ClasesDecorator.DibujoMastilHorizontal;
import ClasesDecorator.DibujoMastilVertical;
import ClasesDecorator.DibujoOjos;
import ClasesDecorator.DibujoPiernaDer;
import ClasesDecorator.DibujoPiernaIzq;
import ClasesDecorator.DibujoTronco;

public class MainPrueba extends AppCompatActivity {
    private ImageViewAhorcado imageView;
    private Button drawButton;
    int n = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.retoahorcado);

        imageView = findViewById(R.id.imageViewAhorcado);

        drawButton = findViewById(R.id.buttonKeyQ);


        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dibujo dibujo = new DibujoEstandar(imageView);
                dibujo.dibujar();
                switch (n) {
                    case 1:
                        Dibujo dibujoMastilVertical = new DibujoMastilVertical(dibujo, imageView);
                        dibujoMastilVertical.dibujar(); n++;
                        break;
                    case 2:
                        Dibujo dibujoMastilHorizontal = new DibujoMastilHorizontal(dibujo, imageView);
                        dibujoMastilHorizontal.dibujar(); n++;
                        break;
                    case 3:
                        Dibujo dibujoCuerda = new DibujoCuerda(dibujo, imageView);
                        dibujoCuerda.dibujar(); n++;
                        break;
                    case 4:
                        Dibujo dibujoCabeza = new DibujoCabeza(dibujo, imageView);
                        dibujoCabeza.dibujar(); n++;
                        break;
                    case 5:
                        Dibujo dibujoTronco = new DibujoTronco(dibujo, imageView);
                        dibujoTronco.dibujar(); n++;
                        break;
                    case 6:
                        Dibujo dibujoBrazoIzq = new DibujoBrazoIzq(dibujo, imageView);
                        dibujoBrazoIzq.dibujar(); n++;
                        break;
                    case 7:
                        Dibujo dibujoBrazoDer = new DibujoBrazoDer(dibujo, imageView);
                        dibujoBrazoDer.dibujar(); n++;
                        break;
                    case 8:
                        Dibujo dibujoPiernaIzq = new DibujoPiernaIzq(dibujo, imageView);
                        dibujoPiernaIzq.dibujar(); n++;
                        break;
                    case 9:
                        Dibujo dibujoPiernaDer = new DibujoPiernaDer(dibujo, imageView);
                        dibujoPiernaDer.dibujar(); n++;
                        break;
                    case 10:
                        Dibujo dibujoOjos = new DibujoOjos(dibujo, imageView);
                        dibujoOjos.dibujar(); n++;
                        break;
                    default:
                        // Acción en caso de que numFallos no esté dentro del rango válido
                        break;
                }
            }
        });
    }
}
