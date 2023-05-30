package ClasesDecorator2;

import com.example.odswix.ImageViewAhorcado;

public class DibujoBrazoDer extends DibujoDecorator{

    public DibujoBrazoDer(Dibujo dibujo, ImageViewAhorcado imageView) {
        super(dibujo,imageView);
    }

    @Override
    public void dibujar() {

        super.getImage().drawBrazoDer();
    }
}
