package ClasesDecorator;

import com.example.odswix.ImageViewAhorcado;

public class DibujoCabeza extends DibujoDecorator{

    public DibujoCabeza(Dibujo dibujo, ImageViewAhorcado imageView) {
        super(dibujo,imageView);
    }

    @Override
    public void dibujar() {

        super.getImage().drawCabeza();
    }
}
