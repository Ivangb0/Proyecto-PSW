package com.example.odswix;

public interface Builder {
     void reset();
     void buildEnunciado();
     void buildTimer();
     void buildDificultad(String dificultad);
     void buildRespuestas();
}