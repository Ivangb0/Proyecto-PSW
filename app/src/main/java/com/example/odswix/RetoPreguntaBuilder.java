package com.example.odswix;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import BusinessLogic.Answer;
import BusinessLogic.Pregunta;
import BusinessLogic.Question;
import Persistence.AnswerDAO;
import Persistence.QuestionDAO;

public class RetoPreguntaBuilder implements Builder{
    private Pregunta pregunta;
    private List<Question> listaPreguntas;
    private Question preguntaFiltrada;
    private List<Answer> listaRespuestas;
    private List<Answer> respuestasPreg;
    public void reset(){
        pregunta = new Pregunta();
    }
    public void buildEnunciado(){
        preguntaFiltrada = filtrarDificultad(this.pregunta.getDificultad());
        pregunta.setEnunciado(preguntaFiltrada.getEnunciado());
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
        pregunta.setTimer(30);
    }
    public void buildVidas(){
        pregunta.setVidas(2);
    }
    public void buildPuntosAcum(){
        pregunta.setPuntosAcum(0);
    }
    public void buildDificultad(String dificultad){ pregunta.setDificultad(dificultad); }
    public void buildRespuestas(){
        respuestasPreg = recuperarRespuestas(preguntaFiltrada.getIdPregunta());
        pregunta.setRespuestas(respuestasPreg);
    }
    private List<Answer> recuperarRespuestas (int id_pregunta){
        AnswerDAO answers = new AnswerDAO();
        listaRespuestas = answers.obtenerTodos();
        List<Answer> resp = new ArrayList<Answer>();
        for(int i = 0; i < listaRespuestas.size();i++){
            if(listaRespuestas.get(i).getPregunta_id() == id_pregunta){
                resp.add(listaRespuestas.get(i));
            }
        }
        return resp;
    }
    public Pregunta getTipo(){
        return pregunta;
    }
}
