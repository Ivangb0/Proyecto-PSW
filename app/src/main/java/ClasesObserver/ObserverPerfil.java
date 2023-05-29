package ClasesObserver;

import com.example.odswix.Perfil;

public class ObserverPerfil implements EstadisticasObserver{
    Perfil perfil;

    public ObserverPerfil(Perfil perfil) {
        this.perfil = perfil;

    }

    @Override
    public void onEstadisticasActualizadas() {
        perfil.obserSetDatos();

    }
}
