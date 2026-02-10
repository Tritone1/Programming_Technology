USE yogibear_game;


CREATE TABLE highscores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(100) NOT NULL,
    score INT NOT NULL,
    level_reached INT NOT NULL,
    time_seconds BIGINT NOT NULL,
    date_played TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_score (score DESC)
);


SELECT * FROM highscores;