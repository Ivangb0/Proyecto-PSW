package BusinessLogic;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Enumeration;

@DatabaseTable
public class Question {
    //Atributos de la clase Question
    @DatabaseField(id = true)
    public int id_Pregunta;
    @DatabaseField
    public String dificultad;
    @DatabaseField
    public int ods;
    @DatabaseField
    public String enunciado;
    @DatabaseField
    public int puntos;

    //constructor vacio para que la libreria del DAO funcione
    Question(){}

    //Constructor de la clase Question

    public Question (int idPreg, String difi, int ods, String enunci, int puntosPreg){
        // Verificar que la dificultad sea "facil", "medio" o "dificil"
        if (difi.equals("facil") || difi.equals("medio") || difi.equals("dificil")) {
            this.id_Pregunta = idPreg;
            this.dificultad = difi;
            this.ods = ods;
            this.enunciado = enunci;
            this.puntos = puntosPreg;
        }
    }

    //metodos get de la clase Question

    public int getIdPregunta() { return id_Pregunta; }

    public String getDificultad() { return dificultad; }

    public int getOds(){
        return ods;
    }

    public String getEnunciado(){
        return enunciado;
    }

    public int getPuntosPregunta() { return puntos; }

    //metodos set de la clase Question

    public void setIdPregunta(int newIdPregunta){ this.id_Pregunta = newIdPregunta; }

    public void setDificultad(String newDificultad){
        if (newDificultad.equals("facil") || newDificultad.equals("medio") || newDificultad.equals("dificil")) {
            this.dificultad = newDificultad;
        }
    }

    public void setOds(int newOds){ this.ods = newOds; }

    public void setEnunciado(String newEnunciado){ this.enunciado = newEnunciado; }

    public void setPuntosPregunta(int newPuntosPregunta){ this.puntos = newPuntosPregunta; }
}
