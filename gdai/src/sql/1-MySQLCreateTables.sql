-- ------------------ Drop Tables --------------------------------------
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS gdai_user;
DROP TABLE IF EXISTS gdai_group;
SET FOREIGN_KEY_CHECKS = 1;

-- N:N relationship between Group & User is still missing. Fixed in further updates.
-- ------------------------------ Group --------------------------------
CREATE TABLE gdai_group
  ( 
     groupId        BIGINT NOT NULL auto_increment,
     groupName      VARCHAR(30) NOT NULL,
     creationTime   TIMESTAMP, 
     expirationTime TIMESTAMP,
     CONSTRAINT GroupPK PRIMARY KEY (groupId),
     CONSTRAINT GroupUniqueKeyGroupName UNIQUE (groupName)
  ) 
engine = innodb; 

CREATE INDEX GroupIndexByGroupName ON gdai_group(groupName); 
------------------------------------------------------------------------

-- ------------------------------ User ---------------------------------
CREATE TABLE gdai_user
  ( 
     userId            BIGINT NOT NULL auto_increment, 
     loginName         VARCHAR(30) NOT NULL, 
     encryptedPassword VARCHAR(13) NOT NULL, 
     firstName         VARCHAR(30) NOT NULL, 
     lastName          VARCHAR(40) NOT NULL, 
     email             VARCHAR(60) NOT NULL, 
     phoneNumber       VARCHAR(20),
     avatarUrl         VARCHAR(500),
     gender            VARCHAR(20),
     hireDate          DATE, 
     dateOfBirth       DATE,
     expirationTime    TIMESTAMP,
     groupId           BIGINT,
     CONSTRAINT UserPK PRIMARY KEY (userId), 
     CONSTRAINT UserUniqueKeyLoginName UNIQUE (loginName),
     FOREIGN KEY (groupId) REFERENCES gdai_group(groupId)
  ) 
engine = innodb; 

CREATE INDEX UserIndexByLoginName ON gdai_user(loginName); 
------------------------------------------------------------------------