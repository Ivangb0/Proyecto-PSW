package ClasesDecorator2;

import com.example.odswix.ImageViewAhorcado;

public class DibujoPiernaIzq extends DibujoDecorator{

    public DibujoPiernaIzq(Dibujo dibujo, ImageViewAhorcado imageView) {
        super(dibujo,imageView);
    }

    @Override
    public void dibujar() {
        getDibujo().dibujar();
    }
}
