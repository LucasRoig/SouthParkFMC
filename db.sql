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

-- Vue Index :
CREATE MATERIALIZED VIEW episode_search_index AS
select episode.episodeid,episode.namevf, episode.namevo, setweight(to_tsvector('french',nameVF),'A') 
						  || setweight(to_tsvector('french', plot),'B') 
                          || setweight(to_tsvector('french',coalesce(string_agg(tagname, ' '))),'A')
                          || setweight(to_tsvector('french',coalesce(string_agg(taggednote, ' '))),'C')
                          || setweight(to_tsvector('french',coalesce(string_agg(apparitionnote, ' '))),'C')as document From 
	episode left join (select * from tagged join tag on tagged.tagid=tag.tagid) as tag on tag.episodeid=episode.episodeid 
    left join apparition on apparition.episodeId=episode.episodeId group by episode.episodeid
	
CREATE INDEX idx_fts_search_episode ON episode_search_index USING gin(document);

--Pour actualiser la vue :
REFRESH MATERIALIZED VIEW episode_search_index;
REINDEX INDEX idx_fts_search_episode;

--Trigger
create or replace function update_episode() returns trigger
	AS $$ 
    	begin
    	REFRESH MATERIALIZED VIEW episode_search_index;
        REINDEX INDEX idx_fts_search_episode;
        RETURN NEW;
        end
    $$
    language PLPGSQL;
	
create trigger update_episode_index AFTER INSERT OR UPDATE OR DELETE ON EPISODE
EXECUTE PROCEDURE update_episode();
create trigger update_episode_index AFTER INSERT OR UPDATE OR DELETE ON Tagged
EXECUTE PROCEDURE update_episode();
create trigger update_episode_index AFTER INSERT OR UPDATE OR DELETE ON apparition
EXECUTE PROCEDURE update_episode();
create trigger update_episode_index AFTER INSERT OR UPDATE OR DELETE ON quote
EXECUTE PROCEDURE update_episode();