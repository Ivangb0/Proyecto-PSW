package ClasesObserver;

import com.example.odswix.Estadisticas;

import java.util.ArrayList;
import java.util.List;

public class GestorEstadisticas {

    private List<EstadisticasObserver> observers = new ArrayList<>();


    public void registrarObserver(EstadisticasObserver observer){
        observers.add(observer);
    }

    public void eliminarObserver(EstadisticasObserver observer){
        observers.remove(observer);
    }


    public void notificarEstadisticasActualizadas() {
        for(EstadisticasObserver observer : observers){
            observer.onEstadisticasActualizadas();
        }
    }

}
