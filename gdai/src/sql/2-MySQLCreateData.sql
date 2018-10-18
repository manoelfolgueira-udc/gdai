-- AvailableLanguages
insert into gdai_language(languageName, languageCountry, options, creationDate)
values
	('en', 'EN', 'en=English, gl=Galician, es=Spanish', NOW()),
	('es', 'ES', 'es=Español, gl=Gallego, en=Inglés', NOW()),
	('gl', 'ES', 'es=Español, gl=Galego, en=Inglés', NOW());

-- Groups
insert into gdai_group(groupName, creationDate, expirationDate, systemId)
values
	('GDAI_ADM', NOW(), NULL, NULL),
	('GP1', NOW(), NULL, 1),
	('GP2', NOW(), NULL, NULL),
	('GP3', NOW(), NULL, NULL),
	('GP4', NOW(), NULL, NULL),
	('GP5', NOW(), NULL, NULL),
	('GP6', NOW(), NULL, NULL),
	('GP7', NOW(), NULL, NULL),
	('GP8', NOW(), NULL, NULL),
	('GP9', NOW(), NULL, NULL),
	('GP10', NOW(), NULL, NULL);
    
-- Users
insert into gdai_user(loginName, encryptedPassword, firstName, lastName, email, phoneNumber, avatarUrl, gender, hireDate, dateOfBirth, expirationDate, groupId)
values
	('Manoel', 'AZ0OZU/oardxM', 'Manoel', 'Folgueira', 'manoel.folgueira@gdai.com', '1456',
		'https://vignette.wikia.nocookie.net/clubpenguin/images/e/e2/Smiley-300x300.png/revision/latest?cb=20090401005140',
		'M', NOW(), NOW(), '2020-08-05 18:19:03', 1),
	('a1', 'AZ0OZU/oardxM', 'b', 'b', 'b@b.com',
		'1456', 'https://vignette.wikia.nocookie.net/clubpenguin/images/e/e2/Smiley-300x300.png/revision/latest?cb=20090401005140',
		'M', NOW(), NOW(), NOW(), 2),
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
		
insert into gdai_project(projectName, projectDescription, creationDate, targetDate, createdById, systemId)
values
	('Billing Entities', 'Project Desc.', NOW(), null, 2, 1);

insert into gdai_application(applicationName, applicationDescription, creationDate, systemId)
values
	('GDAIBillingEntities', 'GDAIBillingEntities Desc.', NOW(), 1);
