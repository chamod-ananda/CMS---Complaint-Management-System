CREATE database cmsDb;
USE cmsDb;
CREATE TABLE users (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(50) UNIQUE,
                       password VARCHAR(100),
                       role ENUM('EMPLOYEE', 'ADMIN')
);

CREATE TABLE complaints (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            title VARCHAR(100),
                            description TEXT,
                            status ENUM('PENDING', 'RESOLVED') DEFAULT 'PENDING',
                            remarks TEXT,
                            user_id INT,
                            FOREIGN KEY (user_id) REFERENCES users(id)
);
