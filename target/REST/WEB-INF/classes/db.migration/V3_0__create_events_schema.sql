CREATE TABLE events(
    id BIGINT AUTO_INCREMENT,
    user_id BIGINT,
    file_id BIGINT,
    created DATETIME,
    event_type VARCHAR(30),
    PRIMARY KEY(id));