# Escape: The Lost World V2

A Java Swing desktop adventure game. The original single Java source file has been separated into individual Java files while keeping all classes in the same package (`com.escapethelostworld`). The gameplay logic and feature behavior are intended to remain unchanged.

## Requirements
- JDK 17 or newer (JDK 21 is also suitable)
- Windows: run `Run_Game_V2.bat`, or compile from a terminal
- Optional MySQL: MySQL Server and MySQL Connector/J driver

## Run from source
Open this folder in IntelliJ IDEA as a Java project, mark `src` as the source root, and run `com.escapethelostworld.Main`.

Command line compile (from project root):

```bash
javac -d out src/com/escapethelostworld/*.java
java -cp out com.escapethelostworld.Main
```

For audio, copy the `assets` folder to the working directory used when launching the game. The game also supports local CSV fallback for recorded runs. MySQL configuration is optional; see `EscapeTheLostWorldDB.properties.example` and the SQL file.

## Two-person contribution split
See `COLLABORATION.md`. Both contributors work in the same repository and commit only their assigned files. Do not create two unrelated copies of the project.
