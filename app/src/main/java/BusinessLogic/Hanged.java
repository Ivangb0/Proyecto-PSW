package BusinessLogic;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "ahorcado")
public class Hanged implements Serializable {
    @DatabaseField(id = true)
    public int id_ahorcado;
    @DatabaseField
    public int id_ods;
    @DatabaseField
    public String descripcion;
    @DatabaseField
    public String respuesta;
    @DatabaseField
    public int puntos;
    @DatabaseField
    public String dificultad;

    Hanged(){}

    public Hanged (int idAhorc, String difi, int ods, String resp, int puntosPreg, String desc){
        // Verificar que la dificultad sea "facil", "medio" o "dificil"
        if (difi.equals("facil") || difi.equals("medio") || difi.equals("dificil")) {
            this.id_ahorcado = idAhorc;
            this.dificultad = difi;
            this.id_ods = ods;
            this.respuesta = resp;
            this.puntos = puntosPreg;
            this.descripcion = desc;
        }
    }

    public int getIdPregunta() { return id_ahorcado; }

    public String getDificultad() { return dificultad; }

    public int getOds(){
        return id_ods;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public String getRespuesta(){
        return respuesta;
    }

    public int getPuntosPregunta() { return puntos; }

    //metodos set de la clase Question

    public void setIdPregunta(int newIdPregunta){ this.id_ahorcado = newIdPregunta; }

    public void setDificultad(String newDificultad){
        if (newDificultad.equals("facil") || newDificultad.equals("medio") || newDificultad.equals("dificil")) {
            this.dificultad = newDificultad;
        }
    }
    public void setOds(int newOds){ this.id_ods = newOds; }

    public void setDescripcion(String newEnunciado){ this.descripcion = newEnunciado; }

    public void setRespuesta(String newResp){ this.respuesta = newResp; }

    public void setPuntosPregunta(int newPuntosPregunta){ this.puntos = newPuntosPregunta; }
}
