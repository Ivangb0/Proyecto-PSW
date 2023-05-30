package com.example.odswix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class ImageViewAhorcado extends androidx.appcompat.widget.AppCompatImageView {
    private boolean shouldDrawLine = false;
    private boolean drawOjos = false;

    public ImageViewAhorcado(Context context) {
        super(context);
    }

    public ImageViewAhorcado(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewAhorcado(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        if (shouldDrawLine) {
            Drawable drawable = getDrawable();
            if (drawable == null) {
                return;
            }



            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas bitmapCanvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, bitmapCanvas.getWidth(), bitmapCanvas.getHeight());
            drawable.draw(bitmapCanvas);

            Paint paintPalo = new Paint();
            paintPalo.setColor(Color.BLACK);
            paintPalo.setStrokeWidth(80);
            bitmapCanvas.drawLine(bitmapCanvas.getWidth() / 4, bitmapCanvas.getHeight()-400, bitmapCanvas.getWidth()/4, 500, paintPalo);

            //mastil horizontal
            bitmapCanvas.drawLine(bitmapCanvas.getWidth() / 4 + 1050,  460, bitmapCanvas.getWidth() / 4 -150,  460, paintPalo);

            //cuerda
            bitmapCanvas.drawLine(bitmapCanvas.getWidth()/2 +205, 430,bitmapCanvas.getWidth() / 4 + 770,  650, paintPalo);

            //cabeza
            bitmapCanvas.drawCircle(bitmapCanvas.getWidth()/2+195, bitmapCanvas.getHeight()/3 +50, 170, paintPalo);
/*
            Paint paintTorso = new Paint();
            paintTorso.setColor(Color.BLACK);
            paintTorso.setStrokeWidth(80);*/
            bitmapCanvas.drawLine(bitmapCanvas.getWidth()/2+200, bitmapCanvas.getHeight()/3 + 200, bitmapCanvas.getWidth()/2+200, bitmapCanvas.getHeight()/2+300, paintPalo);
/*
            Paint paintBrazIzq = new Paint();
            paintBrazIzq.setColor(Color.BLACK);
            paintBrazIzq.setStrokeWidth(80);*/
            bitmapCanvas.drawLine(bitmapCanvas.getWidth()/2+200, bitmapCanvas.getHeight()/3 + 200, bitmapCanvas.getWidth()/2 - 50, bitmapCanvas.getHeight()/2+100, paintPalo);
/*
            Paint paintBrazDer = new Paint();
            paintBrazDer.setColor(Color.BLACK);
            paintBrazDer.setStrokeWidth(80);*/
            bitmapCanvas.drawLine(bitmapCanvas.getWidth()/2+200, bitmapCanvas.getHeight()/3 + 200, bitmapCanvas.getWidth()/2 + 450, bitmapCanvas.getHeight()/2+100, paintPalo);
/*
            Paint paintPierIzq = new Paint();
            paintPierIzq.setColor(Color.BLACK);
            paintPierIzq.setStrokeWidth(80);*/
            bitmapCanvas.drawLine(bitmapCanvas.getWidth()/2+200, bitmapCanvas.getHeight()/2+300,bitmapCanvas.getWidth()/2, bitmapCanvas.getHeight()/2+550,  paintPalo);
/*
            Paint paintPierDer = new Paint();
            paintPierDer.setColor(Color.BLACK);
            paintPierDer.setStrokeWidth(80);*/
            bitmapCanvas.drawLine(bitmapCanvas.getWidth()/2+200, bitmapCanvas.getHeight()/2+300,bitmapCanvas.getWidth() - 750, bitmapCanvas.getHeight()/2+550,  paintPalo);
 /*
            Paint paintOjos = new Paint();
            paintOjos.setColor(Color.RED);
            paintOjos.setStrokeWidth(20);
            bitmapCanvas.drawLine(bitmapCanvas.getWidth()/2+100, bitmapCanvas.getHeight()/3, bitmapCanvas.getWidth()/2+150, bitmapCanvas.getHeight()/3+50, paintOjos);
            bitmapCanvas.drawLine(bitmapCanvas.getWidth()/2+150, bitmapCanvas.getHeight()/3, bitmapCanvas.getWidth()/2+100, bitmapCanvas.getHeight()/3+50, paintOjos);
            bitmapCanvas.drawLine(bitmapCanvas.getWidth()/2+250, bitmapCanvas.getHeight()/3, bitmapCanvas.getWidth()/2+300, bitmapCanvas.getHeight()/3+50, paintOjos);
            bitmapCanvas.drawLine(bitmapCanvas.getWidth()/2+300, bitmapCanvas.getHeight()/3, bitmapCanvas.getWidth()/2+250, bitmapCanvas.getHeight()/3+50, paintOjos);

            Paint paintOjos = new Paint();
            paintOjos.setColor(Color.BLUE);
            paintOjos.setStrokeWidth(10);


            int width = bitmapCanvas.getWidth();
            int height = bitmapCanvas.getHeight();

            float mastilHorizontalY = height * 9 / 10;
            float mastilVerticalX = width / 2;
            float cuerdaFinalX = width * 3 / 4;
            float cuerdaFinalY = height / 10;
            float cabezaCenterX = cuerdaFinalX;
            float cabezaCenterY = cuerdaFinalY + height / 10;
            float troncoEndY = cuerdaFinalY + height / 3;
            float brazoIzquierdoEndX = mastilVerticalX;
            float brazoDerechoEndX = mastilVerticalX + (cuerdaFinalX - mastilVerticalX) / 3;
            float brazoEndY = cuerdaFinalY + height / 8;
            float piernaIzquierdaEndX = mastilVerticalX;
            float piernaDerechaEndX = mastilVerticalX + (cuerdaFinalX - mastilVerticalX) / 3;
            float piernaEndY = cuerdaFinalY + height / 4;
            float eyeSize = height / 40;

            bitmapCanvas.drawLine(width / 10, mastilHorizontalY, width * 9 / 10, mastilHorizontalY, paint);
            bitmapCanvas.drawLine(mastilVerticalX, mastilHorizontalY, mastilVerticalX, height / 10, paint);
            bitmapCanvas.drawLine(mastilVerticalX, height / 10, cuerdaFinalX, cuerdaFinalY, paint);
            bitmapCanvas.drawCircle(cabezaCenterX, cabezaCenterY, height / 20, paint);
            bitmapCanvas.drawLine(cuerdaFinalX, brazoEndY, brazoIzquierdoEndX, troncoEndY, paint);
            bitmapCanvas.drawLine(cuerdaFinalX, cuerdaFinalY + height / 10, cuerdaFinalX, troncoEndY, paint);
            bitmapCanvas.drawLine(cuerdaFinalX, brazoEndY, brazoDerechoEndX, troncoEndY, paint);
            bitmapCanvas.drawLine(cuerdaFinalX, piernaEndY, piernaIzquierdaEndX, piernaEndY, paint);
            bitmapCanvas.drawLine(cuerdaFinalX, piernaEndY, piernaDerechaEndX, piernaEndY, paint);

            bitmapCanvas.drawLine(cabezaCenterX - eyeSize, cabezaCenterY - eyeSize,
                    cabezaCenterX + eyeSize, cabezaCenterY + eyeSize, paintOjos);
            bitmapCanvas.drawLine(cabezaCenterX + eyeSize, cabezaCenterY - eyeSize,
                    cabezaCenterX - eyeSize, cabezaCenterY + eyeSize, paintOjos);


            */
            setImageBitmap(bitmap);
            shouldDrawLine = false;
        }else if(drawOjos) {
            Drawable drawable = getDrawable();
            if (drawable == null) {
                return;
            }



            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas bitmapCanvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, bitmapCanvas.getWidth(), bitmapCanvas.getHeight());
            drawable.draw(bitmapCanvas);

            Paint paintOjos = new Paint();
            paintOjos.setColor(Color.RED);
            paintOjos.setStrokeWidth(15);
            bitmapCanvas.drawLine(bitmapCanvas.getWidth() / 2 + 100, bitmapCanvas.getHeight() / 3, bitmapCanvas.getWidth() / 2 + 150, bitmapCanvas.getHeight() / 3 + 50, paintOjos);
            bitmapCanvas.drawLine(bitmapCanvas.getWidth() / 2 + 150, bitmapCanvas.getHeight() / 3, bitmapCanvas.getWidth() / 2 + 100, bitmapCanvas.getHeight() / 3 + 50, paintOjos);
            bitmapCanvas.drawLine(bitmapCanvas.getWidth() / 2 + 250, bitmapCanvas.getHeight() / 3, bitmapCanvas.getWidth() / 2 + 300, bitmapCanvas.getHeight() / 3 + 50, paintOjos);
            bitmapCanvas.drawLine(bitmapCanvas.getWidth() / 2 + 300, bitmapCanvas.getHeight() / 3, bitmapCanvas.getWidth() / 2 + 250, bitmapCanvas.getHeight() / 3 + 50, paintOjos);

            setImageBitmap(bitmap);
            drawOjos = false;
        }
    }

    public void drawLine() {
        shouldDrawLine = true;
        invalidate();
    }
    public void drawOjos() {
        drawOjos = true;
        invalidate();
    }
}
