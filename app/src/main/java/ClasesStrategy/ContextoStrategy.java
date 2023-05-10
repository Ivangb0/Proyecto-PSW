package ClasesStrategy;

import android.content.Context;

import BusinessLogic.User;

public class ContextoStrategy {
    private Strategy partida;

    public ContextoStrategy(Strategy estrategia){
        partida = estrategia;
    }
    public void elegirTipo(Context context, String tipo, User usuario, boolean appMuted){
        partida.aplicarTipo(context, tipo, usuario, appMuted);
    }
} /*
    public ContextoStrategy(Strategy part, Button bot){
        asignarBotones();
        this.partida = part;
        if(bot == botonPregunta)
            this.botonFrase = bot;
        else if(bot == botonFrase)
            this.botonFrase = bot;
        else if(bot == botonMixto){
            this.botonMixto = bot;
        }
    }

    private void asignarBotones(){
        botonPregunta = CatalogoRetos.botonPregunta;
        botonFrase = CatalogoRetos.botonFrase;
        botonMixto = CatalogoRetos.botonMixto;
    }

    //metodos para identificar que boton se ha presionado


    private boolean partidaPregunta(){
        //si el boton clickado es el de pregunta return true, sino return false
        if(botonPregunta.isPressed()) return true;
        else return false;
    }

    private boolean partidaFrase(){
        if(botonFrase.isPressed()) return true;
        else return false;
    }
    private boolean partidaMixta(){
        if(botonMixto.isPressed()) return true;
        else return false;
    }
}*/
