package com.example.odswix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class ImageViewAhorcado extends androidx.appcompat.widget.AppCompatImageView {

    private boolean drawMastilVer = false;
    private boolean drawMastilHor = false;
    private boolean drawCuerda = false;
    private boolean drawCabeza = false;
    private boolean drawTronco = false;
    private boolean drawBrazoIzq = false;
    private boolean drawBrazoDer = false;
    private boolean drawPiernaIzq = false;
    private boolean drawPiernaDer = false;
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

            Drawable drawable = getDrawable();
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas bitmapCanvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, bitmapCanvas.getWidth(), bitmapCanvas.getHeight());
            drawable.draw(bitmapCanvas);
            if (drawable == null) {
                return;
            }

            Paint paintPalo = new Paint();
            paintPalo.setColor(Color.BLACK);
            paintPalo.setStrokeWidth(80);

            if(drawMastilVer) {
                Drawable drawableMastVer = getDrawable();
                if (drawableMastVer == null) {
                    return;
                }
                Bitmap bitmapMastVer = Bitmap.createBitmap(drawableMastVer.getIntrinsicWidth(), drawableMastVer.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas bitmapCanvasMastVer = new Canvas(bitmapMastVer);
                drawableMastVer.setBounds(0, 0, bitmapCanvasMastVer.getWidth(), bitmapCanvasMastVer.getHeight());
                drawableMastVer.draw(bitmapCanvasMastVer);
                bitmapCanvasMastVer.drawLine(bitmapCanvasMastVer.getWidth() / 4, bitmapCanvasMastVer.getHeight() - 400, bitmapCanvasMastVer.getWidth() / 4, 500, paintPalo);
                setImageBitmap(bitmapMastVer);
                drawMastilVer = false;

            }else if(drawMastilHor) {
                Drawable drawableMastHor = getDrawable();
                if (drawableMastHor == null) {
                    return;
                }
                Bitmap bitmapMastHor = Bitmap.createBitmap(drawableMastHor.getIntrinsicWidth(), drawableMastHor.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas bitmapCanvasMastHor = new Canvas(bitmapMastHor);
                drawableMastHor.setBounds(0, 0, bitmapCanvasMastHor.getWidth(), bitmapCanvasMastHor.getHeight());
                drawableMastHor.draw(bitmapCanvasMastHor);
                bitmapCanvasMastHor.drawLine(bitmapCanvasMastHor.getWidth() / 4 + 1050, 460, bitmapCanvasMastHor.getWidth() / 4 - 150, 460, paintPalo);
                setImageBitmap(bitmapMastHor);
                drawMastilHor = false;

            }else if(drawCuerda) {
                Drawable drawableCuerda = getDrawable();
                if (drawableCuerda == null) {
                    return;
                }
                Bitmap bitmapCuerda = Bitmap.createBitmap(drawableCuerda.getIntrinsicWidth(), drawableCuerda.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas bitmapCanvasCuerda = new Canvas(bitmapCuerda);
                drawableCuerda.setBounds(0, 0, bitmapCanvasCuerda.getWidth(), bitmapCanvasCuerda.getHeight());
                drawableCuerda.draw(bitmapCanvasCuerda);
                bitmapCanvasCuerda.drawLine(bitmapCanvasCuerda.getWidth() / 2 + 205, 430, bitmapCanvasCuerda.getWidth() / 4 + 770, 650, paintPalo);
                setImageBitmap(bitmapCuerda);
                drawCuerda = false;

            }else if(drawCabeza){
                Drawable drawableCabeza = getDrawable();
                if (drawableCabeza == null) {
                    return;
                }
                Bitmap bitmapCabeza = Bitmap.createBitmap(drawableCabeza.getIntrinsicWidth(), drawableCabeza.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas bitmapCanvasCabeza = new Canvas(bitmapCabeza);
                drawableCabeza.setBounds(0, 0, bitmapCanvasCabeza.getWidth(), bitmapCanvasCabeza.getHeight());
                drawableCabeza.draw(bitmapCanvasCabeza);
                bitmapCanvasCabeza.drawCircle(bitmapCanvasCabeza.getWidth()/2+195, bitmapCanvasCabeza.getHeight()/3 +50, 170, paintPalo);
                setImageBitmap(bitmapCabeza);
                drawCabeza = false;

            }else if(drawTronco) {
                Drawable drawableTronco = getDrawable();
                if (drawableTronco == null) {
                    return;
                }
                Bitmap bitmapTronco = Bitmap.createBitmap(drawableTronco.getIntrinsicWidth(), drawableTronco.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas bitmapCanvasTronco = new Canvas(bitmapTronco);
                drawableTronco.setBounds(0, 0, bitmapCanvasTronco.getWidth(), bitmapCanvasTronco.getHeight());
                drawableTronco.draw(bitmapCanvasTronco);
                bitmapCanvasTronco.drawLine(bitmapCanvasTronco.getWidth() / 2 + 200, bitmapCanvasTronco.getHeight() / 3 + 200, bitmapCanvasTronco.getWidth() / 2 + 200, bitmapCanvasTronco.getHeight() / 2 + 300, paintPalo);
                setImageBitmap(bitmapTronco);
                drawTronco = false;

            }else if(drawBrazoIzq) {
                Drawable drawableBrazoIzq = getDrawable();
                if (drawableBrazoIzq == null) {
                    return;
                }
                Bitmap bitmapBrazoIzq = Bitmap.createBitmap(drawableBrazoIzq.getIntrinsicWidth(), drawableBrazoIzq.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas bitmapCanvasBrazoIzq = new Canvas(bitmapBrazoIzq);
                drawableBrazoIzq.setBounds(0, 0, bitmapCanvasBrazoIzq.getWidth(), bitmapCanvasBrazoIzq.getHeight());
                drawableBrazoIzq.draw(bitmapCanvasBrazoIzq);

                bitmapCanvasBrazoIzq.drawLine(bitmapCanvasBrazoIzq.getWidth() / 2 + 200, bitmapCanvasBrazoIzq.getHeight() / 3 + 200, bitmapCanvasBrazoIzq.getWidth() / 2 - 50, bitmapCanvasBrazoIzq.getHeight() / 2 + 100, paintPalo);
                setImageBitmap(bitmapBrazoIzq);
                drawBrazoIzq = false;

            }else if(drawBrazoDer) {
                Drawable drawableBrazoDer = getDrawable();
                if (drawableBrazoDer == null) {
                    return;
                }
                Bitmap bitmapBrazoDer = Bitmap.createBitmap(drawableBrazoDer.getIntrinsicWidth(), drawableBrazoDer.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas bitmapCanvasBrazoDer = new Canvas(bitmapBrazoDer);
                drawableBrazoDer.setBounds(0, 0, bitmapCanvasBrazoDer.getWidth(), bitmapCanvasBrazoDer.getHeight());
                drawableBrazoDer.draw(bitmapCanvasBrazoDer);

                bitmapCanvasBrazoDer.drawLine(bitmapCanvasBrazoDer.getWidth()/2+200, bitmapCanvasBrazoDer.getHeight()/3 + 200, bitmapCanvasBrazoDer.getWidth()/2 + 450, bitmapCanvasBrazoDer.getHeight()/2+100, paintPalo);
                setImageBitmap(bitmapBrazoDer);
                drawBrazoDer = false;

            }else if(drawPiernaIzq) {
                Drawable drawablePiernaIzq = getDrawable();
                if (drawablePiernaIzq == null) {
                    return;
                }
                Bitmap bitmapPiernaIzq = Bitmap.createBitmap(drawablePiernaIzq.getIntrinsicWidth(), drawablePiernaIzq.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas bitmapCanvasPiernaIzq = new Canvas(bitmapPiernaIzq);
                drawablePiernaIzq.setBounds(0, 0, bitmapCanvasPiernaIzq.getWidth(), bitmapCanvasPiernaIzq.getHeight());
                drawablePiernaIzq.draw(bitmapCanvasPiernaIzq);

                bitmapCanvasPiernaIzq.drawLine(bitmapCanvasPiernaIzq.getWidth()/2+200, bitmapCanvasPiernaIzq.getHeight()/2+300,bitmapCanvasPiernaIzq.getWidth()/2, bitmapCanvasPiernaIzq.getHeight()/2+550,  paintPalo);
                setImageBitmap(bitmapPiernaIzq);
                drawPiernaIzq = false;

            }else if(drawPiernaDer) {
                Drawable drawablePiernaDer = getDrawable();
                if (drawablePiernaDer == null) {
                    return;
                }
                Bitmap bitmapPiernaDer = Bitmap.createBitmap(drawablePiernaDer.getIntrinsicWidth(), drawablePiernaDer.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas bitmapCanvasPiernaDer = new Canvas(bitmapPiernaDer);
                drawablePiernaDer.setBounds(0, 0, bitmapCanvasPiernaDer.getWidth(), bitmapCanvasPiernaDer.getHeight());
                drawablePiernaDer.draw(bitmapCanvasPiernaDer);

                bitmapCanvasPiernaDer.drawLine(bitmapCanvasPiernaDer.getWidth()/2+200, bitmapCanvasPiernaDer.getHeight()/2+300,bitmapCanvasPiernaDer.getWidth() - 750, bitmapCanvasPiernaDer.getHeight()/2+550,  paintPalo);
                setImageBitmap(bitmapPiernaDer);
                drawPiernaDer = false;

            }else if(drawOjos) {
                Paint paintOjos = new Paint();
                paintOjos.setColor(Color.RED);
                paintOjos.setStrokeWidth(15);
                Drawable drawableOjos = getDrawable();
                if (drawableOjos == null) {
                    return;
                }
                Bitmap bitmapOjos = Bitmap.createBitmap(drawableOjos.getIntrinsicWidth(), drawableOjos.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas bitmapCanvasOjos = new Canvas(bitmapOjos);
                drawableOjos.setBounds(0, 0, bitmapCanvasOjos.getWidth(), bitmapCanvasOjos.getHeight());
                drawableOjos.draw(bitmapCanvasOjos);
                bitmapCanvasOjos.drawLine(bitmapCanvasOjos.getWidth() / 2 + 100, bitmapCanvasOjos.getHeight() / 3, bitmapCanvasOjos.getWidth() / 2 + 150, bitmapCanvasOjos.getHeight() / 3 + 50, paintOjos);
                bitmapCanvasOjos.drawLine(bitmapCanvasOjos.getWidth() / 2 + 150, bitmapCanvasOjos.getHeight() / 3, bitmapCanvasOjos.getWidth() / 2 + 100, bitmapCanvasOjos.getHeight() / 3 + 50, paintOjos);
                bitmapCanvasOjos.drawLine(bitmapCanvasOjos.getWidth() / 2 + 250, bitmapCanvasOjos.getHeight() / 3, bitmapCanvasOjos.getWidth() / 2 + 300, bitmapCanvasOjos.getHeight() / 3 + 50, paintOjos);
                bitmapCanvasOjos.drawLine(bitmapCanvasOjos.getWidth() / 2 + 300, bitmapCanvasOjos.getHeight() / 3, bitmapCanvasOjos.getWidth() / 2 + 250, bitmapCanvasOjos.getHeight() / 3 + 50, paintOjos);
                setImageBitmap(bitmapOjos);
                drawOjos = false;

            }
    }
    public void drawMastilVer(){
        drawMastilVer = true;
        invalidate();
    }
    public void drawMastilHor(){
        drawMastilHor = true;
        invalidate();
    }
    public void drawCuerda(){
        drawCuerda = true;
        invalidate();
    }
    public void drawCabeza(){
        drawCabeza = true;
        invalidate();
    }
    public void drawTronco(){
        drawTronco = true;
        invalidate();
    }
    public void drawBrazoIzq(){
        drawBrazoIzq = true;
        invalidate();
    }
    public void drawBrazoDer(){
        drawBrazoDer = true;
        invalidate();
    }
    public void drawPiernaIzq(){
        drawPiernaIzq = true;
        invalidate();
    }
    public void drawPiernaDer(){
        drawPiernaDer = true;
        invalidate();
    }
    public void drawOjos() {
        drawOjos = true;
        invalidate();
    }
}
