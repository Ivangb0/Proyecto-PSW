package ClasesDecorator;

import com.example.odswix.ImageViewAhorcado;

public class DibujoEstandar implements Dibujo{
    private ImageViewAhorcado imageView;
    private String nombre;

    public DibujoEstandar(ImageViewAhorcado imageView){
        this.imageView=imageView;
    }

    @Override
    public void dibujar() {

    }
}
