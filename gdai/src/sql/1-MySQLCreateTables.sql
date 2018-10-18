-- ------------------ Drop Tables --------------------------------------
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS gdai_user;
DROP TABLE IF EXISTS gdai_group;
DROP TABLE IF EXISTS gdai_language;
DROP TABLE IF EXISTS gdai_system;
DROP TABLE IF EXISTS gdai_project;
DROP TABLE IF EXISTS gdai_application;
SET FOREIGN_KEY_CHECKS = 1;

-- ------------------------------ Language --------------------------------
CREATE TABLE gdai_language
  ( 
     languageId      BIGINT NOT NULL auto_increment,
     languageName    VARCHAR(10) NOT NULL,
     languageCountry VARCHAR(10) NOT NULL,
     options         TEXT NOT NULL,
     creationDate    TIMESTAMP, 
     CONSTRAINT LanguagePK PRIMARY KEY (languageId, languageName)
  ) 
engine = innodb; 

CREATE INDEX languageName ON gdai_language(languageName); 
------------------------------------------------------------------------

-- ------------------------------ Group --------------------------------
CREATE TABLE gdai_group
  ( 
     groupId        BIGINT NOT NULL auto_increment,
     groupName      VARCHAR(30) NOT NULL,
     creationDate   TIMESTAMP, 
     expirationDate TIMESTAMP,
     systemId       BIGINT,
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
     expirationDate    TIMESTAMP,
     groupId           BIGINT,
     CONSTRAINT UserPK PRIMARY KEY (userId), 
     CONSTRAINT UserUniqueKeyLoginName UNIQUE (loginName),
     FOREIGN KEY (groupId) REFERENCES gdai_group(groupId) ON DELETE CASCADE
  ) 
engine = innodb; 

CREATE INDEX UserIndexByLoginName ON gdai_user(loginName); 
------------------------------------------------------------------------

-- ------------------------------ System --------------------------------
CREATE TABLE gdai_system
  ( 
     systemId    	   BIGINT NOT NULL auto_increment,
     systemName        VARCHAR(255) NOT NULL,
     systemDescription VARCHAR(10000) NOT NULL,
     creationDate      TIMESTAMP NOT NULL,
     groupId           BIGINT,
     CONSTRAINT SystemPK PRIMARY KEY (systemId),
     CONSTRAINT SystemUniqueKeySystemName UNIQUE (systemName),
     FOREIGN KEY (groupId) REFERENCES gdai_group(groupId) ON DELETE CASCADE
  ) 
engine = innodb;

CREATE INDEX SystemIndexBySystemName ON gdai_system(systemName); 
------------------------------------------------------------------------


-- ------------------------------ Project --------------------------------
CREATE TABLE gdai_project
  ( 
     projectId    	    BIGINT NOT NULL auto_increment,
     projectName        VARCHAR(255) NOT NULL,
     projectDescription VARCHAR(10000) NOT NULL,
     creationDate       TIMESTAMP NOT NULL,
     createdById        BIGINT NOT NULL,
     targetDate         TIMESTAMP,
     systemId           BIGINT NOT NULL,
     CONSTRAINT ProjectPK PRIMARY KEY (projectId),
     CONSTRAINT ProjectUniqueKeyProjectName UNIQUE (projectName),
     FOREIGN KEY (createdById) REFERENCES gdai_user(userId),
     FOREIGN KEY (systemId) REFERENCES gdai_system(systemId)
  ) 
engine = innodb; 

CREATE INDEX ProjectIndexByProjectName ON gdai_project(projectName); 
------------------------------------------------------------------------

-- ------------------------------ Application --------------------------------
CREATE TABLE gdai_application
  ( 
     applicationId    	    BIGINT NOT NULL auto_increment,
     applicationName        VARCHAR(255) NOT NULL,
     applicationDescription VARCHAR(10000) NOT NULL,
     creationDate           TIMESTAMP NOT NULL,
     systemId               BIGINT NOT NULL,
     CONSTRAINT ApplicationPK PRIMARY KEY (applicationId),
     CONSTRAINT ApplicationUniqueKeyApplicationName UNIQUE (applicationName),
     FOREIGN KEY (systemId) REFERENCES gdai_system(systemId)
  ) 
engine = innodb; 

CREATE INDEX ApplicationIndexByApplicationName ON gdai_application(applicationName); 
------------------------------------------------------------------------
