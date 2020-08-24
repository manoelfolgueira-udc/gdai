-- ------------------ GLOBALS --------------------------------------
SET GLOBAL event_scheduler = ON;


-- ------------------ Drop Tables --------------------------------------
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS gdai_user;
DROP TABLE IF EXISTS gdai_group;
DROP TABLE IF EXISTS gdai_language;
DROP TABLE IF EXISTS gdai_system;
DROP TABLE IF EXISTS gdai_application;
DROP TABLE IF EXISTS gdai_userstory;
DROP TABLE IF EXISTS gdai_project;
DROP TABLE IF EXISTS gdai_sprint;
DROP TABLE IF EXISTS gdai_project_sprint_jt;
DROP TABLE IF EXISTS gdai_production_pass;
DROP TABLE IF EXISTS gdai_gdai_case;
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
     groupId          BIGINT NOT NULL auto_increment,
     groupName        VARCHAR(64) NOT NULL,
     groupDescription VARCHAR(1000),
     creationDate     TIMESTAMP,
     expirationDate   TIMESTAMP,
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
     isManager         boolean,
     groupId           BIGINT NOT NULL,
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
     expirationDate    TIMESTAMP NOT NULL,
     groupId           BIGINT,
     CONSTRAINT SystemPK PRIMARY KEY (systemId),
     CONSTRAINT SystemUniqueKeySystemName UNIQUE (systemName),
     FOREIGN KEY (groupId) REFERENCES gdai_group(groupId) ON DELETE CASCADE
  ) 
engine = innodb;

CREATE INDEX SystemIndexBySystemName ON gdai_system(systemName); 
------------------------------------------------------------------------

-- ---------------------------- User Story -----------------------------
CREATE TABLE gdai_userstory
  ( 
     userStoryId  	      BIGINT NOT NULL auto_increment,
     userStoryName		  VARCHAR(255) NOT NULL,
     userStoryDescription VARCHAR(3000) NOT NULL,
     creationDate         TIMESTAMP NOT NULL,
     createdById          BIGINT NOT NULL,
     CONSTRAINT UserStoryPK PRIMARY KEY (userStoryId),
     CONSTRAINT UserStoryUniqueKeyUserStoryName UNIQUE (userStoryName),
     FOREIGN KEY (createdById) REFERENCES gdai_user(userId)
  ) 
engine = innodb; 

CREATE INDEX UserStoryIndexByUserStoryName ON gdai_userstory(userStoryName); 
------------------------------------------------------------------------

-- ------------------------------ Sprint -------------------------------
CREATE TABLE gdai_sprint
  ( 
     sprintId    	    BIGINT NOT NULL auto_increment,
     sprintName			VARCHAR(255) NOT NULL,
     sprintStart        TIMESTAMP NOT NULL,
     sprintEnd    		TIMESTAMP NOT NULL,
     creationDate       TIMESTAMP NOT NULL,
     CONSTRAINT SprintPK PRIMARY KEY (sprintId),
     CONSTRAINT SprintUniqueKeySprintName UNIQUE (sprintName)
  ) 
engine = innodb; 

CREATE INDEX SprintIndexBySprintName ON gdai_sprint(sprintName);
------------------------------------------------------------------------

-- ------------------------------ Project --------------------------------
CREATE TABLE gdai_project
  ( 
     projectId    	    BIGINT NOT NULL auto_increment,
     projectName        VARCHAR(255) NOT NULL,
     projectDescription VARCHAR(10000) NOT NULL,
     creationDate       TIMESTAMP NOT NULL,
     createdById        BIGINT NOT NULL,
     requirementsPath   VARCHAR(500),
     systemId           BIGINT NOT NULL,
     userStoryId		BIGINT NOT NULL,
     CONSTRAINT ProjectPK PRIMARY KEY (projectId),
     CONSTRAINT ProjectUniqueKeyProjectName UNIQUE (projectName),
     FOREIGN KEY (createdById) REFERENCES gdai_user(userId),
     FOREIGN KEY (systemId) REFERENCES gdai_system(systemId),
     FOREIGN KEY (userStoryId) REFERENCES gdai_userstory(userStoryId)
  ) 
