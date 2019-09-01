-- CFG
insert into gdai_cfg(cfgAttr, cfgVal, creationDate)
values
	('CFG_SP_WEEKS', '2', NOW());

-- AvailableLanguages
insert into gdai_language(languageName, languageCountry, options, creationDate)
values
	('en', 'EN', 'en=English, gl=Galician, es=Spanish', NOW()),
	('es', 'ES', 'es=Español, gl=Gallego, en=Inglés', NOW()),
	('gl', 'ES', 'es=Español, gl=Galego, en=Inglés', NOW());

--Groups
insert into gdai_group(groupName, creationDate)
values
	('GDAI_ADM', NOW()),
	('GP1', 	 NOW()),
	('GP2', 	 NOW()),
	('GP3', 	 NOW()),
	('GP4', 	 NOW()),
	('GP5', 	 NOW()),
	('GP6', 	 NOW()),
	('GP7', 	 NOW()),
	('GP8', 	 NOW()),
	('GP9', 	 NOW()),
	('GP10', 	 NOW());
	
--INSERT INTO gdai_group (groupName,creationDate,expirationDate) VALUES ("Poole","2019-08-19 20:12:59","2020-12-27 10:14:58"),("Shrewsbury","2019-08-19 14:24:41","2020-08-19 21:20:25"),("Meix-Devant-Virton","2019-08-19 07:34:32","2020-12-06 18:43:15"),("Casacalenda","2019-08-19 16:01:06","2021-02-13 15:12:36"),("Auckland","2019-08-19 16:25:45","2020-12-24 13:07:20"),("Baulers","2019-08-19 18:41:57","2020-11-05 14:09:34"),("Aschersleben","2019-08-19 00:10:00","2021-04-13 16:54:25"),("Leersum","2019-08-19 17:56:05","2021-04-05 20:19:24"),("Lithgow","2019-08-19 15:43:24","2021-03-02 12:00:39"),("Bischofshofen","2019-08-19 19:33:42","2021-07-05 16:53:12");
--INSERT INTO gdai_group (groupName,creationDate,expirationDate) VALUES ("Freiburg","2019-08-19 09:02:16","2020-10-17 23:28:41"),("Pulle","2019-08-19 10:14:27","2020-11-06 16:33:19"),("Ipatinga","2019-08-19 11:32:12","2021-02-09 02:01:30"),("Donk","2019-08-19 06:07:28","2020-10-25 09:01:02"),("Sadiqabad","2019-08-19 17:43:53","2020-10-30 00:23:16"),("Argenteuil","2019-08-19 21:49:13","2020-12-27 23:07:27"),("María Pinto","2019-08-19 06:20:15","2021-01-31 10:22:29"),("Relegem","2019-08-19 09:28:57","2021-02-22 23:11:09");

    
-- Users
insert into gdai_user(loginName, encryptedPassword, firstName, lastName, email, phoneNumber, avatarUrl, gender, hireDate, dateOfBirth, expirationDate, isManager, groupId)
values
	('Manoel', 'AZ0OZU/oardxM', 'Manoel', 'Folgueira', 'manoel.folgueira@gdai.com', '1456',
		'https://image.flaticon.com/icons/svg/149/149452.svg',
		'M', NOW(), NOW(), '2020-08-05 18:19:03', true, 1),
	('a1', 'AZ0OZU/oardxM', 'b', 'b', 'b@b.com',
		'1456', 'https://image.flaticon.com/icons/svg/149/149452.svg',
		'M', NOW(), NOW(), '2020-08-05 18:19:03', false, 2),
	('a2', 'AZ0OZU/oardxM', 'b', 'b', 'b@b.com',
		'1456', 'https://image.flaticon.com/icons/svg/149/149452.svg',
		'M', NOW(), NOW(), NOW(), false, 2),
	('a3', 'AZ0OZU/oardxM', 'b', 'b', 'b@b.com',
		'1456', 'https://image.flaticon.com/icons/svg/149/149452.svg',
		'M', NOW(), NOW(), NOW(), false, 2),
	('a4', 'AZ0OZU/oardxM', 'b', 'b', 'b@b.com',
		'1456', 'https://image.flaticon.com/icons/svg/149/149452.svg',
		'M', NOW(), NOW(), NOW(), false, 2),
	('a5', 'AZ0OZU/oardxM', 'b', 'b', 'b@b.com',
		'1456', 'https://image.flaticon.com/icons/svg/149/149452.svg',
		'M', NOW(), NOW(), NOW(), false, 2);
		
-- Systems
insert into gdai_system(systemName, systemDescription, creationDate, groupId)
values
	('Billing', 'Description here.', NOW(), 1),
	('CRM', 'Description here.', NOW(), 2);
	
-- User Stories
insert into gdai_userstory(userStoryName, userStoryDescription, creationDate, createdById)
values
	('usN', 'usD', NOW(), 1);
		
-- Projects
insert into gdai_project(projectName, projectDescription, creationDate, requirementsPath,
	createdById, systemId, userStoryId)
