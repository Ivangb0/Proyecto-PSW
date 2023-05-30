package ClasesDecorator2;

import com.example.odswix.ImageViewAhorcado;

public class DibujoBrazoIzq extends DibujoDecorator{
    public DibujoBrazoIzq(Dibujo dibujo, ImageViewAhorcado imageView) {
        super(dibujo,imageView);
    }

    @Override
    public void dibujar() {
        getDibujo().dibujar();
    }
}
