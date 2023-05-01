package CalsesBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import BusinessLogic.Frase;
import BusinessLogic.Question;
import Persistence.QuestionDAO;

public class RetoFraseFacilBuilder implements Builder {
    private Frase frase;
    private List<Question> listaPreguntas;
    private Question preguntaFiltrada;
    //private List<Answer> listaRespuestas;
    //private List<Answer> respuestasPreg;
    public void reset(){
        frase = new Frase();
    }
    public void buildEnunciado(){
        preguntaFiltrada = filtrarDificultad(this.frase.getDificultad());
        //frase.setQuestion(preguntaFiltrada);
        frase.setEnunciado(preguntaFiltrada.getEnunciado());
    }
    private Question filtrarDificultad(String dificultad){
        QuestionDAO questions = new QuestionDAO();
        listaPreguntas = questions.obtenerTodos();
        List<Question> aux = new ArrayList<>();
        for(int i = 0; i<listaPreguntas.size();i++){
            if(listaPreguntas.get(i).getDificultad().equals(dificultad)){
                aux.add(listaPreguntas.get(i));
            }
        }
        Random random = new Random();
        int resultado = random.nextInt(aux.size());
        Question res = aux.get(resultado);
        return res;
    }
    public void buildTimer(){
        frase.setTimer(120);
    }
    public void buildDificultad(){ frase.setDificultad("Facil"); } //Medio Dificil
    public void buildRespuestas(){

    }

    public Frase getTipo(){
        return frase;
    }
}