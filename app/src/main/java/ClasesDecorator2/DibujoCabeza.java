package ClasesDecorator2;

public class DibujoCabeza extends DibujoDecorator{

    public DibujoCabeza(Dibujo dibujo) {
        super(dibujo);
    }

    @Override
    public void dibujar() {
        getDibujo().dibujar();
    }
}
