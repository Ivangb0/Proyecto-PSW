package BusinessLogic;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Answer {
    //Atributos de la clase Answer
    @DatabaseField(id = true)
    public int id_respuesta;

    @DatabaseField
    public String respuesta;

    @DatabaseField
    public int pregunta_id;

    @DatabaseField
    public boolean esCorrecta;

    //Constructor
    public Answer(int id_resp, String resp, int preg_id, boolean esCorrec) {
        this.id_respuesta = id_resp;
        this.respuesta = resp;
        this.pregunta_id = preg_id;
        this.esCorrecta = esCorrec;
    }

    //Metodos get de la clase answer

    public int getId_respuesta(){ return id_respuesta;}

    public String getRespuesta(){return respuesta;}

    public int getPregunta_id(){return pregunta_id;}

    public boolean getEsCorrecta(){return esCorrecta;}

    //Metodos set de la clase answer

    public void setId_respuesta(int newId_respuesta){ this.id_respuesta = newId_respuesta;}

    public void setRespuesta(String newRespuesta){this.respuesta = newRespuesta;}

    public void setPregunta_id(int newPregunta_id){this.pregunta_id = newPregunta_id;}

    public void setEsCorrecta(boolean newesCorrecta){this.esCorrecta = newesCorrecta;}

}
