package ClasesDecorator2;

public class DibujoBrazoDer extends DibujoDecorator{

    public DibujoBrazoDer(Dibujo dibujo) {
        super(dibujo);
    }

    @Override
    public void dibujar() {
        getDibujo().dibujar();
    }
}
