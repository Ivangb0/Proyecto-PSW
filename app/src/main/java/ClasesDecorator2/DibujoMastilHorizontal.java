package ClasesDecorator2;

public class DibujoMastilHorizontal extends DibujoDecorator{

    public DibujoMastilHorizontal(Dibujo dibujo) {
        super(dibujo);
    }

    @Override
    public void dibujar() {
        getDibujo().dibujar();
    }
}
