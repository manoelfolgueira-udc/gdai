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
	('api', 'AZ0OZU/oardxM', 'API', 'GDAI', 'api.gdaiapp@gmail.com',
		'660 91 94 67', 'https://image.flaticon.com/icons/svg/149/149452.svg',
		'M', NOW(), '1980-11-05 00:00:00', '2022-11-05 18:19:03', false, 1),
	('mrodriguez', 'AZ0OZU/oardxM', 'María', 'Rodríguez', 'mrodriguez.gdaiapp@gmail.com',
		'614 32 05 23', 'https://image.flaticon.com/icons/svg/149/149452.svg',
		'F', NOW(), '1996-01-06 00:00:00', '2022-12-05 18:19:03', true, 4),
	('rsuarez', 'AZ0OZU/oardxM', 'Roberto', 'Suarez', 'rsuarez.gdaiapp@gmail.com',
		'622 32 15 11', 'https://image.flaticon.com/icons/svg/149/149452.svg',
		'M', NOW(), '1977-10-22 00:00:00', '2022-12-05 18:19:03', false, 4),
	('mbravo', 'AZ0OZU/oardxM', 'Mario', 'Bravo', 'mbravo.gdaiapp@gmail.com',
		'644 12 15 31', 'https://image.flaticon.com/icons/svg/149/149452.svg',
		'M', NOW(), '1997-04-07 00:00:00', '2022-12-05 18:19:03', false, 4),
	('sdachs', 'AZ0OZU/oardxM', 'Sabela', 'Dachs', 'sdachs.gdaiapp@gmail.com',
		'655 17 53 89', 'https://image.flaticon.com/icons/svg/149/149452.svg',
		'F', NOW(), '1995-12-05 00:00:00', '2022-12-05 18:19:03', true, 2),
	('alopez', 'AZ0OZU/oardxM', 'Ángel', 'López', 'alopez.gdaiapp@gmail.com',
		'601 29 22 87', 'https://image.flaticon.com/icons/svg/149/149452.svg',
		'M', NOW(), '1991-03-02 00:00:00', '2022-12-05 18:19:03', false, 2),
	('csoto', 'AZ0OZU/oardxM', 'Carlos', 'Soto', 'csoto.gdaiapp@gmail.com',
		'611 98 00 64', 'https://image.flaticon.com/icons/svg/149/149452.svg',
		'M', NOW(), '1982-01-24 00:00:00', '2022-12-05 18:19:03', false, 2),
	('rgarcia', 'AZ0OZU/oardxM', 'Rubén', 'García', 'rgarcia.gdaiapp@gmail.com',
		'740 56 27 89', 'https://image.flaticon.com/icons/svg/149/149452.svg',
		'M', NOW(), '1984-11-14 00:00:00', '2022-08-02 18:19:03', true, 3),
	('carias', 'AZ0OZU/oardxM', 'Carla', 'Arias', 'carias.gdaiapp@gmail.com',
		'699 84 47 19', 'https://image.flaticon.com/icons/svg/149/149452.svg',
		'F', NOW(), '1977-12-05 00:00:00', '2022-08-02 18:19:03', false, 3);
		
-- Systems
insert into gdai_system(systemName, systemDescription, creationDate, expirationDate, groupId)
values
	('Facturador', 'Sistema raíz de facturación.', NOW(), null, 3),
	('CRM', 'Sistema gestor de clientes.', NOW(), null, 2),
	('Campaign', 'Contratación de servicios vía web.', NOW(), null, 4);

-- Applications
insert into gdai_application(applicationName, applicationDescription, creationDate, expirationDate, systemId)
values
	('Fact', 'Aplicación principal de facturación.', NOW(), null, 1),
	('Promator', 'Software de gestión de cargos y promociones.', NOW(), null, 1),
	('CRMALL', 'Aplicación principal de gestión de clientes', NOW(), null, 2),
	('Vista', 'Software que ofrece datos mediante vistas y servicios web', NOW(), null, 2),
	('CampaignWeb', 'Aplicación web para realizar contrataciones mediante campañas.', NOW(), null, 3);
	
-- User Stories
insert into gdai_userstory(userStoryName, userStoryDescription, creationDate, createdById)
values
	('Añadir Reparación Campaign', 'Permitir contratar reparaciones a domicilio.', NOW(), 2),
	('Pago Criptomonedas', 'Permitir pagar mediante criptomonedas.', NOW(), 2);
		
