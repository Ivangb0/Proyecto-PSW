package com.example.odswix;

import BusinessLogic.Ahorcado;
import BusinessLogic.Frase;
import BusinessLogic.Pregunta;
import BusinessLogic.User;
import CalsesBuilder.Director;
import CalsesBuilder.RetoAhorcadoDificilBuilder;
import CalsesBuilder.RetoAhorcadoFacilBuilder;
import CalsesBuilder.RetoAhorcadoMedioBuilder;
import CalsesBuilder.RetoFraseDificilBuilder;
import CalsesBuilder.RetoFraseFacilBuilder;
import CalsesBuilder.RetoFraseMedioBuilder;
import CalsesBuilder.RetoPreguntaDificilBuilder;
import CalsesBuilder.RetoPreguntaFacilBuilder;
import CalsesBuilder.RetoPreguntaMedioBuilder;

public class GestorConstructor {
    private static int puntosAcumTotales;
    private static int puntosAcum;
    static User usuario = null;
    static boolean appMuted;

    public void init(User user, int acumTotal, int acum, boolean muted) {
        puntosAcumTotales = acumTotal;
        puntosAcum = acum;
        usuario = user;
        appMuted = muted;
    }

    public static Pregunta construirPregunta(int nPreg){
        Director director = new Director();
        Pregunta pregunta = new Pregunta();
        RetoPreguntaFacilBuilder preguntaFacilBuilder = new RetoPreguntaFacilBuilder();
        RetoPreguntaMedioBuilder preguntaMedioBuilder = new RetoPreguntaMedioBuilder();
        RetoPreguntaDificilBuilder preguntaDificilBuilder = new RetoPreguntaDificilBuilder();
        if (nPreg < 4) {
            director.construirPregunta(preguntaFacilBuilder);
            pregunta = preguntaFacilBuilder.getTipo();
        } else if (nPreg < 7) {
            director.construirPregunta(preguntaMedioBuilder);
            pregunta = preguntaMedioBuilder.getTipo();
        } else {
            director.construirPregunta(preguntaDificilBuilder);
            pregunta = preguntaDificilBuilder.getTipo();
        }
        return pregunta;
    }

    public Ahorcado construirAhorcado(int npreg){
        Director director = new Director();
        Ahorcado ahorcado = new Ahorcado();
        RetoAhorcadoFacilBuilder ahorcadoFacilBuilder = new RetoAhorcadoFacilBuilder();
        RetoAhorcadoMedioBuilder ahorcadoMedioBuilder = new RetoAhorcadoMedioBuilder();
        RetoAhorcadoDificilBuilder ahorcadoDificilBuilder = new RetoAhorcadoDificilBuilder();

        if (npreg < 4) {
            director.construirAhorcado(ahorcadoFacilBuilder);
            ahorcado = ahorcadoFacilBuilder.getTipo();
        } else if (npreg < 7) {
            director.construirAhorcado(ahorcadoMedioBuilder);
            ahorcado = ahorcadoMedioBuilder.getTipo();
        } else {
            director.construirAhorcado(ahorcadoDificilBuilder);
            ahorcado = ahorcadoDificilBuilder.getTipo();
        }
        return ahorcado;
    }

    public Frase construirFrase(int nPreg){
        Frase tipoFrase = new Frase();
        Director director = new Director();
        RetoFraseFacilBuilder fraseFacilBuilder = new RetoFraseFacilBuilder();
        RetoFraseMedioBuilder fraseMedioBuilder = new RetoFraseMedioBuilder();
        RetoFraseDificilBuilder fraseDificilBuilder = new RetoFraseDificilBuilder();
        if (nPreg < 4) {
            director.construirFrase(fraseFacilBuilder);
            tipoFrase = fraseFacilBuilder.getTipo();
        } else if (nPreg < 7) {
            director.construirFrase(fraseMedioBuilder);
            tipoFrase = fraseMedioBuilder.getTipo();
        } else {
            director.construirFrase(fraseDificilBuilder);
            tipoFrase = fraseDificilBuilder.getTipo();
        }
        return tipoFrase;
    }
}