values
	('Billing Entities', 'Project Desc.', NOW(), 'src/main/resources/uploads/project_requirements/P-000004_eg_desarrollo_realizado.pdf', 2, 1, 1),
	('GDAIBilling', 'Project Desc.', NOW(), 'src/main/resources/uploads/project_requirements/P-000004_eg_desarrollo_realizado.pdf', 2, 1, 1),
	('CRM', 'Project Desc.', NOW(), 'src/main/resources/uploads/project_requirements/P-000004_eg_desarrollo_realizado.pdf', 2, 2, 1);
	
-- Sprints
-- Scheduler

insert into gdai_sprint(sprintName, sprintStart, sprintEnd, creationDate, createdById)
SELECT
   CONCAT(
        'SP_',
        CAST(COUNT(sp.sprintId) + 1 AS CHAR(50))
    ),
    CURRENT_DATE, CURRENT_DATE + INTERVAL cfg.cfgVal * 7 DAY, NOW(), 1
FROM
    gdai_sprint sp right outer join gdai_cfg cfg
on cfg.cfgAttr = 'CFG_SP_WEEKS';

--CREATE EVENT gdai_event_insert_sprint
--    ON SCHEDULE
--      EVERY 10 MINUTE
--      STARTS NOW() + INTERVAL 10 MINUTE
--    COMMENT 'Inserts the current sprint'
--    DO
--insert into gdai_sprint(sprintName, sprintStart, sprintEnd, creationDate, createdById)
--SELECT
--   CONCAT(
--        'SP_',
--        CAST(COUNT(sp.sprintId) + 1 AS CHAR(50))
--    ),
--    CURRENT_DATE + INTERVAL cfg.cfgVal * 7 DAY, CURRENT_DATE + INTERVAL cfg.cfgVal*cfg.cfgVal * 7 DAY, NOW(), 1
--FROM
--    gdai_sprint sp right outer join gdai_cfg cfg
--on cfg.cfgAttr = 'CFG_SP_WEEKS';

-- project_sprint_jt
insert into gdai_project_sprint_jt(projectId, sprintId)
values
	(1, 1), (2,1), (3,1);

-- Applications
insert into gdai_application(applicationName, applicationDescription, creationDate, systemId)
values
	('GDAIBillingEntities', 'GDAIBillingEntities Desc.', NOW(), 1),
	('GDAIBilling', 'GDAIBilling Desc.', NOW(), 1),
	('CRM', 'CRM Desc.', NOW(), 2);
