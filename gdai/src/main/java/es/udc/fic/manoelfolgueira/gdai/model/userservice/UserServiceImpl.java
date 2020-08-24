package es.udc.fic.manoelfolgueira.gdai.model.userservice;

import java.util.LinkedList;
import java.util.List;

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

	/**
	 * {@inheritDoc}
	 */
	public UserDetails registerUser(String clearPassword, UserDetails userDetails)
			throws DuplicateInstanceException {

		try {
			userDao.findByLoginName(userDetails.getLoginName());
			throw new DuplicateInstanceException(userDetails.getLoginName(), User.class.getName());
		} catch (InstanceNotFoundException e) {
			String encryptedPassword = PasswordEncrypter.crypt(clearPassword);

			User user = new User(userDetails);
			
			user.setEncryptedPassword(encryptedPassword);

			userDao.save(user);
			
			userDetails.setUserId(user.getUserId());
			
			return userDetails;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = true)
	public UserDetails login(String loginName, String password, boolean passwordIsEncrypted)
			throws InstanceNotFoundException, IncorrectPasswordException {

		User user = userDao.findByLoginName(loginName);
		String storedPassword = user.getEncryptedPassword();

		if (passwordIsEncrypted) {
			if (!password.equals(storedPassword)) {
				throw new IncorrectPasswordException(loginName);
			}
		} else {
			if (!PasswordEncrypter.isClearPasswordCorrect(password, storedPassword)) {
				throw new IncorrectPasswordException(loginName);
			}
		}
		return new UserDetails(user);

	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = true)
	public UserDetails findUser(Long userId) throws InstanceNotFoundException {

		return new UserDetails(userDao.find(userId));
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateUserDetails(Long userProfileId, UserDetails userDetails) throws InstanceNotFoundException {

		User user = userDao.find(userProfileId);

		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setGender(userDetails.getGender());
		user.setPhoneNumber(userDetails.getPhoneNumber());
		user.setAvatarUrl(userDetails.getAvatarUrl());

		user.setHireDate(userDetails.getHireDate());
		user.setDateOfBirth(userDetails.getDateOfBirth());
		user.setExpirationDate(userDetails.getExpirationDate());

		user.setIsManager(userDetails.getIsManager());

		user.setGroup(new Group(userDetails.getGroup()));

		userDao.save(user);

	}

	/**
	 * {@inheritDoc}
	 */
	public void changePassword(Long userProfileId, String oldClearPassword, String newClearPassword)
			throws IncorrectPasswordException, InstanceNotFoundException {

		User user = userDao.find(userProfileId);

		String storedPassword = user.getEncryptedPassword();

		if (!PasswordEncrypter.isClearPasswordCorrect(oldClearPassword, storedPassword)) {
			throw new IncorrectPasswordException(user.getLoginName());
		}

		user.setEncryptedPassword(PasswordEncrypter.crypt(newClearPassword));
		
		userDao.save(user);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserDetails> findAllSortedByName() {
		
		LinkedList<UserDetails> usersDetails = new LinkedList<>();
		
		userDao.findAllSortedByName().forEach(u -> {
			usersDetails.add(new UserDetails(u));
		});
		
		return usersDetails;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(Long userId) throws InstanceNotFoundException {
		userDao.remove(userId);
	}

}
