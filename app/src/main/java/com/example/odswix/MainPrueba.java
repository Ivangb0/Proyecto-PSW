package com.example.odswix;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ClasesDecorator.BaseImage;
import ClasesDecorator.Image;
import ClasesDecorator.OjosImage;
import ClasesDecorator.TorsoImage;

public class MainPrueba extends AppCompatActivity {
    private ImageViewAhorcado imageView;
    private Button drawButton;
    int n = 0;
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
                Image baseImage = new BaseImage(imageView);
                Image torsoImage = new TorsoImage(baseImage, imageView);
                Image ojosImage = new OjosImage(baseImage, imageView);
                if(n==0) {
                    torsoImage.display(); n++;
                }else {
                    ojosImage.display();
                }
            }
        });
    }
}
