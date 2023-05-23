package CalsesBuilder;

import java.io.Serializable;

public class Director implements Serializable {
    public Director(){}

    public void construirPregunta(Builder builder){
        builder.reset();
        builder.buildDificultad();
        builder.buildEnunciado();
        builder.buildTimer();
        builder.buildRespuestas();
    }
    public void construirFrase(Builder builder){
        builder.reset();
        builder.buildDificultad();
        builder.buildEnunciado();
        builder.buildTimer();
        builder.buildRespuestas();
    }

    public void construirAhorcado(Builder builder){
        builder.reset();
        builder.buildDificultad();
        builder.buildEnunciado();
        builder.buildTimer();
        builder.buildRespuestas();
    }
}
