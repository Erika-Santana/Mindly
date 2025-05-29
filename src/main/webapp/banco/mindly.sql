CREATE DATABASE IF NOT EXISTS book_my_mind;
USE book_my_mind;
DROP DATABASE book_my_mind;


SELECT * FROM professional_user;
SELECT * FROM address;
SELECT * FROM user_specialty_approuch;
SELECT * FROM specialty_professional;
SELECT * FROM approuch_professional;
SELECT * FROM set_hours_professional;
SELECT * FROM user_specialty_approuch;
SELECT * FROM days_of_week;

CREATE TABLE address(
    ID INTEGER AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    number_ INTEGER NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL
);

CREATE TABLE client_user(
    ID int AUTO_INCREMENT,
    client_name VARCHAR(255) NOT NULL,
    cpf VARCHAR(30) NOT NULL UNIQUE,
    contact VARCHAR(100) NOT NULL,
    password_ VARCHAR(100) NOT NULL,
    profile_image VARCHAR(255),
    login VARCHAR(100) NOT NULL,
    ID_address INTEGER NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (ID_address) REFERENCES address(ID)
);

CREATE TABLE professional_user(
    ID INTEGER AUTO_INCREMENT,
    professional_name VARCHAR(255) NOT NULL,
    fantasy_name VARCHAR(255) NOT NULL,
    ID_address INTEGER NOT NULL,
    about_me TEXT NOT NULL,
	about_my_job TEXT NOT NULL,
    profile_image VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255),
    date_creation DATETIME DEFAULT CURRENT_TIMESTAMP,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    password_ VARCHAR(100) NOT NULL,
    login VARCHAR(100) NOT NULL, 
   
    PRIMARY KEY (ID),
    FOREIGN KEY (ID_address) REFERENCES address(ID)
);
CREATE TABLE specialty_professional(
    ID INT AUTO_INCREMENT,
    specialty VARCHAR(255),
    PRIMARY KEY (ID)
);

SELECT * FROM approuch_professional;

CREATE TABLE approuch_professional(
    ID INT AUTO_INCREMENT,
    approuch VARCHAR(255) NOT NULL,
    PRIMARY KEY(ID)
);

CREATE TABLE user_specialty_approuch(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ID_professional INT NOT NULL,
    ID_specialist INT NOT NULL,
    ID_approuch INT NOT NULL,
    FOREIGN KEY (ID_professional) REFERENCES professional_user(ID),
    FOREIGN KEY (ID_specialist) REFERENCES specialty_professional(ID),
    FOREIGN KEY (ID_approuch) REFERENCES approuch_professional(ID)
);



CREATE TABLE set_hours_professional(
    ID INTEGER AUTO_INCREMENT PRIMARY KEY,
    ID_professional_approuch INTEGER NOT NULL,
    start_hour TIME NOT NULL,
    end_hour TIME NOT NULL,
    appoitment_duration TIME NOT NULL, 
    FOREIGN KEY (ID_professional_approuch) REFERENCES user_specialty_approuch(ID),
    CONSTRAINT time_order CHECK (start_hour < end_hour)
);

CREATE TABLE days_of_week(
    ID INTEGER AUTO_INCREMENT PRIMARY KEY,
    ID_hours_professional INTEGER NOT NULL, 
    day_of_week ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'),
    FOREIGN KEY (ID_hours_professional) REFERENCES set_hours_professional(ID)
);


CREATE TABLE imagemWork(
	ID INTEGER AUTO_INCREMENT PRIMARY KEY,
    ID_professional INT NOT NULL,
    imagem VARCHAR(255) NOT NULL,
    
    FOREIGN KEY (ID_professional) REFERENCES professional_user(ID)
    );
    

    
