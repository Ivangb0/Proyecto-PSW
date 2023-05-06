package CalsesBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import BusinessLogic.Frase;
import BusinessLogic.Phrase;
import Persistence.PhraseDAO;

public class RetoFraseFacilBuilder implements Builder, Serializable {
    private Frase frase;
    private List<Phrase> listaPreguntas;
    private Phrase preguntaFiltrada;

    public void reset(){
        frase = new Frase();
    }
    public void buildEnunciado(){
        preguntaFiltrada = filtrarDificultad(this.frase.getDificultad());
        frase.setEnunciado(preguntaFiltrada.getDescripcion());
    }
    private Phrase filtrarDificultad(String dificultad){
        PhraseDAO phrases = new PhraseDAO();
        listaPreguntas = phrases.obtenerTodos();
        List<Phrase> aux = new ArrayList<>();
        for(int i = 0; i<listaPreguntas.size();i++){
            if(listaPreguntas.get(i).getDificultad().equals(dificultad)){
                aux.add(listaPreguntas.get(i));
            }
        }
        Random random = new Random();
        int resultado = random.nextInt(aux.size());
        Phrase res = aux.get(resultado);
        return res;
    }
    public void buildTimer(){
        frase.setTimer(120);
    }
    public void buildDificultad(){ frase.setDificultad("Facil"); }
    public void buildRespuestas(){
        for(int i = 0; i < listaPreguntas.size();i++) {
            if (listaPreguntas.get(i).getIdPregunta() == preguntaFiltrada.getIdPregunta()) {
                frase.setRespuesta(listaPreguntas.get(i).getFrase());
            }
        }
    }

    public Frase getTipo(){
        return frase;
    }
}