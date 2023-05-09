package ClasesStrategy;

import java.util.Random;

public class RetoMixtoStrategy implements Strategy{
    @Override
    public void elegirTipoReto() {
        Random rand = new Random();
        int num = rand.nextInt(2);
        if (num == 0) {
            //RetoFraseStrategy pregunta = new RetoFraseStrategy();
            //pregunta.elegirTipoReto();
        } else {
            //RetoPreguntaStrategy pregunta = new RetoPreguntaStrategy();
            //pregunta.elegirTipoReto();
        }
    }
}
