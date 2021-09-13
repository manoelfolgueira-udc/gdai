-- AvailableLanguages
insert into gdai_language(languageName, languageCountry, options, creationDate)
values
	('en', 'EN', 'en=English, gl=Galician, es=Spanish', NOW()),
	('es', 'ES', 'es=Español, gl=Gallego, en=Inglés', NOW()),
	('gl', 'ES', 'es=Español, gl=Galego, en=Inglés', NOW());

--Groups
INSERT INTO gdai_group (groupName,groupDescription,creationDate,expirationDate) VALUES ("GDAI_ADM","",NOW(), null);
INSERT INTO gdai_group (groupName,groupDescription,creationDate,expirationDate) VALUES ("D_CLIENTES","",NOW(),null);
INSERT INTO gdai_group (groupName,groupDescription,creationDate,expirationDate) VALUES ("D_FACTURACION","",NOW(),null);
INSERT INTO gdai_group (groupName,groupDescription,creationDate,expirationDate) VALUES ("D_WEB_ELECTRONICA","",NOW(),null);
-- Users
insert into gdai_user(loginName, encryptedPassword, firstName, lastName, email, phoneNumber, avatarUrl, gender, hireDate, dateOfBirth, expirationDate, isManager, groupId)
values
	('agdai', 'AZ0OZU/oardxM', 'Admin', 'GDAI', 'gdaiapp@gmail.com',
		'660 91 94 67', 'https://image.flaticon.com/icons/svg/149/149452.svg',
		'M', NOW(), '1980-11-05 00:00:00', '2022-11-05 18:19:03', false, 1),
	('mrodriguez', 'AZ0OZU/oardxM', 'María', 'Rodríguez', 'usuario.gdaiapp@gmail.com',
		'614 32 05 23', 'https://image.flaticon.com/icons/svg/149/149452.svg',
		'F', NOW(), '1996-01-06 00:00:00', '2022-12-05 18:19:03', true, 4),
	('sdachs', 'AZ0OZU/oardxM', 'Sabela', 'Dachs', 'sdachs.gdaiapp@gmail.com',
		'614 32 05 23', 'https://image.flaticon.com/icons/svg/149/149452.svg',
		'F', NOW()-62, '1998-08-08 00:00:00', '2022-12-05 18:19:03', false, 2),
	('rgarcia', 'AZ0OZU/oardxM', 'Rubén', 'García', 'rgarcia@gdaiapp.com',
		'740 56 27 89', 'https://image.flaticon.com/icons/svg/149/149452.svg',
		'M', NOW(), '1974-11-14 00:00:00', '2022-08-02 18:19:03', false, 3);
		
-- Systems
insert into gdai_system(systemName, systemDescription, creationDate, expirationDate, groupId)
values
	('Fact', 'Sistema raíz de facturación.', NOW(), null, 3),
	('CRM', 'Sistema gestor de clientes.', NOW(), null, 2),
	('Campaign', 'Contratación de servicios vía web.', NOW(), null, 4),
	('Promator', 'Sistema de gestión de promociones y cargos.', NOW(), null, 3);

-- Applications
insert into gdai_application(applicationName, applicationDescription, creationDate, expirationDate, systemId)
values
	('Facturador', 'Aplicación principal de facturación.', NOW(), null, 1),
	('CRMALL', 'Aplicación principal de gestiónde clientes', NOW(), null, 2),
	('CampaignWeb', 'Aplicación web para realizar contrataciones mediante campañas.', NOW(), null, 3);
	
-- User Stories
insert into gdai_userstory(userStoryName, userStoryDescription, creationDate, createdById)
values
	('Añadir Reparación Campaign', 'Permitir contratar reparaciones a domicilio.', NOW(), 2),
	('Pago Transferencia', 'Permitir pagar mediante transferencia bancaria.', NOW(), 2);
		
-- Projects
insert into gdai_project(projectName, projectDescription, creationDate, requirementsPath,
	createdById, systemId, userStoryId)
values
	('Billing Entities', 'Project Desc.', NOW(), 'src/main/resources/uploads/project_requirements/P-000004_eg_desarrollo_realizado.pdf', 2, 3, 1),
	('GDAIBilling', 'Project Desc.', NOW(), 'src/main/resources/uploads/project_requirements/P-000004_eg_desarrollo_realizado.pdf', 2, 3, 2),
	('CRM', 'Project Desc.', NOW(), 'src/main/resources/uploads/project_requirements/P-000004_eg_desarrollo_realizado.pdf', 2, 3, 1);
	
-- Sprints
-- Scheduler

insert into gdai_sprint(sprintName, sprintStart, sprintEnd, creationDate)
SELECT
   CONCAT(
        'SP_',
        CAST(COUNT(sp.sprintId) + 1 AS CHAR(50))
    ),
    CURRENT_DATE, CURRENT_DATE + INTERVAL 2 * 7 DAY - INTERVAL 1 SECOND, NOW()
FROM
    gdai_sprint sp;

insert into gdai_sprint(sprintName, sprintStart, sprintEnd, creationDate)
SELECT
   CONCAT(
        'SP_',
        CAST(COUNT(sp.sprintId) + 1 AS CHAR(50))
    ),
    (select max(sprintEnd) from gdai_sprint) + INTERVAL 1 SECOND, (select max(sprintEnd) from gdai_sprint) + INTERVAL 2 * 7 DAY, NOW()
FROM
    gdai_sprint sp;
    
-- project_sprint_jt
insert into gdai_project_sprint_jt(projectId, sprintId)
values
	(1, 1), (2,1), (3,1);