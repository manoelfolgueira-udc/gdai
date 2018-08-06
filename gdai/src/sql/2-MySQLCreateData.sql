-- Groups
insert into gdai_group(name, creationTime, expirationTime)
values('Administrators', NOW(), NULL);        
    
-- Users
insert into gdai_user(loginName, encryptedPassword, firstName, lastName, email, phoneNumber, avatarUrl, gender, hireDate, dateOfBirth, expirationtime, group_id)
values("b", "AZ0OZU/oardxM", "b", "b", "b@b.com", "1456", "https://vignette.wikia.nocookie.net/clubpenguin/images/e/e2/Smiley-300x300.png/revision/latest?cb=20090401005140", "M", NOW(), NOW(), NOW(), 1);