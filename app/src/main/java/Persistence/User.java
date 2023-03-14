package Persistence;

public class User {

    //atributos de la clase user
    public int idUser;
    public String username;
    public String email;
    public String password;
    public int puntosAcum;
    public int medallas;
    public int trofeos;

    //constructor de la clase user
    public User (int idUs, String usern, String email, String pswrd, int ptosAcum, int medals, int trof){
        this.idUser = idUs;
        this.username = usern;
        this.email = email;
        this.password = pswrd;
        this.puntosAcum = ptosAcum;
        this.medallas = medals;
        this.trofeos = trof;
    }

    //metodos get de los atributos de la clase user
    public int getIdUser() {
        return idUser;
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
        return puntosAcum;
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
        this.puntosAcum = newPuntosAcum;
    }

    public void setMedallas (int newMedallas){
        this.medallas = newMedallas;
    }

    public void setTrofeos (int newTrofeos){
        this.trofeos = newTrofeos;
    }
}
