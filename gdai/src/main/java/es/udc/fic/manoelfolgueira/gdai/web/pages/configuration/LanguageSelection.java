package es.udc.fic.manoelfolgueira.gdai.web.pages.configuration;

import java.util.Locale;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PersistentLocale;

import es.udc.fic.manoelfolgueira.gdai.web.pages.Index;
import es.udc.fic.manoelfolgueira.gdai.web.util.AvailableLanguages;

/**
 * Provides language selection to any User
 * 
 * @author Manoel Folgueira <manoel.folgueira@udc.es>
 * @file LanguageSelection.java
 */
public class LanguageSelection {

	@Property
	private String language;

	@Inject
	private Locale locale;

	@Inject
	private PersistentLocale persistentLocale;

	void onPrepareForRender() {
		language = locale.getLanguage();
	}

	public String getLanguages() {
		return AvailableLanguages.getOptions(locale.getLanguage());
	}

	Object onSuccess() {

		persistentLocale.set(new Locale(language));

		return Index.class;

	}

}
