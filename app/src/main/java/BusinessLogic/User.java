package BusinessLogic;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;


@DatabaseTable
public class User implements Serializable {

    //atributos de la clase user
    @DatabaseField(id = true)
    public int id_user;
    @DatabaseField
    public String username;
    @DatabaseField
    public String email;
    @DatabaseField
    public String password;
    @DatabaseField
    public int puntosAcumTotales;
    @DatabaseField
    public int trofeos;
    @DatabaseField
    public int medallas;

    //constructor vacio para que la libreria del DAO funcione
    User(){}

    //constructor de la clase user
    public User (int idUs, String usern, String email, String pswrd, int ptosAcum, int trof, int medals){
        this.id_user = idUs;
        this.username = usern;
        this.email = email;
        this.password = pswrd;
        this.puntosAcumTotales = ptosAcum;
        this.trofeos = trof;
        this.medallas = medals;
    }

    //metodos get de los atributos de la clase user
    public int getIdUser() {
        return id_user;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getPuntosAcum(){
        return puntosAcumTotales;
    }

    public int getMedallas(){
        return medallas;
    }

    public int getTrofeos(){
        return trofeos;
    }

    //metodos set de los atributos de la clase user
    public void setUsername (String newUsername){
        this.username = newUsername;
    }

    public void setEmail (String newEmail) {
        this.email = newEmail;
    }

    public void setPassword (String newPassword){
        this.password = newPassword;
    }

    public void setPuntosAcum (int newPuntosAcum){
        this.puntosAcumTotales = newPuntosAcum;
    }

    public void setMedallas (int newMedallas){
        this.medallas = newMedallas;
    }

    public void setTrofeos (int newTrofeos){
        this.trofeos = newTrofeos;
    }
}
