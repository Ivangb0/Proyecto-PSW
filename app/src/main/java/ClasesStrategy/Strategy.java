package ClasesStrategy;

import android.content.Context;

import BusinessLogic.User;

public interface Strategy {
    void aplicarTipo(Context context, String tipo, User usuario, boolean appMuted);
}
