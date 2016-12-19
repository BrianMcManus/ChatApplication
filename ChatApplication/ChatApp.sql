DROP DATABASE IF EXISTS ChatApp;

CREATE DATABASE IF NOT EXISTS ChatApp;
USE ChatApp;

CREATE TABLE ChatApp.Users
(
	userId INT(5) NOT NULL AUTO_INCREMENT,
	username VARCHAR(100) NOT NULL,
        email VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
        loggedIn BOOLEAN NOT NULL Default false,
	PRIMARY KEY (userId)
);

CREATE TABLE ChatApp.Message
(
	messageId INT(5) NOT NULL AUTO_INCREMENT,
	message VARCHAR(10000) NOT NULL,
	sender VARCHAR(100) NOT NULL,
	messageRead BOOLEAN NOT NULL,
	timeSent DATE NOT NULL,
	inForum BOOLEAN NOT NULL,
	PRIMARY KEY (messageId)
);
CREATE TABLE ChatApp.UserMessage
(
	userId INT(5) NOT NULL,
	messageId INT(5) NOT NULL,
	CONSTRAINT fkUser FOREIGN KEY(userId) REFERENCES ChatApp.Users(userId),
	CONSTRAINT fkMessage FOREIGN KEY(messageId) REFERENCES ChatApp.Message(messageId)
);

