package es.udc.fic.manoelfolgueira.gdai.web.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.annotations.Inject;

import es.udc.fic.manoelfolgueira.gdai.model.languageservice.LanguageService;
import es.udc.fic.manoelfolgueira.gdai.model.util.Config;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.util.AvailableLanguages;

/**
 * This module is automatically included as part of the Tapestry IoC Registry,
 * it's a good place to configure and extend Tapestry, or to place your own
 * service definitions.
 */

public class AppModule {

	@Inject
	private LanguageService languageService;

	public void contributeApplicationDefaults(MappedConfiguration<String, String> configuration)
			throws InstanceNotFoundException {

		AvailableLanguages.initialize(languageService);
		configuration.add(SymbolConstants.SUPPORTED_LOCALES, AvailableLanguages.getNames());
		configuration.add(SymbolConstants.JAVASCRIPT_INFRASTRUCTURE_PROVIDER, "jquery");

		// Initialize Properties Configuration
		Config.getInstance();

		configuration.add(SymbolConstants.HMAC_PASSPHRASE,
				Config.getInstance().getProperties().getProperty("HMAC_PASSPHRASE"));

	}

}
