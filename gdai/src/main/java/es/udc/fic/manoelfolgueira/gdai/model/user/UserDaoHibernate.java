package es.udc.fic.manoelfolgueira.gdai.model.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDaoHibernate;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserDao {

	@Override
	public User findByLoginName(String loginName) throws InstanceNotFoundException {

    	User user = (User) getSession().createQuery(
    			"SELECT u FROM User u WHERE u.loginName = :loginName")
    			.setParameter("loginName", loginName)
    			.uniqueResult();
    	if (user == null) {
   			throw new InstanceNotFoundException(loginName, User.class.getName());
    	} else {
    		return user;
    	}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllSortedByName() {
		return getSession().createQuery(
    			"SELECT u FROM User u order by u.loginName")
				.list();
	}
	
	@Override
	public void remove(Long userId) throws InstanceNotFoundException {
		int numRows = getSession().createQuery(
				"DELETE FROM User u WHERE u.userId = :userId")
			.setParameter("userId", userId).executeUpdate();
		
		if (numRows == 0) {
			throw new InstanceNotFoundException(userId, User.class.getName());
		}
				
	}
	
	

}