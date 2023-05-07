package ClasesStrategy;

import BusinessLogic.Pregunta;

public class RetoPreguntaStrategy implements Strategy {
    private Pregunta pregunta;

    //Constructor
    public RetoPreguntaStrategy(Pregunta preg){
        this.pregunta = preg;
    }

    @Override
    public void elegirTipoReto() {

    }
}
