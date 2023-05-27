package com.example.odswix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class MyImageView extends androidx.appcompat.widget.AppCompatImageView {
    private boolean shouldDrawLine = false;

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
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
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStrokeWidth(25);
            Paint paintOjos = new Paint();
            paintOjos.setColor(Color.BLUE);
            paintOjos.setStrokeWidth(10);
            bitmapCanvas.drawLine(0, 0, bitmapCanvas.getWidth(), bitmapCanvas.getHeight(), paint);

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



            setImageBitmap(bitmap);
            shouldDrawLine = false;
        }
    }

    public void drawLine() {
        shouldDrawLine = true;
        invalidate();
    }
}
