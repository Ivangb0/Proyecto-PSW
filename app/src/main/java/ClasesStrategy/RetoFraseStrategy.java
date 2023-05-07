package ClasesStrategy;

import BusinessLogic.Frase;

public class RetoFraseStrategy implements Strategy {
    private Frase frase;

    //Constructor
    public RetoFraseStrategy(Frase fras){
        this.frase = fras;
    }

    @Override
    public void elegirTipoReto() {
    }
}
