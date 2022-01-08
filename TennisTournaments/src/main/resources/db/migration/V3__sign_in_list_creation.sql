CREATE TABLE signed_in_lists (
    id BIGINT NOT NULL AUTO_INCREMENT,
    tournament_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (tournament_id) REFERENCES tournaments(id)
);

CREATE TABLE signed_in_players (
    id BIGINT NOT NULL AUTO_INCREMENT,
    player_id BIGINT NOT NULL,
    signed_in_list_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (player_id) REFERENCES players(id),
    FOREIGN KEY (signed_in_list_id) REFERENCES signed_in_lists(id)

);