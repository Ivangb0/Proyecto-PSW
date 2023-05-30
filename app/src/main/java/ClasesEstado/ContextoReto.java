package ClasesEstado;

import com.example.odswix.CatalogoRetos;

public class ContextoReto {

        private EstadoReto estadoActual;

        public void setEstado(EstadoReto estado) {
            estadoActual = estado;
        }

        public void configurarVistas(CatalogoRetos menu) {
            estadoActual.configurarVistas(menu);
        }

}
