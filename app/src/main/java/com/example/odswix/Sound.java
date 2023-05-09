package com.example.odswix;

import android.content.Context;
import android.media.MediaPlayer;

public class Sound {
    MediaPlayer soundAcierto;
    MediaPlayer soundBackground;
    MediaPlayer soundFallo;
    MediaPlayer soundCountdown10s;
    MediaPlayer soundVictoria;
    MediaPlayer soundDerrota;
    public MediaPlayer getSoundAcierto(Context context){
        soundAcierto = MediaPlayer.create(context, R.raw.acierto);
        return soundAcierto;
    }
    public MediaPlayer getSoundBackground(Context context){
        soundBackground = MediaPlayer.create(context, R.raw.backgroundmusic);
        return soundBackground;
    }
    public MediaPlayer getSoundFallo(Context context){
        soundFallo = MediaPlayer.create(context, R.raw.musicafracaso);
        return soundFallo;
    }
    public MediaPlayer getSoundCountdown10s(Context context){
        soundCountdown10s = MediaPlayer.create(context, R.raw.countdown10secs);
        return soundCountdown10s;
    }
    public MediaPlayer getSoundVictoria(Context context){
        soundVictoria = MediaPlayer.create(context, R.raw.victoria);
        return soundVictoria;
    }
    public MediaPlayer getSoundDerrota(Context context){
        soundDerrota = MediaPlayer.create(context, R.raw.gameover);
        return soundDerrota;
    }
}
