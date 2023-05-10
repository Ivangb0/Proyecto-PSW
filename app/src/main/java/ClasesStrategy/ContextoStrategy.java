package ClasesStrategy;

import android.content.Context;

import BusinessLogic.User;

public class ContextoStrategy {
    private Strategy partida;

    public ContextoStrategy(Strategy estrategia){
        partida = estrategia;
    }
    public void elegirTipo(Context context, String tipo, User usuario, boolean appMuted){
        partida.aplicarTipo(context, tipo, usuario, appMuted);
    }
}