engine = innodb; 

CREATE INDEX ProjectIndexByProjectName ON gdai_project(projectName); 
------------------------------------------------------------------------

-- ------------------------------ project_sprint_jt --------------------------------
CREATE TABLE gdai_project_sprint_jt
  ( 
     projectId    	    BIGINT NOT NULL,
     sprintId    	    BIGINT NOT NULL,
     CONSTRAINT ProjectSprintJTPK PRIMARY KEY (projectId, sprintId),
     FOREIGN KEY (projectId) REFERENCES gdai_project(projectId),
     FOREIGN KEY (sprintId) REFERENCES gdai_sprint(sprintId)
  ) 
engine = innodb; 

CREATE INDEX ProjectSprintJTIND ON gdai_project_sprint_jt(projectId, sprintId); 
------------------------------------------------------------------------

-- ------------------------------ Application --------------------------------
CREATE TABLE gdai_application
  ( 
     applicationId    	    BIGINT NOT NULL auto_increment,
     applicationName        VARCHAR(255) NOT NULL,
     applicationDescription VARCHAR(10000) NOT NULL,
     creationDate           TIMESTAMP NOT NULL,
     expirationDate         TIMESTAMP NOT NULL, 
     systemId               BIGINT NOT NULL,
     CONSTRAINT ApplicationPK PRIMARY KEY (applicationId),
     CONSTRAINT ApplicationUniqueKeyApplicationName UNIQUE (applicationName),
     FOREIGN KEY (systemId) REFERENCES gdai_system(systemId)
  ) 
engine = innodb; 

CREATE INDEX ApplicationIndexByApplicationName ON gdai_application(applicationName); 
------------------------------------------------------------------------

-- ------------------------------ Case --------------------------------
CREATE TABLE gdai_gdai_case
  ( 
     gdaiCaseId    	     BIGINT NOT NULL auto_increment,
     gdaiCaseDescription VARCHAR(10000) NOT NULL,
     gdaiCaseResolution  VARCHAR(10000) NOT NULL,
     creationDate    TIMESTAMP NOT NULL,
     createdById     BIGINT NOT NULL,
     systemId        BIGINT NOT NULL,
     CONSTRAINT CasePK PRIMARY KEY (gdaiCaseId),
     FOREIGN KEY (createdById) REFERENCES gdai_user(userId),
     FOREIGN KEY (systemId) REFERENCES gdai_system(systemId)
  ) 
engine = innodb; 

CREATE INDEX CaseIndexByCaseDesc ON gdai_gdai_case(gdaiCaseId);
------------------------------------------------------------------------

-- ------------------------------ ProductionPass --------------------------------
CREATE TABLE gdai_production_pass
  ( 
     productionPassId    	   BIGINT NOT NULL auto_increment,
     productionPassName        VARCHAR(255) NOT NULL,
     productionPassResolution VARCHAR(10000),
     creationDate       	   TIMESTAMP NOT NULL,
     passPath		           VARCHAR(500),
     createdById        	   BIGINT NOT NULL,
     systemId                  BIGINT NOT NULL,
     projectId  		       BIGINT,
     gdaiCaseId				   BIGINT,
     CONSTRAINT ProductionPassPK PRIMARY KEY (productionPassId),
     CONSTRAINT ProductionPassUniqueKeyProductionPassName UNIQUE (productionPassName),
     FOREIGN KEY (createdById) REFERENCES gdai_user(userId),
     FOREIGN KEY (systemId) REFERENCES gdai_system(systemId),
     FOREIGN KEY (projectId) REFERENCES gdai_project(projectId),
     FOREIGN KEY (gdaiCaseId) REFERENCES gdai_gdai_case(gdaiCaseId)
  ) 
engine = innodb; 

CREATE INDEX ProductionPassIndexByProductionPassId ON gdai_production_pass(productionPassId); 
------------------------------------------------------------------------