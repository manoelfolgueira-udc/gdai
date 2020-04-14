package es.udc.fic.manoelfolgueira.gdai.model.languageservice;

import java.util.Map;

import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface LanguageService {

	/**
	 * Gets the list of language options ordered
	 * 
	 * @return
	 * @throws InstanceNotFoundException
	 */
	public Map<String, String> getOptionsOrdered() throws InstanceNotFoundException;

	/**
	 * Returns all the language names
	 * 
	 * @return All the language names as a string
	 * @throws InstanceNotFoundException
	 */
	public String getNames() throws InstanceNotFoundException;

	/**
	 * Whenever a new language is added to the database, reload all the language
	 * preferences from the database.
	 * 
	 * @throws InstanceNotFoundException
	 */
	public void reload() throws InstanceNotFoundException;

	// TODO implement a RESTfull Web Service to reload the language configuration
}
