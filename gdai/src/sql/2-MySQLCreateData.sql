-- AvailableLanguages
insert into gdai_language(languageName, languageCountry, options, creationTime)
values
	('en', 'EN', 'en=English, gl=Galician, es=Spanish', NOW()),
	('es', 'ES', 'es=Español, gl=Gallego, en=Inglés', NOW()),
	('gl', 'ES', 'es=Español, gl=Galego, en=Inglés', NOW());

-- Groups
insert into gdai_group(groupName, creationTime, expirationTime)
values
	('GDAI_ADM', NOW(), NULL),
	('GP1', NOW(), NULL),
	('GP2', NOW(), NULL),
	('GP3', NOW(), NULL),
	('GP4', NOW(), NULL),
	('GP5', NOW(), NULL),
	('GP6', NOW(), NULL),
	('GP7', NOW(), NULL),
	('GP8', NOW(), NULL),
	('GP9', NOW(), NULL),
	('GP10', NOW(), NULL);
    
-- Users
insert into gdai_user(loginName, encryptedPassword, firstName, lastName, email, phoneNumber, avatarUrl, gender, hireDate, dateOfBirth, expirationtime, groupId)
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