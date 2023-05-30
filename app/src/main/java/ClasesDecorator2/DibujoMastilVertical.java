package ClasesDecorator2;

import com.example.odswix.ImageViewAhorcado;

public class DibujoMastilVertical extends DibujoDecorator{

    public DibujoMastilVertical(Dibujo dibujo, ImageViewAhorcado imageView) {
        super(dibujo,imageView);
    }

    @Override
    public void dibujar() {
        super.getImage().drawLine();
    }
}
