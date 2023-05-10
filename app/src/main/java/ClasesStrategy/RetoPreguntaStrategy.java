package ClasesStrategy;

import android.content.Context;
import android.content.Intent;

import com.example.odswix.Gestor;

import BusinessLogic.User;

public class RetoPreguntaStrategy implements Strategy {

    @Override
    public void aplicarTipo(Context context, String tipo, User usuario, boolean appMuted) {
        Intent intent = new Intent(context, Gestor.class);
        intent.putExtra("tipo",tipo);
        intent.putExtra("user", usuario);
        intent.putExtra("muted", appMuted);
        intent.putExtra("numPreg", 1);
        intent.putExtra("puntosAcum", 0);
        intent.putExtra("puntosCons", 0);
        intent.putExtra("vidasPreg", 2);
        intent.putExtra("consolidado", false);
        context.startActivity(intent);
    }
}
