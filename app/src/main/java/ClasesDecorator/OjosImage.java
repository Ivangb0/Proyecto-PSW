package ClasesDecorator;

import com.example.odswix.ImageViewAhorcado;

public class OjosImage extends ImageDecorator{
    Image image;
    ImageViewAhorcado imagen;
    public OjosImage(Image image, ImageViewAhorcado imagen){
        super(image,imagen);
    }

    @Override
    public void display(){
        imagen.drawOjos();
    }
}
