package ClasesDecorator;

import com.example.odswix.ImageViewAhorcado;

public class DibujoBrazoIzq extends DibujoDecorator{
    public DibujoBrazoIzq(Dibujo dibujo, ImageViewAhorcado imageView) {
        super(dibujo,imageView);
    }

    @Override
    public void dibujar() {

        super.getImage().drawBrazoIzq();
    }
}
