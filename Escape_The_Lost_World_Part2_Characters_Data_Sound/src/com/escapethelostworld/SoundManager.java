package com.escapethelostworld;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import java.util.List;


class SoundManager {
    boolean musicEnabled=true, sfxEnabled=true;
    private Clip musicClip;
    private String currentTrack="";

    void setEnabled(boolean b){ setMusicEnabled(b); setSfxEnabled(b); }
    void setMusicEnabled(boolean b){ musicEnabled=b; if(!b) stopMusic(); }
    void setSfxEnabled(boolean b){ sfxEnabled=b; }

    void play(int type){
        if(!sfxEnabled)return;
        String[] names={"coin","jump","power","hit"};
        int idx=Math.max(0,Math.min(names.length-1,type));
        playFile("/sounds/"+names[idx]+".wav", type==3?0.90f:0.70f);
    }
    void click(){ if(sfxEnabled) playFile("/sounds/click.wav",0.55f); }
    void slide(){ if(sfxEnabled) playFile("/sounds/jump.wav",0.38f); }
    void roar(){ if(sfxEnabled) playFile("/sounds/roar.wav",0.75f); }

    void playMenuMusic(){ playLoop("home_ambient.wav",0.18f); }
    void playRunMusic(){ playLoop("run_adventure.wav",0.22f); }
    void playChaseMusic(){ playLoop("monster_chase.wav",0.28f); }
    void playVictoryMusic(){ playOnce("victory.wav",0.55f); }
    void playGameOverMusic(){ playOnce("gameover.wav",0.50f); }
    void playStartMusic(){ playOnce("start_theme.wav",0.55f); }

    private void playLoop(String file,float volume){
        if(!musicEnabled)return;
        if(file.equals(currentTrack) && musicClip!=null && musicClip.isRunning())return;
        stopMusic();
        try(InputStream in=Main.class.getResourceAsStream("/music/"+file)){
            if(in==null)return;
            try(AudioInputStream ais=AudioSystem.getAudioInputStream(new BufferedInputStream(in))){
                musicClip=AudioSystem.getClip(); musicClip.open(ais); setVolume(musicClip,volume);
                currentTrack=file; musicClip.loop(Clip.LOOP_CONTINUOUSLY); musicClip.start();
            }
        }catch(Exception ignored){ musicClip=null; currentTrack=""; }
    }
    private void playOnce(String file,float volume){
        if(!musicEnabled)return;
        stopMusic();
        try(InputStream in=Main.class.getResourceAsStream("/music/"+file)){
            if(in==null)return;
            try(AudioInputStream ais=AudioSystem.getAudioInputStream(new BufferedInputStream(in))){
                Clip clip=AudioSystem.getClip(); clip.open(ais); setVolume(clip,volume);
                clip.addLineListener(ev->{if(ev.getType()==LineEvent.Type.STOP)clip.close();});
                clip.start();
            }
        }catch(Exception ignored){}
    }
    private void playFile(String resource,float volume){
        try(InputStream in=Main.class.getResourceAsStream(resource)){
            if(in==null)return;
            try(AudioInputStream ais=AudioSystem.getAudioInputStream(new BufferedInputStream(in))){
                Clip clip=AudioSystem.getClip(); clip.open(ais); setVolume(clip,volume);
                clip.addLineListener(ev->{if(ev.getType()==LineEvent.Type.STOP)clip.close();}); clip.start();
            }
        }catch(Exception ignored){}
    }
    private void setVolume(Clip clip,float volume){
        try{
            FloatControl gain=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            float db=(float)(20*Math.log10(Math.max(.001,Math.min(1,volume))));
            gain.setValue(Math.max(gain.getMinimum(),Math.min(gain.getMaximum(),db)));
        }catch(Exception ignored){}
    }
    void stopMusic(){ if(musicClip!=null){try{musicClip.stop();musicClip.close();}catch(Exception ignored){}} musicClip=null; currentTrack=""; }
}

