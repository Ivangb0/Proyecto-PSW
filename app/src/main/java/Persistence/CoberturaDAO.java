package Persistence;

import com.example.odswix.IniciarSesion;
import com.j256.ormlite.stmt.query.In;

import java.util.ArrayList;
import java.util.List;

import BusinessLogic.Cobertura;
import BusinessLogic.User;

public class CoberturaDAO extends DBA<Cobertura> {
    public CoberturaDAO() {
        init(Cobertura.class);
    }

    List<Cobertura> listaCoberturas = new ArrayList<>();

    User usu = IniciarSesion.usuario;

    public List<Double> getListaFallos() {
        listaCoberturas = this.obtenerTodos();

        List<Double> listaFallos = new ArrayList<Double>();

        for (int i = 0; i < listaCoberturas.size(); i++) {
            if (listaCoberturas.get(i).getId_user() == usu.getIdUser()) {
                listaFallos.add((double) listaCoberturas.get(i).getFallos());
            }
        }
        return listaFallos;
    }

    public List<Double> getListaAciertos() {
        listaCoberturas = this.obtenerTodos();

        List<Double> listaAciertos = new ArrayList<Double>();

        for (int j = 0; j < listaCoberturas.size(); j++) {
            if (listaCoberturas.get(j).getId_user() == usu.getIdUser()) {
                listaAciertos.add( (double) listaCoberturas.get(j).getAciertos());
            }
        }
        return listaAciertos;
    }
}
