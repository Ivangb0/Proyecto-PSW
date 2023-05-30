package ClasesDecorator2;

import com.example.odswix.ImageViewAhorcado;

public class DibujoOjos extends DibujoDecorator{

    public DibujoOjos(Dibujo dibujo, ImageViewAhorcado imageView) {
        super(dibujo,imageView);
    }

    @Override
    public void dibujar() {

        super.getImage().drawOjos();
    }
}
