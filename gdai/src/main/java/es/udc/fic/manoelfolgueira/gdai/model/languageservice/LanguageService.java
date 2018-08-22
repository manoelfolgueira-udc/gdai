package es.udc.fic.manoelfolgueira.gdai.model.languageservice;

import java.util.Map;

import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface LanguageService {

	public Map<String, String> getOptionsOrdered() throws InstanceNotFoundException;

	public String getNames() throws InstanceNotFoundException;
	
	public void reload() throws InstanceNotFoundException;
}
