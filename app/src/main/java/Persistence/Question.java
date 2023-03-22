package Persistence;

import java.util.Enumeration;

public class Question {
    //Atributos de la clase Question
    public int idPregunta;
    public enum dificultad {
        Facil,
        Medio,
        Dificil
    }
    public dificultad dificultad;
    public int ods;
    public String enunciado;
    public int puntosPregunta;

    //Constructor de la clase Question

    public Question (int idPreg, dificultad difi, int ods, String enunci, int puntosPreg){
        this.idPregunta = idPreg;
        this.dificultad = difi;
        this.ods = ods;
        this.enunciado = enunci;
        this.puntosPregunta = puntosPreg;
    }

    //metodos get de la clase Question

    public int getIdPregunta() { return idPregunta; }

    public dificultad getDificultad() { return dificultad; }

    public int getOds(){
        return ods;
    }

    public String getEnunciado(){
        return enunciado;
    }

    public int getPuntosPregunta() { return puntosPregunta; }

    //metodos set de la clase Question

    public void setIdPregunta(int newIdPregunta){ this.idPregunta = newIdPregunta; }

    public void setDificultad(dificultad newDificultad){ this.dificultad = newDificultad; }

    public void setOds(int newOds){ this.ods = newOds; }

    public void setEnunciado(String newEnunciado){ this.enunciado = newEnunciado; }

    public void setPuntosPregunta(int newPuntosPregunta){ this.puntosPregunta = newPuntosPregunta; }
}
