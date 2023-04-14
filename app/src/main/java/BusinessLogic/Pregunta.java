package BusinessLogic;

import java.io.Serializable;
import java.util.List;

public class Pregunta implements Serializable {
    private String enunciado;
    private int timer;
    private String dificultad;
    private List<Answer> respuestas;
    private int pntsAcum;

    public Pregunta(){}

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
    public void setPuntosAcum(int pntsAcum){
        this.pntsAcum = pntsAcum;
    }
    public int getPuntosAcum(){
        return this.pntsAcum;
    }
    public void setDificultad(String dificultad){
        this.dificultad = dificultad;
    }
    public String getDificultad(){
        return this.dificultad;
    }
    public void setRespuestas(List<Answer> respuestas){
        this.respuestas = respuestas;
    }
    public List<Answer> getRespuestas(){
        return this.respuestas;
    }
}
