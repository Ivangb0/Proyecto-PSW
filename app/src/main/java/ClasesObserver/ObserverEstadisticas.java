package ClasesObserver;

import com.example.odswix.Estadisticas;

public class ObserverEstadisticas implements EstadisticasObserver {

    Estadisticas estadisticas;

    public ObserverEstadisticas(Estadisticas estadisticas){
        this.estadisticas = estadisticas;
    }
    @Override
    public void onEstadisticasActualizadas() {
        estadisticas.onEstadisticasActualizadas();
    }
}
