package ClasesDecorator;

import android.widget.ImageView;

import com.example.odswix.R;
import com.example.odswix.RetoAhorcado;

public class ImageDecorator implements Image {
    private Image image;
    private int newResourceId;
    ImageView imageViewAhorcado;

    public ImageDecorator(Image image, int newResourceId) {
        this.image = image;
        this.newResourceId = newResourceId;
    }

    @Override
    public void display() {
        // Primero, muestra la imagen original
        image.display();

        // Luego, cambia la imagen por otra
        imageViewAhorcado = RetoAhorcado.imageViewAhorcado;
        imageViewAhorcado.setImageResource(newResourceId);
    }
}
