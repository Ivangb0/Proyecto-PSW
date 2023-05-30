package ClasesDecorator;

import com.example.odswix.ImageViewAhorcado;

public abstract class ImageDecorator implements Image {
    private Image image;
    private ImageViewAhorcado imagen;

    public ImageDecorator(Image image, ImageViewAhorcado imagen) {
        this.image = image;
        this.imagen = imagen;
    }
/*
    @Override
    public void display(ImageViewAhorcado imagen) {
        // Primero, muestra la imagen original
        image.display();

        // Luego, cambia la imagen por otra
        imageViewAhorcado = RetoAhorcado.imageViewAhorcado;
        imageViewAhorcado.setImageResource(newResourceId);
    }*/
}
