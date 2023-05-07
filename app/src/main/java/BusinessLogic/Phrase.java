package BusinessLogic;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "frase")
public class Phrase implements Serializable {
    @DatabaseField(id = true)
    public int id_frase;
    @DatabaseField
    public int id_ods;
    @DatabaseField
    public String descripcion;
    @DatabaseField
    public String frase;
    @DatabaseField
    public int puntos;
    @DatabaseField
    public String dificultad;

    Phrase(){}

    public Phrase (int idPreg, String difi, int ods, String enunci, int puntosPreg, String frase){
        // Verificar que la dificultad sea "facil", "medio" o "dificil"
        if (difi.equals("facil") || difi.equals("medio") || difi.equals("dificil")) {
            this.id_frase = idPreg;
            this.dificultad = difi;
            this.id_ods = ods;
            this.descripcion = enunci;
            this.puntos = puntosPreg;
            this.frase=frase;
        }
    }

    public int getIdPregunta() { return id_frase; }

    public String getDificultad() { return dificultad; }

    public int getOds(){
        return id_ods;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public String getFrase(){
        return frase;
    }

    public int getPuntosPregunta() { return puntos; }

    //metodos set de la clase Question

    public void setIdPregunta(int newIdPregunta){ this.id_frase = newIdPregunta; }

    public void setDificultad(String newDificultad){
        if (newDificultad.equals("facil") || newDificultad.equals("medio") || newDificultad.equals("dificil")) {
            this.dificultad = newDificultad;
        }
    }

    public void setOds(int newOds){ this.id_ods = newOds; }

    public void setDescripcion(String newEnunciado){ this.descripcion = newEnunciado; }

    public void setFrase(String newFrase){ this.frase = newFrase; }

    public void setPuntosPregunta(int newPuntosPregunta){ this.puntos = newPuntosPregunta; }
}
