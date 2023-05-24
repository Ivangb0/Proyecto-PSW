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
    @DatabaseField
    public int ganadas;
    @DatabaseField
    public int perdidas;
    @DatabaseField
    public int abandonadas;
    @DatabaseField
    public int aciertos;
    @DatabaseField
    public int fallos;
    @DatabaseField
    public int tiempoUso;

    @DatabaseField
    public int nivel;


    //constructor vacio para que la libreria del DAO funcione
    User(){}

    //constructor de la clase user
    public User (int idUs, String usern, String email, String pswrd, int ptosAcum, int trof, int medals, int ganad, int perdi, int aband, int acie, int fall, int tiempoU, int nivel){
        this.id_user = idUs;
        this.username = usern;
        this.email = email;
        this.password = pswrd;
        this.puntosAcumTotales = ptosAcum;
        this.trofeos = trof;
        this.medallas = medals;
        this.ganadas = ganad;
        this.perdidas = perdi;
        this.abandonadas = aband;
        this.aciertos = acie;
        this.fallos = fall;
        this.tiempoUso = tiempoU;
        this.nivel = nivel;
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

    public int getPuntosAcumTotales(){
        return puntosAcumTotales;
    }

    public int getMedallas(){
        return medallas;
    }

    public int getTrofeos(){
        return trofeos;
    }

    public int getGanadas() {return ganadas;}

    public int getPerdidas() {return perdidas;}
    public int getAbandonadas() {return abandonadas;}
    public int getAciertos(){return aciertos;}
    public int getFallos(){return fallos;}
    public int getTiempoUso(){return tiempoUso;}
    public int getNivel(){return nivel;}

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

    public void setPuntosAcumTotales (int newPuntosAcum){
        this.puntosAcumTotales = newPuntosAcum;
    }

    public void setMedallas (int newMedallas){
        this.medallas = newMedallas;
    }

    public void setTrofeos (int newTrofeos){
        this.trofeos = newTrofeos;
    }

    public void setGanadas (int newGanadas) { this.ganadas = newGanadas;}

    public void setPerdidas (int newPerdidas) { this.perdidas = newPerdidas;}

    public void setAbandonadas (int newAbandonadas) { this.abandonadas = newAbandonadas;}

    public void setAciertos (int newAciertos) {this.aciertos = newAciertos;}

    public void setFallos (int newFallos) {this.fallos = newFallos;}

    public void setTiempoUso (int newTiempoUso) {this.tiempoUso = newTiempoUso;}

    public void setNivel (int newNivel) {this.nivel = newNivel;}
}
