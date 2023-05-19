package ClasesPrototype;

import android.content.Context;

public class LetrasPrueba extends androidx.appcompat.widget.AppCompatButton implements Cloneable {

    public LetrasPrueba(Context context) {
        super(context);
    }



    @Override
    public LetrasPrueba clone() throws CloneNotSupportedException{
        return (LetrasPrueba) super.clone();
    }
}
