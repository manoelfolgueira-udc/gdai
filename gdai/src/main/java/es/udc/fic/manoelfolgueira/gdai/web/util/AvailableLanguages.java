package es.udc.fic.manoelfolgueira.gdai.web.util;

import java.util.Map;

import es.udc.fic.manoelfolgueira.gdai.model.languageservice.LanguageService;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public class AvailableLanguages {

    private static Map<String, String> options;

    private static String names = "";

    private AvailableLanguages() {}

    public static void initialize(LanguageService languageService) throws InstanceNotFoundException {

    	options = languageService.getOptionsOrdered();
        names   = languageService.getNames();
        
    }

    public static String getNames() {
        return names;
    }

    public static String getOptions(String languageCode) {

        String languages = options.get(languageCode);
        
        if (languages != null) {
            return languages;
        } else {
            return options.get("en");
        }

    }

}
