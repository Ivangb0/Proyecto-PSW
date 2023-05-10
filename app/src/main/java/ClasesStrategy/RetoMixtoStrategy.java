package ClasesStrategy;

import android.content.Context;

import java.util.Random;

import BusinessLogic.User;

public class RetoMixtoStrategy implements Strategy{

    @Override
    public void aplicarTipo(Context context, String tipo, User usuario, boolean appMuted) {
        Random rand = new Random();
        int num = rand.nextInt(2);
        if (num == 0) {
            RetoFraseStrategy pregunta = new RetoFraseStrategy();
            pregunta.aplicarTipo(context, tipo, usuario, appMuted);
        } else {
            RetoPreguntaStrategy pregunta = new RetoPreguntaStrategy();
            pregunta.aplicarTipo(context, tipo, usuario, appMuted);
        }
    }
}
