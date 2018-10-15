package es.udc.fic.manoelfolgueira.gdai.model.languageservice;

import java.util.Calendar;

public class LanguageDetails {

	private String languageName;
	private String languageCountry;
	private String options;
	private Calendar creationDate = Calendar.getInstance();

	public LanguageDetails() {
	}
	
	public LanguageDetails(String languageName, String languageCountry, String options,
			Calendar creationDate) {

		this.languageName = languageName;
		this.languageCountry = languageCountry;
		this.options = options;
		this.creationDate = creationDate;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getLanguageCountry() {
		return languageCountry;
	}

	public void setLanguageCountry(String languageCountry) {
		this.languageCountry = languageCountry;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}
	
}
