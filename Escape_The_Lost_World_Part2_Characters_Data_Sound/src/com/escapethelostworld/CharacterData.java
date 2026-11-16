package com.escapethelostworld;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import java.util.List;


class CharacterData {
    final String name, outfit; final Color hair, shirt, pants, shoes, skin; final int price;
    CharacterData(String n,String o,Color h,Color sh,Color p,Color so,Color sk,int pr){name=n;outfit=o;hair=h;shirt=sh;pants=p;shoes=so;skin=sk;price=pr;}
}

