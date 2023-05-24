package CalsesBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import BusinessLogic.Ahorcado;
import BusinessLogic.Hanged;
import Persistence.AhorcadoDAO;



public class RetoAhorcadoFacilBuilder implements Builder{
    private Ahorcado ahorcado;
    private Hanged ahorcadoFiltrado;
    private List<Hanged> listaAhorcado;

    public void reset() {
        ahorcado = new Ahorcado();
    }

    public void buildEnunciado(){
        ahorcadoFiltrado = filtrarDificultad(this.ahorcado.getDificultad());
        ahorcado.setHanged(ahorcadoFiltrado);
        ahorcado.setEnunciado(ahorcadoFiltrado.getDescripcion());
    }
    private Hanged filtrarDificultad(String dificultad){
        AhorcadoDAO ahorcado = new AhorcadoDAO();
        listaAhorcado = ahorcado.obtenerTodos();
        List<Hanged> aux = new ArrayList<>();
        for(int i = 0; i<listaAhorcado.size();i++){
            if(listaAhorcado.get(i).getDificultad().equals(dificultad)){
                aux.add(listaAhorcado.get(i));
            }
        }
        Random random = new Random();
        int resultado = random.nextInt(aux.size());
        Hanged res = aux.get(resultado);
        return res;
    }
    public void buildTimer(){
        ahorcado.setTimer(120);
    }
    public void buildDificultad(){ ahorcado.setDificultad("Facil"); }
    public void buildRespuestas(){
        for(int i = 0; i < listaAhorcado.size();i++) {
            if (listaAhorcado.get(i).getIdPregunta() == ahorcadoFiltrado.getIdPregunta()) {
                ahorcado.setRespuesta(listaAhorcado.get(i).getRespuesta());
            }
        }
    }
    public Ahorcado getTipo(){
        return ahorcado;
    }

}
