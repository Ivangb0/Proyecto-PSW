package ClasesDecorator2;

public class DibujoBrazoIzq extends DibujoDecorator{
    public DibujoBrazoIzq(Dibujo dibujo) {
        super(dibujo);
    }

    @Override
    public void dibujar() {
        getDibujo().dibujar();
    }
}