-- Projects
insert into gdai_project(projectName, projectDescription, creationDate, requirementsPath,
	createdById, systemId, userStoryId)
values
	('Contratar reparaciones.', 'Contratar productos de reparación.', NOW(), 'src/main/resources/uploads/project_requirements/P-0000001_requisitos.pdf', 3, 1, 1),
	('Pagar con criptomonedas', 'Implementar método de pago con criptomonedas.', NOW(), 'src/main/resources/uploads/project_requirements/P-0000002_requisitos.pdf', 3, 3, 2),
		
	('Contratar reparaciones por campañas.', 'Contratar productos de reparación por campañas.', NOW(), 'src/main/resources/uploads/project_requirements/P-0000003_requisitos.pdf', 3, 1, 1),
	('Incluir más tipos de criptomonedas', 'Incluir más tipos de criptomonedas.', NOW(), 'src/main/resources/uploads/project_requirements/P-0000004_requisitos.pdf', 3, 3, 2);

-- Sprints
-- Scheduler
insert into gdai_sprint(sprintName, sprintStart, sprintEnd, creationDate)
SELECT
    CONCAT(
        'SPRINT MAYO ',
        case CAST(COUNT(sp.sprintId) + 1 AS CHAR(50)) when '1' then 'I' end
    ),
    '2021-05-01 00:00:00', '2021-05-16 00:00:00' - INTERVAL 1 SECOND, NOW()
FROM
    gdai_sprint sp;

insert into gdai_sprint(sprintName, sprintStart, sprintEnd, creationDate)
values
 ('SPRINT MAYO II', '2021-05-16 00:00:00', '2021-06-01 00:00:00' - INTERVAL 1 SECOND, NOW()),
 
 ('SPRINT JUNIO I', '2021-06-01 00:00:00', '2021-06-16 00:00:00' - INTERVAL 1 SECOND, NOW()),
 ('SPRINT JUNIO II', '2021-06-16 00:00:00', '2021-07-01 00:00:00' - INTERVAL 1 SECOND, NOW()),
 
 ('SPRINT JULIO I', '2021-07-01 00:00:00', '2021-07-16 00:00:00' - INTERVAL 1 SECOND, NOW()),
 ('SPRINT JULIO II', '2021-07-16 00:00:00', '2021-08-01 00:00:00' - INTERVAL 1 SECOND, NOW()),
 
 ('SPRINT AGOSTO I', '2021-08-01 00:00:00', '2021-08-16 00:00:00' - INTERVAL 1 SECOND, NOW()),
 ('SPRINT AGOSTO II', '2021-08-16 00:00:00', '2021-09-01 00:00:00' - INTERVAL 1 SECOND, NOW()),
 
 ('SPRINT SEPTIEMBRE I', '2021-09-01 00:00:00', '2021-09-16 00:00:00' - INTERVAL 1 SECOND, NOW()),
 ('SPRINT SEPTIEMBRE II', '2021-09-16 00:00:00', '2021-10-01 00:00:00' - INTERVAL 1 SECOND, NOW());

    
-- project_sprint_jt
insert into gdai_project_sprint_jt(projectId, sprintId)
values
	(1, 10), (2,10), (3,10), (4,10);
	
-- gdai_case
insert into gdai_gdai_case(gdaiCaseDescription, gdaiCaseResolution, creationDate, createdById, systemId)
values
	('La web no está permitiendo realizar contrataciones.', 'Se reinicia el aplicativo y ya funciona.', NOW(), 1, 3),
	('La web no permite aplicar la promoción de bienvenida.', null, NOW(), 1, 3),
	('No está permitiendo la web realizar pagos mediante Paypal.', null, NOW(), 1, 3),
	
	('El facturador no muestra la última factura de este cliente.', null, NOW(), 1, 1),
	('No vemos reflejado el cargo de instalación en este cliente.', 'Debido a un proceos batch. Corregido.', NOW(), 1, 1),
	('No podemos acceder al facturador.', null, NOW(), 1, 1),
	
	('No podemos modificar la información de la cuenta bancaria de este cliente.', null, NOW(), 1, 2),
	('Lo contratado por este cliente pertenece a este otro cliente. ¿Lo revisáis?', null, NOW(), 1, 2);

-- Projects
insert into gdai_production_pass(productionPassName, productionPassResolution, creationDate, passPath, createdById, systemId)
values
	('Pase contratar reparaciones', 'Pase contratar productos de reparación.', NOW(), 'src/main/resources/uploads/production_passes/W-0000001_requisitos.pdf', 6, 2);