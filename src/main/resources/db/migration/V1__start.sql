USE lab;

DROP TABLE IF EXISTS addressbook;

CREATE TABLE addressbook (
	id INT NOT NULL,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50),
	cellphone VARCHAR(15) NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO 
	addressbook 
VALUES 
	("1", "user_01", "user_01@email.com", "+551190001101"),
	("2", "user_02", "user_02@email.com", "+551190001102"),
	("3", "user_03", "user_03@email.com", "+551190001103"),
	("4", "user_04", "user_04@email.com", "+551190001104"),
	("5", "user_05", "user_05@email.com", "+551190001105");