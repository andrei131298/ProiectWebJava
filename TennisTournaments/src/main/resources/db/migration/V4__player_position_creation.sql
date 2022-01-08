CREATE TABLE player_positions (
    id BIGINT NOT NULL AUTO_INCREMENT,
    tournament_id BIGINT NOT NULL,
    player_id BIGINT NOT NULL,
    position INT NOT NULL,
    has_lost BOOLEAN NOT NULL,
    current_phase VARCHAR(50) NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (player_id) REFERENCES players(id),
    FOREIGN KEY (tournament_id) REFERENCES tournaments(id)

);