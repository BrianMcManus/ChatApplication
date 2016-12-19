DROP DATABASE IF EXISTS ChatApp;

CREATE DATABASE IF NOT EXISTS ChatApp;
USE ChatApp;

CREATE TABLE ChatApp.User
(
	userId INT(5) NOT NULL AUTO_INCREMENT,
	username VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	PRIMARY KEY (userId)
);

CREATE TABLE ChatApp.Message
(
	messageId INT(5) NOT NULL AUTO_INCREMENT,
	message VARCHAR(10000) NOT NULL,
	sender VARCHAR(100) NOT NULL,
	recipient VARCHAR(100) NOT NULL,
	read BOOLEAN NOT NULL,
	timeSent DATE NOT NULL,
	inForum BOOLEAN NOT NULL,
	PRIMARY KEY (messageId)
);

CREATE TABLE ChatApp.UserMessage
(
	userId INT(5) NOT NULL,
	messageId INT(5) NOT NULL,
	CONSTRAINT fkUser FOREIGN KEY(userId) REFERENCES ChatApp.User(userId),
	CONSTRAINT fkMessage FOREIGN KEY(messageId) REFERENCES ChatApp.Message(messageId)
);

