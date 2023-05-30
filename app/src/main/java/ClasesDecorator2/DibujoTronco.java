package ClasesDecorator2;

public class DibujoTronco extends DibujoDecorator{

    public DibujoTronco(Dibujo dibujo) {
        super(dibujo);
    }

    @Override
    public void dibujar() {
        getDibujo().dibujar();
    }
}
