-- ------------------ Drop Tables --------------------------------------
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS gdai_user;
DROP TABLE IF EXISTS gdai_group;
SET FOREIGN_KEY_CHECKS = 1;

-- N:N relationship between Group & User is still missing. Fixed in further updates.
-- ------------------------------ Group --------------------------------
CREATE TABLE gdai_group
  ( 
     id         BIGINT NOT NULL auto_increment, 
     name           VARCHAR(30) NOT NULL,
     creationtime   TIMESTAMP, 
     expirationtime TIMESTAMP, 
     CONSTRAINT group_pk PRIMARY KEY (id, name) 
  ) 
engine = innodb; 

CREATE INDEX groupindexbyname ON gdai_group (name); 
------------------------------------------------------------------------

-- ------------------------------ User ---------------------------------
CREATE TABLE gdai_user
  ( 
     id                BIGINT NOT NULL auto_increment, 
     loginname         VARCHAR(30) NOT NULL, 
     encryptedpassword VARCHAR(13) NOT NULL, 
     firstname         VARCHAR(30) NOT NULL, 
     lastname          VARCHAR(40) NOT NULL, 
     email             VARCHAR(60) NOT NULL, 
     ext               VARCHAR(20), 
     creationtime      TIMESTAMP, 
     expirationtime    TIMESTAMP, 
     CONSTRAINT user_pk PRIMARY KEY (id), 
     CONSTRAINT userloginnameuniquekey UNIQUE (loginname) 
  ) 
engine = innodb; 

CREATE INDEX userindexbyloginname ON gdai_user (loginname); 
------------------------------------------------------------------------