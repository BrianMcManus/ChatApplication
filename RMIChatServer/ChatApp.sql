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

	reciever VARCHAR(100) NOT NULL,

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



INSERT INTO Users (username, email, password, loggedIn) VALUES ('brian', 'test@test.com', 'password', 0);
INSERT INTO Users (username, email, password, loggedIn) VALUES ('niall', 'test1@test.com', 'password', 0);
INSERT INTO Users (username, email, password, loggedIn) VALUES ('sergio', 'test2@test.com', 'password', 0);

INSERT INTO Message(message, reciever , messageRead, timeSent, inForum) VALUES ('Hello', 'brian', 0, '2016-02-02', 1);
INSERT INTO Message(message, reciever , messageRead, timeSent, inForum) VALUES ('Hi', 'sergio', 0, '2016-03-03', 0);
INSERT INTO Message(message, reciever , messageRead, timeSent, inForum) VALUES ('wats up?', 'niall', 0, '2016-04-04', 0);

INSERT INTO UserMessage(userId, messageId) VALUES (3,1);
INSERT INTO UserMessage(userId, messageId) VALUES (1,2);
INSERT INTO UserMessage(userId, messageId) VALUES (2,3);