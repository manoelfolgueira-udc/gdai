package es.udc.fic.manoelfolgueira.gdai.model.userservice;

import es.udc.fic.manoelfolgueira.gdai.model.user.User;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.DuplicateInstanceException;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface UserService {

    public User registerUser(String loginName, String clearPassword,
            UserDetails userProfileDetails)
            throws DuplicateInstanceException;

    public User login(String loginName, String password,
            boolean passwordIsEncrypted) throws InstanceNotFoundException,
            IncorrectPasswordException;

    public User findUserProfile(Long userProfileId)
            throws InstanceNotFoundException;

    public void updateUserDetails(Long userProfileId,
            UserDetails userProfileDetails)
            throws InstanceNotFoundException;

    public void changePassword(Long userProfileId, String oldClearPassword,
            String newClearPassword) throws IncorrectPasswordException,
            InstanceNotFoundException;

}
