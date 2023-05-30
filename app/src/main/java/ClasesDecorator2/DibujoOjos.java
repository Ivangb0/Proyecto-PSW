package ClasesDecorator2;

public class DibujoOjos extends DibujoDecorator{

    public DibujoOjos(Dibujo dibujo) {
        super(dibujo);
    }

    @Override
    public void dibujar() {
        getDibujo().dibujar();
    }
}
