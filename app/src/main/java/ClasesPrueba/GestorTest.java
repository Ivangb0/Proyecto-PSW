package ClasesPrueba;

import static org.junit.Assert.assertTrue;

import com.example.odswix.Gestor;

import org.junit.Test;

import BusinessLogic.Ahorcado;
import BusinessLogic.Frase;
import BusinessLogic.Pregunta;

public class GestorTest {
    @Test
    public void construirFraseFacilTest(){
        Gestor gestor = new Gestor();

        Frase frase = gestor.construirFrase(1);
        assertTrue(frase.getDificultad().equals("Facil"));
    }
    @Test
    public void construirFraseMedioTest(){
        Gestor gestor = new Gestor();

        Frase frase = gestor.construirFrase(5);
        assertTrue(frase.getDificultad().equals("Medio"));
    }
    @Test
    public void construirFraseDificilTest(){
        Gestor gestor = new Gestor();

        Frase frase = gestor.construirFrase(9);
        assertTrue(frase.getDificultad().equals("Dificil"));
    }
    @Test
    public void construirPreguntaFacilTest(){
        Gestor gestor = new Gestor();

        Pregunta pregunta = gestor.construirPregunta(1);
        assertTrue(pregunta.getDificultad().equals("Facil"));
    }
    @Test
    public void construirPreguntaMedioTest(){
        Gestor gestor = new Gestor();

        Pregunta pregunta = gestor.construirPregunta(5);
        assertTrue(pregunta.getDificultad().equals("Medio"));
    }
    @Test
    public void construirPreguntaDificilTest(){
        Gestor gestor = new Gestor();

        Pregunta pregunta = gestor.construirPregunta(9);
        assertTrue(pregunta.getDificultad().equals("Dificil"));
    }
    @Test
    public void construirAhorcadoFacilTest(){
        Gestor gestor = new Gestor();

        Ahorcado ahorcado = gestor.construirAhorcado(1);
        assertTrue(ahorcado.getDificultad().equals("Facil"));
    }
    @Test
    public void construirAhorcadoMedioTest(){
        Gestor gestor = new Gestor();

        Ahorcado ahorcado = gestor.construirAhorcado(5);
        assertTrue(ahorcado.getDificultad().equals("Medio"));
    }
    @Test
    public void construirAhorcadoDificilTest(){
        Gestor gestor = new Gestor();

        Ahorcado ahorcado = gestor.construirAhorcado(9);
        assertTrue(ahorcado.getDificultad().equals("Dificil"));
    }
}
