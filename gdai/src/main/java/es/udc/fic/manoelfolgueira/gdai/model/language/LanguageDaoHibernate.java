package es.udc.fic.manoelfolgueira.gdai.model.language;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDaoHibernate;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

@Repository("languageDao")
public class LanguageDaoHibernate extends GenericDaoHibernate<Language, Long> implements LanguageDao {

	public Language findByName(String languageName) throws InstanceNotFoundException {

    	Language group = (Language) getSession().createQuery(
    			"SELECT l FROM Language l WHERE l.languageName = :languageName")
    			.setParameter("languageName", languageName)
    			.uniqueResult();
    	if (group == null) {
   			throw new InstanceNotFoundException(languageName, Language.class.getName());
    	} else {
    		return group;
    	}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Language> findAll() {
		List<Language> languages = getSession().createQuery("SELECT l FROM Language l").list();
		return languages;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllNames() {
		List<String> languageNames = getSession().createQuery("SELECT l.languageName FROM Language l").list();
		return languageNames;
	}

}