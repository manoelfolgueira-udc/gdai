package es.udc.fic.manoelfolgueira.gdai.model.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;
import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.user.UserDao;
import es.udc.fic.manoelfolgueira.gdai.model.userservice.util.PasswordEncrypter;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    
    public User registerUser(String loginName, String clearPassword,
            UserDetails userDetails, Group group)
            throws DuplicateInstanceException {

        try {
            userDao.findByLoginName(loginName);
            throw new DuplicateInstanceException(loginName,
                    User.class.getName());
        } catch (InstanceNotFoundException e) {
            String encryptedPassword = PasswordEncrypter.crypt(clearPassword);

            User userProfile = new User(loginName,
                    encryptedPassword, userDetails.getFirstName(),
                    userDetails.getLastName(), userDetails.getGender(), userDetails
                        .getEmail(), userDetails.getPhoneNumber(), userDetails.getAvatarUrl(),
                        userDetails.getHiredate(), userDetails.getDateOfBirth(), userDetails.getExpirationTime(), group);

            userDao.save(userProfile);
            return userProfile;
        }

    }

    @Transactional(readOnly = true)
    public User login(String loginName, String password,
            boolean passwordIsEncrypted) throws InstanceNotFoundException,
            IncorrectPasswordException {

        User userProfile = userDao.findByLoginName(loginName);
        String storedPassword = userProfile.getEncryptedPassword();

        if (passwordIsEncrypted) {
            if (!password.equals(storedPassword)) {
                throw new IncorrectPasswordException(loginName);
            }
        } else {
            if (!PasswordEncrypter.isClearPasswordCorrect(password,
                    storedPassword)) {
                throw new IncorrectPasswordException(loginName);
            }
        }
        return userProfile;

    }

    @Transactional(readOnly = true)
    public User findUserProfile(Long userProfileId)
            throws InstanceNotFoundException {

        return userDao.find(userProfileId);
    }

    public void updateUserDetails(Long userProfileId,
            UserDetails userDetails)
            throws InstanceNotFoundException {

        User userProfile = userDao.find(userProfileId);
        userProfile.setFirstName(userDetails.getFirstName());
        userProfile.setLastName(userDetails.getLastName());
        userProfile.setEmail(userDetails.getEmail());

    }

    public void changePassword(Long userProfileId, String oldClearPassword,
            String newClearPassword) throws IncorrectPasswordException,
            InstanceNotFoundException {

        User userProfile;
        userProfile = userDao.find(userProfileId);

        String storedPassword = userProfile.getEncryptedPassword();

        if (!PasswordEncrypter.isClearPasswordCorrect(oldClearPassword,
                storedPassword)) {
            throw new IncorrectPasswordException(userProfile.getLoginName());
        }

        userProfile.setEncryptedPassword(PasswordEncrypter
                .crypt(newClearPassword));

    }

}
