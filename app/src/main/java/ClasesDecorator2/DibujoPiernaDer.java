package ClasesDecorator2;

public class DibujoPiernaDer extends DibujoDecorator{

    public DibujoPiernaDer(Dibujo dibujo) {
        super(dibujo);
    }

    @Override
    public void dibujar() {
        getDibujo().dibujar();
    }
}
