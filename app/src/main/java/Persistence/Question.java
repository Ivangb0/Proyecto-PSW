package Persistence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Enumeration;

@DatabaseTable
public class Question {
    //Atributos de la clase Question
    @DatabaseField(id = true)
    public int idPregunta;
    @DatabaseField
    public String dificultad;
    @DatabaseField
    public int ods;
    @DatabaseField
    public String enunciado;
    @DatabaseField
    public int puntosPregunta;

    //Constructor de la clase Question

    public Question (int idPreg, String difi, int ods, String enunci, int puntosPreg){
        // Verificar que la dificultad sea "facil", "medio" o "dificil"
        if (difi.equals("facil") || difi.equals("medio") || difi.equals("dificil")) {
            this.idPregunta = idPreg;
            this.dificultad = difi;
            this.ods = ods;
            this.enunciado = enunci;
            this.puntosPregunta = puntosPreg;
        }
    }

    //metodos get de la clase Question

    public int getIdPregunta() { return idPregunta; }

    public String getDificultad() { return dificultad; }

    public int getOds(){
        return ods;
    }

    public String getEnunciado(){
        return enunciado;
    }

    public int getPuntosPregunta() { return puntosPregunta; }

    //metodos set de la clase Question

    public void setIdPregunta(int newIdPregunta){ this.idPregunta = newIdPregunta; }

    public void setDificultad(String newDificultad){
        if (newDificultad.equals("facil") || newDificultad.equals("medio") || newDificultad.equals("dificil")) {
            this.dificultad = newDificultad;
        }
    }

    public void setOds(int newOds){ this.ods = newOds; }

    public void setEnunciado(String newEnunciado){ this.enunciado = newEnunciado; }

    public void setPuntosPregunta(int newPuntosPregunta){ this.puntosPregunta = newPuntosPregunta; }
}
