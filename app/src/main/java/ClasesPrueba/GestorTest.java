package ClasesPrueba;

import static org.junit.Assert.assertTrue;

import com.example.odswix.GestorConstructor;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import BusinessLogic.Ahorcado;
import BusinessLogic.Frase;
import BusinessLogic.Pregunta;

@RunWith(JUnit4.class)
public class GestorTest {
    @org.junit.Test
    public void construirFraseFacilTest(){
        GestorConstructor gestor = new GestorConstructor();

        Frase frase = gestor.construirFrase(1);
        assertTrue(frase.getDificultad().equals("Facil"));
    }
    @org.junit.Test
    public void construirFraseMedioTest(){
        GestorConstructor gestor = new GestorConstructor();

        Frase frase = gestor.construirFrase(5);
        assertTrue(frase.getDificultad().equals("Medio"));
    }
    @org.junit.Test
    public void construirFraseDificilTest(){
        GestorConstructor gestor = new GestorConstructor();

        Frase frase = gestor.construirFrase(9);
        assertTrue(frase.getDificultad().equals("Dificil"));
    }
    @org.junit.Test
    public void construirPreguntaFacilTest(){
        GestorConstructor gestor = new GestorConstructor();

        Pregunta pregunta = gestor.construirPregunta(1);
        assertTrue(pregunta.getDificultad().equals("Facil"));
    }
    @org.junit.Test
    public void construirPreguntaMedioTest(){
        GestorConstructor gestor = new GestorConstructor();

        Pregunta pregunta = gestor.construirPregunta(1);
        assertTrue(pregunta.getDificultad().equals("Facil"));
    }
    @org.junit.Test
    public void construirPreguntaDificilTest(){
        GestorConstructor gestor = new GestorConstructor();

        Pregunta pregunta = gestor.construirPregunta(9);
        assertTrue(pregunta.getDificultad().equals("Dificil"));
    }
    @org.junit.Test
    public void construirAhorcadoFacilTest(){
        GestorConstructor gestor = new GestorConstructor();

        Ahorcado ahorcado = gestor.construirAhorcado(1);
        assertTrue(ahorcado.getDificultad().equals("Facil"));
    }
    @org.junit.Test
    public void construirAhorcadoMedioTest(){
        GestorConstructor gestor = new GestorConstructor();

        Ahorcado ahorcado = gestor.construirAhorcado(5);
        assertTrue(ahorcado.getDificultad().equals("Medio"));
    }
    @org.junit.Test
    public void construirAhorcadoDificilTest(){
        GestorConstructor gestor = new GestorConstructor();

        Ahorcado ahorcado = gestor.construirAhorcado(10);
        assertTrue(ahorcado.getDificultad().equals("Dificil"));
    }
}
