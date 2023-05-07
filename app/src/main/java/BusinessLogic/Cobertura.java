package BusinessLogic;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
@DatabaseTable
public class Cobertura implements Serializable {
    @DatabaseField(id = true)
    public int id_ods;

    @DatabaseField
    public int id_user;

    @DatabaseField
    public int aciertos;

    @DatabaseField
    public int fallos;

    Cobertura(){}

    public Cobertura(int id_ods, int id_user, int acier, int fall){
        this.id_ods = id_ods;
        this.id_user = id_user;
        this.aciertos = acier;
        this.fallos = fall;
    }

    //metodos get de la clase coberturaods
    public int getId_ods(){return id_ods;}

    public int getId_user(){return id_user;}

    public int getAciertos(){return aciertos;}

    public int getFallos(){return fallos;}


    public void  setId_ods(int newId_ods){this.id_ods = newId_ods;}

    public void setId_user(int newId_user){this.id_user = newId_user;}

    public void setAciertos(int newAciertos){this.aciertos = newAciertos;}

    public void setFallos(int newFallos){this.fallos = newFallos;}
}

