package ClasesDecorator2;

public class DibujoCuerda extends DibujoDecorator{

    public DibujoCuerda(Dibujo dibujo) {
        super(dibujo);
    }

    @Override
    public void dibujar() {
        getDibujo().dibujar();
    }
}
