CREATE TABLE files (
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(30),
    path VARCHAR(256),
    created DATETIME,
    updated DATETIME,
    PRIMARY KEY(id));