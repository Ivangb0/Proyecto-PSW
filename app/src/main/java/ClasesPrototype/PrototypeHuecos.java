package ClasesPrototype;

import android.widget.ImageButton;

public class PrototypeHuecos extends Prototype {
    private ImageButton hueco;

    public PrototypeHuecos(){};

    public PrototypeHuecos(ImageButton hueco) {
            this.hueco = hueco;
    }

    @Override
    public Prototype clone() {
        return new PrototypeHuecos(this.hueco);
        /*PrototypeHuecos clone = null;
        try {
            clone = (PrototypeHuecos) super.clone();
            clone.hueco = new ImageButton(hueco.getContext());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;*/
    }
}