--INSERT INTO gdai_application (applicationName,applicationDescription,creationDate,systemId) VALUES ("Galicia","Ethiopia","2020-04-02 18:02:22",1),("Illes Balears","Bahamas","2020-06-25 07:46:24",1),("CN","Costa Rica","2020-02-25 09:29:38",1),("Aragón","Angola","2019-08-30 21:13:15",1),("CV","Romania","2019-02-22 08:59:00",1),("PV","Croatia","2019-06-17 07:06:30",1),("Castilla - La Mancha","South Africa","2020-01-29 07:09:56",1),("CV","Yemen","2018-12-27 17:05:52",1),("Illes Balears","Albania","2018-12-26 20:35:34",1),("Ceuta","South Africa","2019-10-21 20:04:51",1);
--INSERT INTO gdai_application (applicationName,applicationDescription,creationDate,systemId) VALUES ("Madrid","Tonga","2018-12-03 07:28:44",1),("Canarias","Tajikistan","2018-09-16 00:54:24",1),("Principado de Asturias","Korea, North","2019-01-16 22:57:57",1),("La Rioja","Slovakia","2019-11-08 17:09:31",1),("Catalunya","Guernsey","2019-08-18 16:10:03",1),("PV","Guatemala","2019-02-26 22:19:41",1),("CN","Wallis and Futuna","2019-10-12 22:39:46",1),("Cantabria","Dominica","2019-03-17 04:24:12",1),("MA","Palestine, State of","2020-05-06 15:28:16",1),("Galicia","South Africa","2018-10-05 09:11:41",1);
--INSERT INTO gdai_application (applicationName,applicationDescription,creationDate,systemId) VALUES ("AN","Tuvalu","2019-09-15 02:34:38",1),("Madrid","Norfolk Island","2020-01-19 03:08:33",1),("Comunitat Valenciana","Peru","2020-06-22 06:52:21",1),("CA","New Zealand","2018-09-14 16:41:51",1),("Comunitat Valenciana","Syria","2020-02-15 01:45:11",1),("BA","Tonga","2018-10-26 18:08:11",1),("CM","Bouvet Island","2020-01-26 15:52:37",1),("MA","Lithuania","2020-07-02 23:58:17",1),("Castilla - La Mancha","Mauritania","2019-02-27 14:26:51",1),("CV","Antarctica","2019-07-25 03:02:08",1);
--INSERT INTO gdai_application (applicationName,applicationDescription,creationDate,systemId) VALUES ("LR","Korea, South","2020-06-20 08:41:06",1),("Melilla","Ethiopia","2018-11-15 18:06:33",1),("EX","Dominica","2018-10-03 22:41:41",1),("Comunitat Valenciana","Angola","2019-10-16 13:14:00",1),("CE","Uruguay","2019-04-22 17:08:13",1),("Castilla - La Mancha","Tanzania","2020-01-18 09:38:39",1),("ME","Romania","2020-05-14 00:56:15",1),("CA","Iceland","2020-05-03 18:55:28",1),("Extremadura","Bahamas","2019-03-04 12:38:34",1),("AS","Brunei","2019-12-20 14:24:57",1);
--INSERT INTO gdai_application (applicationName,applicationDescription,creationDate,systemId) VALUES ("AS","Lithuania","2020-03-23 09:36:19",1),("Catalunya","Tonga","2019-10-04 04:12:21",1),("Canarias","Cook Islands","2019-09-09 06:23:32",1),("ME","India","2018-11-07 10:39:52",1),("MU","Saint Lucia","2019-05-13 04:15:57",1),("CA","Qatar","2019-07-03 22:24:57",1),("Euskadi","Oman","2019-11-06 15:26:01",1),("LR","Suriname","2020-05-18 16:56:47",1),("Ceuta","Cameroon","2019-10-07 18:25:25",1),("Navarra","Haiti","2019-11-03 22:31:21",1);
--INSERT INTO gdai_application (applicationName,applicationDescription,creationDate,systemId) VALUES ("GA","Turkey","2019-09-29 17:24:51",1),("BA","Aruba","2018-10-28 15:42:39",1),("AS","Suriname","2020-07-08 05:10:52",1),("Canarias","Ghana","2020-03-09 10:16:15",1),("Galicia","Cameroon","2019-05-15 21:49:54",1),("PV","South Georgia and The South Sandwich Islands","2020-06-07 14:00:59",1),("Euskadi","Greece","2019-11-30 00:38:29",1),("AR","Papua New Guinea","2020-06-17 11:00:44",1),("Comunitat Valenciana","Rwanda","2019-02-14 03:09:13",1),("MA","Kuwait","2020-03-02 21:59:24",1);
--INSERT INTO gdai_application (applicationName,applicationDescription,creationDate,systemId) VALUES ("Navarra","Senegal","2019-12-04 08:32:52",1),("CN","Eritrea","2018-10-27 21:47:44",1),("Euskadi","Haiti","2020-06-23 15:12:05",1),("Cantabria","Madagascar","2019-02-18 11:30:49",1),("Canarias","Jamaica","2018-11-02 06:54:47",1),("GA","Bonaire, Sint Eustatius and Saba","2019-08-16 14:39:55",1),("Principado de Asturias","Slovenia","2020-03-18 22:43:23",1),("Illes Balears","Andorra","2019-09-22 04:06:28",1),("CL","Central African Republic","2018-10-19 03:44:57",1),("CA","French Southern Territories","2020-01-15 04:16:35",1);
--INSERT INTO gdai_application (applicationName,applicationDescription,creationDate,systemId) VALUES ("Catalunya","Netherlands","2020-03-15 08:03:32",1),("Principado de Asturias","Djibouti","2019-12-12 18:34:30",1),("Aragón","Cambodia","2020-07-26 22:32:11",1),("Comunitat Valenciana","Russian Federation","2019-12-19 21:20:01",1),("Illes Balears","Congo (Brazzaville)","2019-08-01 00:02:23",1),("CN","Seychelles","2019-10-25 22:15:56",1),("Ceuta","Morocco","2020-06-17 20:22:22",1),("EX","Panama","2019-11-09 11:07:54",1),("CN","Wallis and Futuna","2019-02-27 18:40:11",1),("Catalunya","France","2020-05-21 09:10:46",1);
--INSERT INTO gdai_application (applicationName,applicationDescription,creationDate,systemId) VALUES ("CA","Zambia","2019-06-23 12:02:42",1),("CE","Aruba","2020-01-06 14:32:08",1),("Catalunya","Niue","2020-04-01 01:02:37",1),("AN","United States","2019-09-22 10:14:31",1),("Melilla","Djibouti","2018-10-29 15:56:25",1),("PV","United States Minor Outlying Islands","2018-12-13 18:38:44",1),("Castilla y León","Liechtenstein","2019-10-22 02:08:26",1),("Murcia","Norfolk Island","2020-06-05 04:30:30",1),("PV","Saint Kitts and Nevis","2020-08-07 02:00:49",1),("CA","Ghana","2019-01-06 06:01:22",1);
--INSERT INTO gdai_application (applicationName,applicationDescription,creationDate,systemId) VALUES ("PV","Tunisia","2019-04-09 10:24:46",1),("AN","Senegal","2019-11-21 02:29:02",1),("CA","Faroe Islands","2020-07-01 00:11:27",1),("EX","Botswana","2020-02-26 15:02:46",1),("CV","Saint Martin","2018-12-25 23:50:09",1),("EX","Niue","2019-04-18 02:41:15",1),("AS","Christmas Island","2018-11-08 20:37:55",1),("AR","Egypt","2019-10-30 14:19:51",1),("CL","Pitcairn Islands","2018-10-15 12:42:43",1),("Galicia","Niger","2019-10-03 08:48:41",1);