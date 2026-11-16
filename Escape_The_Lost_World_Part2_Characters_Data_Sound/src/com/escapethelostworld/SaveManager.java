package com.escapethelostworld;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import java.util.List;


class SaveManager {
    final File file = new File(System.getProperty("user.home"), ".monster-run-save.properties");
    Properties p = new Properties();
    SaveManager(){ load(); }
    void load(){ if(file.exists()) try(FileInputStream in=new FileInputStream(file)){p.load(in);}catch(Exception ignored){} }
    void save(){ try(FileOutputStream out=new FileOutputStream(file)){p.store(out,"Monster Run save");}catch(Exception ignored){} }
    int coins(){return Integer.parseInt(p.getProperty("coins","500"));}
    void coins(int v){p.setProperty("coins",String.valueOf(Math.max(0,v)));save();}
    int best(){return Integer.parseInt(p.getProperty("best","0"));}
    void best(int v){if(v>best()){p.setProperty("best",String.valueOf(v));save();}}
    int selectedChar(){return Integer.parseInt(p.getProperty("char","0"));}
    void selectedChar(int v){p.setProperty("char",String.valueOf(v));save();}
    int selectedWorld(){return Integer.parseInt(p.getProperty("world","0"));}
    void selectedWorld(int v){p.setProperty("world",String.valueOf(v));save();}
    boolean sound(){return !p.getProperty("sound","true").equals("false");}
    void sound(boolean b){p.setProperty("sound",String.valueOf(b));save();}
    boolean music(){return !p.getProperty("music","true").equals("false");}
    void music(boolean b){p.setProperty("music",String.valueOf(b));save();}
    boolean sfx(){return !p.getProperty("sfx","true").equals("false");}
    void sfx(boolean b){p.setProperty("sfx",String.valueOf(b));save();}
    String difficulty(){return p.getProperty("difficulty","NORMAL");}
    void difficulty(String v){p.setProperty("difficulty",v);save();}
    String camera(){return p.getProperty("camera","CINEMATIC");}
    void camera(String v){p.setProperty("camera",v);save();}
    String charSize(){return p.getProperty("charSize","NORMAL");}
    void charSize(String v){p.setProperty("charSize",v);save();}
    void reset(){p.clear();save();}
    boolean missionDone(int i){return p.getProperty("mission"+i,"false").equals("true");}
    void setMissionDone(int i){p.setProperty("mission"+i,"true");save();}
}

