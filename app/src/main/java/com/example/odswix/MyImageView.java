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
            paint.setStrokeWidth(5);
            bitmapCanvas.drawLine(0, 0, bitmapCanvas.getWidth(), bitmapCanvas.getHeight(), paint);

            setImageBitmap(bitmap);
            shouldDrawLine = false;
        }
    }

    public void drawLine() {
        shouldDrawLine = true;
        invalidate();
    }
}
