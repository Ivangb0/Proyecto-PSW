package ClasesObserver;

import com.example.odswix.Estadisticas;

import java.util.ArrayList;
import java.util.List;

public class GestorEstadisticas {

    private List<EstadisticasObserver> observers = new ArrayList<>();
    private int obsAciertos;
    private int obsFallos;
    private int obsGanadas;
    private int obsPerdidas;
    private int obsAbandonadas;
    //EstadisticasObservable estadisticasObservable;


    public void registrarObserver(EstadisticasObserver observer){
        observers.add(observer);
    }

    public void eliminarObserver(EstadisticasObserver observer){
        observers.remove(observer);
    }

    public void actualizarEstadisticas(int obsAci, int obsFall, int obsGan, int obsPer, int obsAba){
        this.obsAciertos = obsAci;
        this.obsFallos = obsFall;
        this.obsGanadas = obsGan;
        this.obsPerdidas = obsPer;
        this.obsAbandonadas = obsAba;
        notificarEstadisticasActualizadas();
    }

    private void notificarEstadisticasActualizadas() {
        for(EstadisticasObserver observer : observers){
            observer.onEstadisticasActualizadas(obsAciertos,obsFallos,obsGanadas,obsPerdidas,obsAbandonadas);
        }
    }

}
