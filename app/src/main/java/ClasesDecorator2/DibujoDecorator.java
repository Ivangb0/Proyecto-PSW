package ClasesDecorator2;

import com.example.odswix.ImageViewAhorcado;

public abstract class DibujoDecorator implements Dibujo{

    private Dibujo dibujo;
    private ImageViewAhorcado imageView;
    public DibujoDecorator(Dibujo dibujo, ImageViewAhorcado imageView){
        this.imageView=imageView;
        this.dibujo = dibujo;
    }

    protected Dibujo getDibujo(){
        return dibujo;
    }
    protected ImageViewAhorcado getImage(){
        return imageView;
    }
}
