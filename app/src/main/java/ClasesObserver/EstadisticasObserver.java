package ClasesObserver;

public interface EstadisticasObserver {

    void onEstadisticasActualizadas(int aciertos, int fallos, int ganadas, int perdidas, int abandonadas);
}
