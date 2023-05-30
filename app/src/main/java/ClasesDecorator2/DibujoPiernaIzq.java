package ClasesDecorator2;

public class DibujoPiernaIzq extends DibujoDecorator{

    public DibujoPiernaIzq(Dibujo dibujo) {
        super(dibujo);
    }

    @Override
    public void dibujar() {
        getDibujo().dibujar();
    }
}
