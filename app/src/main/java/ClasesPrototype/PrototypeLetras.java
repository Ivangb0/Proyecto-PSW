package ClasesPrototype;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class PrototypeLetras extends Prototype implements View.OnTouchListener {
    private Button letra;

    public PrototypeLetras(){};

    public PrototypeLetras(Button letra) {
        this.letra = letra;
        letra.setClickable(false);
        letra.setBackgroundColor(0xE0F33F);
    }

    public Button getButton(){
        return this.letra;
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

    @Override
    public Prototype clone() {
        return new PrototypeLetras(this.letra);
    }
}
