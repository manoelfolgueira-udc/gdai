package es.udc.fic.manoelfolgueira.gdai.model.languageservice;

import java.util.Calendar;

public class LanguageDetails {

	private String languageName;
	private String languageCountry;
	private String options;
	private Calendar creationTime = Calendar.getInstance();

	public LanguageDetails() {
	}
	
	public LanguageDetails(String languageName, String languageCountry, String options,
			Calendar creationTime) {

		this.languageName = languageName;
		this.languageCountry = languageCountry;
		this.options = options;
		this.creationTime = creationTime;
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

	public Calendar getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Calendar creationTime) {
		this.creationTime = creationTime;
	}
	
}
