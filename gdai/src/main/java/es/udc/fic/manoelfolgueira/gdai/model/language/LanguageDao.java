package es.udc.fic.manoelfolgueira.gdai.model.language;

import java.util.List;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface LanguageDao extends GenericDao<Language, Long> {

	/**
	 * Returns a Language by name
	 * 
	 * @param name
	 *            the language identifier
	 * @return the Language
	 */
	public Language findByName(String name) throws InstanceNotFoundException;

	/**
	 * Returns all languages
	 * 
	 * @return all Language
	 */
	public List<Language> findAll();

	/**
	 * Returns all language codes
	 * 
	 * @return all languageNames
	 */
	public List<String> findAllNames();
}
