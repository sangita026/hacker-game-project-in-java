package com.escapethelostworld;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import java.util.List;


class CharacterCatalog {
    static final CharacterData[] DATA = {
        new CharacterData("Aarav","Trail Hoodie",new Color(65,42,28),new Color(230,82,92),new Color(52,76,105),new Color(245,245,245),new Color(224,171,132),0),
        new CharacterData("Ryan","Green Runner",new Color(50,36,27),new Color(75,166,113),new Color(63,73,77),new Color(240,230,211),new Color(194,143,104),0),
        new CharacterData("Kabir","Sunny Street",new Color(32,28,26),new Color(246,190,67),new Color(47,94,145),new Color(245,245,245),new Color(213,160,118),800),
        new CharacterData("Leo","Orange Explorer",new Color(80,48,31),new Color(238,126,64),new Color(111,78,53),new Color(52,52,52),new Color(224,172,131),1200),
        new CharacterData("Arjun","Blue Tracksuit",new Color(39,29,26),new Color(80,126,218),new Color(42,65,112),new Color(247,247,247),new Color(185,132,94),1500),
        new CharacterData("Mia","Pink Sport",new Color(82,48,34),new Color(240,125,168),new Color(64,74,106),new Color(247,231,221),new Color(230,183,149),0),
        new CharacterData("Zara","Teal Runner",new Color(31,28,26),new Color(65,179,171),new Color(48,53,68),new Color(244,235,222),new Color(203,151,113),900),
        new CharacterData("Anaya","Yellow Hoodie",new Color(52,31,23),new Color(244,195,70),new Color(78,87,120),new Color(235,235,240),new Color(225,177,137),1300),
        new CharacterData("Sofia","Lavender Casual",new Color(88,54,42),new Color(164,126,220),new Color(72,68,97),new Color(242,235,225),new Color(217,167,133),1700),
        new CharacterData("Kiara","Red Sprint",new Color(38,27,24),new Color(222,73,85),new Color(48,48,55),new Color(245,245,245),new Color(192,139,103),2000)
    };
}

