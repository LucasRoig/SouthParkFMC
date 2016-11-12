-- Script creation de la base

CREATE TABLE SEASON (
seasonId INTEGER PRIMARY KEY,
diffusionYear INTEGER NOT NULL
);

CREATE TABLE EPISODE(
episodeId SERIAL PRIMARY KEY,
productionCode INTEGER NOT NULL,
seasonId INTEGER NOT NULL,
nameVO varchar(64) NOT NULL,
nameVF varchar(64),
plot text,
indexInSeason INTEGER NOT NULL,
FOREIGN KEY (seasonId) REFERENCES SEASON(seasonId) ON DELETE CASCADE ON UPDATE CASCADE,
UNIQUE (seasonId,indexInSeason),
UNIQUE (nameVO),
UNIQUE (nameVF)
);

CREATE TABLE TAG(
tagId SERIAL PRIMARY KEY,
tagName varchar(64) NOT NULL UNIQUE
);

CREATE TABLE ROLE(
roleId SERIAL PRIMARY KEY,
roleName TEXT NOT NULL UNIQUE
);

CREATE TABLE CHARACTERS(
characterId SERIAL PRIMARY KEY,
characterName varchar(64) NOT NULL,
background TEXT
);


CREATE TABLE APPARITION(
characterId INTEGER,
episodeId INTEGER,
roleId INTEGER,
apparitionNote TEXT,
PRIMARY KEY (characterId,episodeId,roleId),
FOREIGN KEY (characterId) REFERENCES CHARACTERS(characterId) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (episodeId) REFERENCES EPISODE(episodeId) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (roleId) REFERENCES ROLE(roleId) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE TAGGED(
tagId INTEGER,
episodeId INTEGER,
taggedNote TEXT,
PRIMARY KEY (tagId,episodeId),
FOREIGN KEY (episodeId) REFERENCES EPISODE(episodeId) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (tagId) REFERENCES TAG(tagId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE QUOTE(
quoteId SERIAL,
episodeId INTEGER,
characterId INTEGER,
quoteText TEXT NOT NULL,
quoteNote TEXT,
PRIMARY KEY (quoteId,episodeId),
FOREIGN KEY (episodeId) REFERENCES EPISODE(episodeId) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (characterId) REFERENCES CHARACTERS(characterId)
);

CREATE TABLE PRIVILEGE(
privilege varchar(16) PRIMARY KEY
);

CREATE TABLE USERS(
userLogin varchar(32) PRIMARY KEY,
userPassword text NOT NULL,
privilege varchar(16) NOT NULL,
FOREIGN KEY (privilege) REFERENCES PRIVILEGE(privilege)
); 

