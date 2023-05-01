package BusinessLogic;

import java.io.Serializable;
import java.util.List;

public class Frase implements Serializable {
    private String enunciado;
    private int timer;
    private String dificultad;
    private List<Answer> respuestas;
    private Question question;

    public Frase(){}

    public void setEnunciado(String enunciado){
        this.enunciado = enunciado;
    }
    public String getEnunciado(){
        return this.enunciado;
    }
    public void setTimer(int timer){
        this.timer = timer;
    }
    public int getTimer(){
        return this.timer;
    }
    public void setDificultad(String dificultad){
        this.dificultad = dificultad;
    }
    public String getDificultad(){
        return this.dificultad;
    }
    public void setRespuesta(List<Answer> respuestas){
        this.respuestas = respuestas;
    }
    public List<Answer> getRespuesta(){
        return this.respuestas;
    }

    /*public void setQuestion(Question question){
        this.question = question;
    }
    public Question getQuestion(){ return this.question; }
     */
}
