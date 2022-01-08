CREATE TABLE categories (
    id BIGINT NOT NULL AUTO_INCREMENT,
    tournament_category VARCHAR(50) NOT NULL,
    prize_money INT NOT NULL,
    tax INT NOT NULL,

    PRIMARY KEY (id)
);


CREATE TABLE tournaments (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    level_accepted VARCHAR(50) NOT NULL,
    start_date datetime,
    end_date datetime,
    sign_in_start_date datetime,
    sign_in_end_date datetime,
    number_of_players INT NOT NULL,
    tournament_type VARCHAR(50) NOT NULL,
    category_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);
