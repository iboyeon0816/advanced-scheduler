DROP DATABASE IF EXISTS scheduler2;
CREATE DATABASE scheduler2;

USE scheduler2;

CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(10) NOT NULL,
                      email VARCHAR(30) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      created_at TIMESTAMP,
                      updated_at TIMESTAMP
);

CREATE TABLE schedule (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id BIGINT NOT NULL,
                          title VARCHAR(30) NOT NULL,
                          contents VARCHAR(100),
                          created_at TIMESTAMP,
                          updated_at TIMESTAMP,
                          FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE comment (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         schedule_id BIGINT NOT NULL,
                         user_id BIGINT NOT NULL,
                         contents VARCHAR(100) NOT NULL,
                         created_at TIMESTAMP,
                         updated_at TIMESTAMP,
                         FOREIGN KEY(schedule_id) REFERENCES schedule(id) ON DELETE CASCADE,
                         FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE CASCADE
);