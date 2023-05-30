package ClasesEstado;

import com.example.odswix.CatalogoRetos;

public class Nivel2EstadoReto implements EstadoReto{
    @Override
    public void configurarVistas(CatalogoRetos menu) {
        menu.desbloquearRetoPregunta(true);
        menu.desbloquearRetoCompletarFrase(true);
        menu.desbloquearRetoAhorcado(true);
        menu.desbloquearRetoMixto(false);
    }
}
