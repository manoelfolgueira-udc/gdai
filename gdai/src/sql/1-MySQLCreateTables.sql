-- ---------- Table for validation queries from the connection pool. ---
DROP TABLE PingTable;
CREATE TABLE PingTable (foo CHAR(1));
------------------------------------------------------------------------

-- ------------------ Drop Tables --------------------------------------
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Group;
SET FOREIGN_KEY_CHECKS = 1;

-- N:N relationship between Group & User is still missing. Fixed in further updates.
-- ------------------------------ Group --------------------------------
CREATE TABLE Group (
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(30) COLLATE latin1_bin NOT NULL,
    creationTime TIMESTAMP,
    expirationTime TIMESTAMP,
    CONSTRAINT Group_PK PRIMARY KEY (name)
)
ENGINE = InnoDB;
CREATE INDEX GroupIndexByName ON Group (name);
------------------------------------------------------------------------

-- ------------------------------ User ---------------------------------
CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    loginName VARCHAR(30) COLLATE latin1_bin NOT NULL,
    encryptedPassword VARCHAR(13) NOT NULL, 
    firstName VARCHAR(30) NOT NULL,
    lastName VARCHAR(40) NOT NULL,
    email VARCHAR(60) NOT NULL,
    ext VARCHAR(20),
    creationTime TIMESTAMP,
    expirationTime TIMESTAMP,
    CONSTRAINT User_PK PRIMARY KEY (id),
    CONSTRAINT userLoginNameUniqueKey UNIQUE (loginName)
) 
ENGINE = InnoDB;
CREATE INDEX UserIndexByLoginName ON User (loginName);
------------------------------------------------------------------------