package ClasesDecorator;

import com.example.odswix.ImageViewAhorcado;

public class TorsoImage extends ImageDecorator{
    Image image;
    ImageViewAhorcado imagen;
    public TorsoImage(Image image, ImageViewAhorcado imagen){
        super(image,imagen);
    }

    @Override
    public void display(){
        imagen.drawLine();
    }
}
