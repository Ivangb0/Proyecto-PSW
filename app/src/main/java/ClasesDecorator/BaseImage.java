package ClasesDecorator;

import android.widget.ImageView;

import com.example.odswix.RetoAhorcado;

public class BaseImage implements Image {
    private int resourceId;

    ImageView imageViewAhorcado = RetoAhorcado.imageViewAhorcado;

    public BaseImage(int resourceId) {
            this.resourceId = resourceId;
    }
    @Override
    public void display() {
        imageViewAhorcado.setImageResource(resourceId);
    }
}
