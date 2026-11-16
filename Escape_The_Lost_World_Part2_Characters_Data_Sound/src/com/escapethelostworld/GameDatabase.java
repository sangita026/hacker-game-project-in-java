package com.escapethelostworld;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import java.util.List;


class GameDatabase {
    private final File dbFile = new File(System.getProperty("user.home"), "EscapeTheLostWorldDatabase.csv");
    private final File configFile = new File(System.getProperty("user.home"), "EscapeTheLostWorldDB.properties");
    private boolean mysqlAvailable;

    GameDatabase() { ensureFile(); mysqlAvailable = testMySqlDriver(); }

    private boolean testMySqlDriver() {
        try { Class.forName("com.mysql.cj.jdbc.Driver"); return true; }
        catch (Throwable ignored) { return false; }
    }

    private void ensureFile() {
        if (dbFile.exists()) return;
        try (PrintWriter out = new PrintWriter(new FileWriter(dbFile, true))) {
            out.println("timestamp,character,world,coins,score,distance,hits,result");
        } catch (IOException ignored) {}
    }

    synchronized void recordRun(String character, String world, int coins, int score,
                                double distance, int hits, String result) {
        // MySQL is used automatically when Connector/J is present and credentials are valid.
        // CSV remains a safe local fallback so the game still runs on a clean PC.
        if (mysqlAvailable && recordRunMySql(character, world, coins, score, distance, hits, result)) return;
        recordRunCsv(character, world, coins, score, distance, hits, result);
    }

    private boolean recordRunMySql(String character, String world, int coins, int score,
                                   double distance, int hits, String result) {
        String host="localhost", port="3306", database="escape_lost_world", user="root", password="";
        if(configFile.exists()){
            Properties cfg=new Properties();
            try(FileInputStream in=new FileInputStream(configFile)){cfg.load(in);
                host=cfg.getProperty("host",host); port=cfg.getProperty("port",port); database=cfg.getProperty("database",database);
                user=cfg.getProperty("user",user); password=cfg.getProperty("password",password);
            }catch(Exception ignored){}
        }
        String url="jdbc:mysql://"+host+":"+port+"/"+database+"?useSSL=false&serverTimezone=UTC";
        String sql="INSERT INTO game_sessions (player_id, character_id, world_id, coins, score, distance_m, hits, result) VALUES (1,?,?,?,?,?,?,?)";
        int characterId=Math.max(1,Math.min(10, characterId(character)));
        int worldId=Math.max(1,Math.min(6, worldId(world)));
        try(java.sql.Connection con=java.sql.DriverManager.getConnection(url,user,password);
            java.sql.PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1,characterId); ps.setInt(2,worldId); ps.setInt(3,coins); ps.setInt(4,score); ps.setDouble(5,distance); ps.setInt(6,hits); ps.setString(7,result); ps.executeUpdate();
            return true;
        }catch(Exception ignored){ return false; }
    }
    private int characterId(String name){
        for(int i=0;i<CharacterCatalog.DATA.length;i++) if(CharacterCatalog.DATA[i].name.equals(name)) return i+1;
        return 1;
    }
    private int worldId(String name){
        WorldType[] a=WorldType.values(); for(int i=0;i<a.length;i++) if(a[i].title.equals(name)) return i+1; return 1;
    }

    private void recordRunCsv(String character, String world, int coins, int score, double distance, int hits, String result) {
        ensureFile();
        try (PrintWriter out = new PrintWriter(new FileWriter(dbFile, true))) {
            out.printf(Locale.ROOT, "%s,%s,%s,%d,%d,%.2f,%d,%s%n",
                    new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                    csv(character), csv(world), coins, score, distance, hits, result);
        } catch (IOException ignored) {}
    }

    int sessionCount() {
        ensureFile(); int count = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(dbFile))) {
            String line; while ((line = in.readLine()) != null) if (!line.startsWith("timestamp,")) count++;
        } catch (IOException ignored) {}
        return count;
    }

    String path() { return dbFile.getAbsolutePath(); }
    private String csv(String value) { return value.replace(",", " ").replace("\n", " ").replace("\r", " "); }
}

