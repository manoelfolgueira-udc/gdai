-- Groups
insert into gdai_group(name, creationTime, expirationTime)
values('Administrators', NOW(), NULL);        
    
-- Users
insert into gdai_user(loginName, encryptedPassword, firstName, lastName, email, ext, creationtime, expirationtime)
values("Admin", "RV2qd.VRrAKE6", "Admin", "Admin", "admin@gdai.com", "1456" ,NOW(), null);