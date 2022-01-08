CREATE TABLE players (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100),
    level VARCHAR(50) NOT NULL,
    pocket_budget INT NOT NULL,
    points INT NOT NULL,

    PRIMARY KEY (id)
);
