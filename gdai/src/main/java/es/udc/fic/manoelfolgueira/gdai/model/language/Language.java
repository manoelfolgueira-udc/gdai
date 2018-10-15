package es.udc.fic.manoelfolgueira.gdai.model.language;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="gdai_language")
public class Language {

	@Column(name = "languageId")
	@SequenceGenerator( // It only takes effect for
		name = "languageIdGenerator", // databases providing identifier
		sequenceName = "languageSeq")
	// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "languageIdGenerator")
	private Long languageId;
	private String languageName;
	private String languageCountry;
	private String options;
	private Calendar creationDate = Calendar.getInstance();

	public Language() {
	}
	
	public Language(String languageName, String languageCountry, String options,
			Calendar creationDate) {

		this.languageName = languageName;
		this.languageCountry = languageCountry;
		this.options = options;
		this.creationDate = creationDate;
	}

	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
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
