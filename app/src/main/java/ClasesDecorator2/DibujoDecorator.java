package ClasesDecorator2;

public abstract class DibujoDecorator implements Dibujo{

    private Dibujo dibujo;
    public DibujoDecorator(Dibujo dibujo){
        this.dibujo = dibujo;
    }

    protected Dibujo getDibujo(){
        return dibujo;
    }
}
