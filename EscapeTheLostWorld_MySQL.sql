CREATE DATABASE IF NOT EXISTS escape_lost_world;
USE escape_lost_world;

CREATE TABLE IF NOT EXISTS players (
    player_id INT AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS characters (
    character_id INT PRIMARY KEY,
    character_name VARCHAR(100) NOT NULL,
    gender ENUM('Boy','Girl') NOT NULL
);

CREATE TABLE IF NOT EXISTS worlds (
    world_id INT PRIMARY KEY,
    world_name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS game_sessions (
    session_id INT AUTO_INCREMENT PRIMARY KEY,
    player_id INT NOT NULL,
    character_id INT NOT NULL,
    world_id INT NOT NULL,
    coins INT NOT NULL DEFAULT 0,
    score INT NOT NULL DEFAULT 0,
    distance_m DECIMAL(10,2) NOT NULL DEFAULT 0,
    hits INT NOT NULL DEFAULT 0,
    result ENUM('GAME_OVER','VICTORY') NOT NULL,
    played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (player_id) REFERENCES players(player_id),
    FOREIGN KEY (character_id) REFERENCES characters(character_id),
    FOREIGN KEY (world_id) REFERENCES worlds(world_id)
);

INSERT INTO characters (character_id, character_name, gender) VALUES
(1,'Boy 1','Boy'),(2,'Boy 2','Boy'),(3,'Boy 3','Boy'),(4,'Boy 4','Boy'),(5,'Boy 5','Boy'),
(6,'Girl 1','Girl'),(7,'Girl 2','Girl'),(8,'Girl 3','Girl'),(9,'Girl 4','Girl'),(10,'Girl 5','Girl')
ON DUPLICATE KEY UPDATE character_name=VALUES(character_name), gender=VALUES(gender);

INSERT INTO worlds (world_id, world_name) VALUES
(1,'Lost Jungle'),(2,'Desert Ruins'),(3,'Crystal City'),
(4,'Frost Peaks'),(5,'Volcano Run'),(6,'Sky Kingdom')
ON DUPLICATE KEY UPDATE world_name=VALUES(world_name);

INSERT INTO players (player_name) SELECT 'Player 1' WHERE NOT EXISTS
(SELECT 1 FROM players WHERE player_name='Player 1');

-- Useful report queries
SELECT * FROM game_sessions ORDER BY played_at DESC;
SELECT player_id, player_name FROM players;
SELECT character_id, character_name, gender FROM characters;
SELECT world_id, world_name FROM worlds;
SELECT player_id, MAX(score) AS best_score FROM game_sessions GROUP BY player_id;
