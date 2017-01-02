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

	receiver VARCHAR(100) NOT NULL,

	messageRead BOOLEAN NOT NULL,

	timeSent TIMESTAMP NOT NULL,

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

INSERT INTO Message(message, receiver , messageRead, timeSent, inForum) VALUES ('Hello', 'forum', 0, '2016-08-05 18:19:03', 1);
INSERT INTO Message(message, receiver , messageRead, timeSent, inForum) VALUES ('Hi', 'sergio', 0, '2016-08-05 18:20:03', 0);
INSERT INTO Message(message, receiver , messageRead, timeSent, inForum) VALUES ('wats up?', 'niall', 0, '2016-08-05 18:21:03', 0);
INSERT INTO Message(message, receiver , messageRead, timeSent, inForum) VALUES ('Hey guys', 'forum', 0, '2016-08-05 18:30:03', 1);
INSERT INTO Message(message, receiver , messageRead, timeSent, inForum) VALUES ('Hi there', 'brian', 0, '2016-08-05 18:43:03', 0);
INSERT INTO Message(message, receiver , messageRead, timeSent, inForum) VALUES ('hows everyone?', 'niall', 0, '2016-08-05 18:55:03', 0);

INSERT INTO UserMessage(userId, messageId) VALUES (3,1);
INSERT INTO UserMessage(userId, messageId) VALUES (2,2);
INSERT INTO UserMessage(userId, messageId) VALUES (1,3);
INSERT INTO UserMessage(userId, messageId) VALUES (3,4);
INSERT INTO UserMessage(userId, messageId) VALUES (2,5);
INSERT INTO UserMessage(userId, messageId) VALUES (1,6);