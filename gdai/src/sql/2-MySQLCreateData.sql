-- Groups
insert into Group(name, creationTime, expirationTime)
values('Administrators', NOW(), NULL);        
    
-- Users
insert into User(loginName, encryptedPassword, firstName, lastName, email)
values("Admin", "RV2qd.VRrAKE6", "Admin", "Admin", "admin@gdai.com");