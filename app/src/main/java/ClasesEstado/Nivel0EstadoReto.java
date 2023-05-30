package ClasesEstado;

import com.example.odswix.CatalogoRetos;

public class Nivel0EstadoReto implements EstadoReto{
    @Override
    public void configurarVistas(CatalogoRetos menu) {
        menu.desbloquearRetoPregunta(true);
        menu.desbloquearRetoCompletarFrase(false);
        menu.desbloquearRetoAhorcado(false);
        menu.desbloquearRetoMixto(false);
    }
}
