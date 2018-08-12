package es.udc.fic.manoelfolgueira.gdai.web.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;

import es.udc.fic.manoelfolgueira.gdai.web.util.AvailableLanguages;


/**
 * This module is automatically included as part of the Tapestry IoC Registry,
 * it's a good place to configure and extend Tapestry, or to place your own
 * service definitions.
 */
public class AppModule {

    public static void contributeApplicationDefaults(
        MappedConfiguration<String, String> configuration) {

    	AvailableLanguages.initialize();
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, 
        		AvailableLanguages.getCodes());
        configuration.add(SymbolConstants.JAVASCRIPT_INFRASTRUCTURE_PROVIDER, "jquery");
    }
    
    

}
