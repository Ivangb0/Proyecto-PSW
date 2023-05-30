package ClasesDecorator2;

import com.example.odswix.ImageViewAhorcado;

public class DibujoTronco extends DibujoDecorator{

    public DibujoTronco(Dibujo dibujo, ImageViewAhorcado imageView) {
        super(dibujo,imageView);
    }

    @Override
    public void dibujar() {
        getDibujo().dibujar();
    }
}
