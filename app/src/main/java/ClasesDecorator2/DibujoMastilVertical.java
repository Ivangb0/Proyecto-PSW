package ClasesDecorator2;

public class DibujoMastilVertical extends DibujoDecorator{

    public DibujoMastilVertical(Dibujo dibujo) {
        super(dibujo);
    }

    @Override
    public void dibujar() {
        getDibujo().dibujar();
    }
}
