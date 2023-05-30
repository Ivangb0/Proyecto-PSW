package ClasesDecorator2;

import com.example.odswix.ImageViewAhorcado;

public class DibujoCuerda extends DibujoDecorator{

    public DibujoCuerda(Dibujo dibujo, ImageViewAhorcado imageView) {
        super(dibujo,imageView);
    }

    @Override
    public void dibujar() {

        super.getImage().drawCuerda();
    }
}
