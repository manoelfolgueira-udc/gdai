package es.udc.fic.manoelfolgueira.gdai.model.languageservice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.fic.manoelfolgueira.gdai.model.language.Language;
import es.udc.fic.manoelfolgueira.gdai.model.language.LanguageDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;
import es.udc.fic.manoelfolgueira.gdai.web.util.AvailableLanguages;

@Service("languageService")
@Transactional
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageDao languageDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, String> getOptionsOrdered() throws InstanceNotFoundException {
		List<Language> languages = languageDao.findAll();

		Map<String, String> options = new HashMap<String, String>();

		for (Language l : languages) {

			String dbOptions = l.getOptions();

			dbOptions = dbOptions.replaceAll("\\s+", "");
			String[] currentOptions = dbOptions.split(",");

			Arrays.sort(currentOptions, new Comparator<String>() {
				public int compare(String str1, String str2) {
					String substr1 = str1.split("=")[1];
					String substr2 = str2.split("=")[1];

					return substr2.compareTo(substr1) * -1;
				}
			});

			String sortedOptions = "";

			for (String op : currentOptions) {
				sortedOptions += op + ",";
			}

			sortedOptions.substring(0, sortedOptions.length() - 1);

			options.put(l.getLanguageName(), sortedOptions);
		}

		return options;

	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = true)
	@Override
	public String getNames() throws InstanceNotFoundException {
		List<String> languageNames = languageDao.findAllNames();

		if (languageNames.isEmpty()) {
			throw new InstanceNotFoundException(null, Language.class.getName());
		}

		String lns = "";
		for (String l : languageNames) {

			lns += l + ",";
		}

		return lns.substring(0, lns.length() - 1);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reload() throws InstanceNotFoundException {
		AvailableLanguages.reload();
	}

}
