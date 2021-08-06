CREATE TABLE events(
    id BIGINT AUTO_INCREMENT,
    user_id BIGINT,
    file_id BIGINT,
    created DATETIME,
    PRIMARY KEY(id));