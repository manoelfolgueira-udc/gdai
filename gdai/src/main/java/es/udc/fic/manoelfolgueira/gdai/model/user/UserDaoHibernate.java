package es.udc.fic.manoelfolgueira.gdai.model.user;

import org.springframework.stereotype.Repository;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDaoHibernate;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserDao {

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

}