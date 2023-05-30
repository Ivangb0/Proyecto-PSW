package ClasesDecorator2;

public class DibujoEstandar implements Dibujo{

    private String nombre;

    public DibujoEstandar(String s){
        nombre=s;
    }

    @Override
    public void dibujar() {
        System.out.println("Dibujo estandar");
    }
}
