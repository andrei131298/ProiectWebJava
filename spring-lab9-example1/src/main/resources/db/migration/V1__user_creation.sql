CREATE TABLE institution (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    type VARCHAR(50),

    PRIMARY KEY (id)
);

CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    full_name VARCHAR(50),
    user_type VARCHAR(50),
    account_created datetime,
    institution_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (institution_id) REFERENCES institution(id)
);

CREATE TABLE user_details (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cnp VARCHAR(50) NOT NULL,
    age INT,
    other_information VARCHAR(255),

    PRIMARY KEY (id)
);

CREATE TABLE users_user_details (
    id BIGINT NOT NULL AUTO_INCREMENT,
    users BIGINT NOT NULL,
    user_details BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (users) REFERENCES users(id),
    FOREIGN KEY (user_details) REFERENCES user_details(id)
);
