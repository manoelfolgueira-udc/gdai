-- AvailableLanguages
insert into gdai_language(languageName, languageCountry, options, creationDate)
values
	('en', 'EN', 'en=English, gl=Galician, es=Spanish', NOW()),
	('es', 'ES', 'es=Español, gl=Gallego, en=Inglés', NOW()),
	('gl', 'ES', 'es=Español, gl=Galego, en=Inglés', NOW());

-- Groups
insert into gdai_group(groupName, creationDate, expirationDate)
values
	('GDAI_ADM', NOW(), NULL),
	('GP1', 	 NOW(), NULL),
	('GP2', 	 NOW(), NULL),
	('GP3', 	 NOW(), NULL),
	('GP4', 	 NOW(), NULL),
	('GP5', 	 NOW(), NULL),
	('GP6', 	 NOW(), NULL),
	('GP7', 	 NOW(), NULL),
	('GP8', 	 NOW(), NULL),
	('GP9', 	 NOW(), NULL),
	('GP10', 	 NOW(), NULL);
    
-- Users
insert into gdai_user(loginName, encryptedPassword, firstName, lastName, email, phoneNumber, avatarUrl, gender, hireDate, dateOfBirth, expirationDate, groupId)
values
	('Manoel', 'AZ0OZU/oardxM', 'Manoel', 'Folgueira', 'manoel.folgueira@gdai.com', '1456',
		'https://vignette.wikia.nocookie.net/clubpenguin/images/e/e2/Smiley-300x300.png/revision/latest?cb=20090401005140',
		'M', NOW(), NOW(), '2020-08-05 18:19:03', 1),
	('a1', 'AZ0OZU/oardxM', 'b', 'b', 'b@b.com',
		'1456', 'https://vignette.wikia.nocookie.net/clubpenguin/images/e/e2/Smiley-300x300.png/revision/latest?cb=20090401005140',
		'M', NOW(), NOW(), '2020-08-05 18:19:03', 2),
	('a2', 'AZ0OZU/oardxM', 'b', 'b', 'b@b.com',
		'1456', 'https://vignette.wikia.nocookie.net/clubpenguin/images/e/e2/Smiley-300x300.png/revision/latest?cb=20090401005140',
		'M', NOW(), NOW(), NOW(), 2),
	('a3', 'AZ0OZU/oardxM', 'b', 'b', 'b@b.com',
		'1456', 'https://vignette.wikia.nocookie.net/clubpenguin/images/e/e2/Smiley-300x300.png/revision/latest?cb=20090401005140',
		'M', NOW(), NOW(), NOW(), 2),
	('a4', 'AZ0OZU/oardxM', 'b', 'b', 'b@b.com',
		'1456', 'https://vignette.wikia.nocookie.net/clubpenguin/images/e/e2/Smiley-300x300.png/revision/latest?cb=20090401005140',
		'M', NOW(), NOW(), NOW(), 2),
	('a5', 'AZ0OZU/oardxM', 'b', 'b', 'b@b.com',
		'1456', 'https://vignette.wikia.nocookie.net/clubpenguin/images/e/e2/Smiley-300x300.png/revision/latest?cb=20090401005140',
		'M', NOW(), NOW(), NOW(), 2);
		
-- Systems
insert into gdai_system(systemName, systemDescription, creationDate, groupId)
values
	('Billing', 'Description here.', NOW(), 1);
	
-- User Stories
insert into gdai_userstory(userStoryName, userStoryDescription, creationDate, createdById)
values
	('usN', 'usD', NOW(), 1);
	
-- Sprints
insert into gdai_sprint(sprintName, sprintStart, sprintEnd, creationDate, createdById)
values
	('SP1', NOW(), NOW(), NOW(), 1),
	('SP2', NOW(), NOW(), NOW(), 1);
	
-- Projects
insert into gdai_project(projectName, projectDescription, creationDate, targetDate,
	createdById, systemId, sprintId, userStoryId)
values
	('Billing Entities', 'Project Desc.', NOW(), null, 2, 1, 1, 1);
	
-- project_sprint_jt
insert into gdai_project_sprint_jt(projectId, sprintId)
values
	(1, 1);

-- Applications
insert into gdai_application(applicationName, applicationDescription, creationDate, systemId)
values
	('GDAIBillingEntities', 'GDAIBillingEntities Desc.', NOW(), 1);
