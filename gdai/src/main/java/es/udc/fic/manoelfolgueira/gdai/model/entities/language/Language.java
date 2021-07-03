package es.udc.fic.manoelfolgueira.gdai.model.entities.language;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "gdai_language")
public class Language {

	@Column(name = "languageId")
	@SequenceGenerator(name = "languageIdGenerator", sequenceName = "languageSeq")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "languageIdGenerator")
	private Long languageId;
	private String languageName;
	private String languageCountry;
	private String options;
	private Calendar creationDate = Calendar.getInstance();

	/**
	 * Empty constructor
	 */
	public Language() {
	}

	/**
	 * Main constructor
	 * 
	 * @param languageName
	 *            language name
	 * @param languageCountry
	 *            language country
	 * @param options
	 *            a language options string
	 * @see es.udc.fic.manoelfolgueira.gdai.model.services.languageservice for further
	 *      information
	 * @see es.udc.fic.manoelfolgueira.gdai.web.pages.configuration for further
	 *      information
	 * @param creationDate
	 *            when the language is registered in GDAI
	 */
	public Language(String languageName, String languageCountry, String options, Calendar creationDate) {
		this.languageName = languageName;
		this.languageCountry = languageCountry;
		this.options = options;
		this.creationDate = creationDate;
	}

	/**
	 * @return the languageId
	 */
	public Long getLanguageId() {
		return languageId;
	}

	/**
	 * @param languageId
	 *            the languageId to set
	 */
	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	/**
	 * @return the languageName
	 */
	public String getLanguageName() {
		return languageName;
	}

	/**
	 * @param languageName
	 *            the languageName to set
	 */
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	/**
	 * @return the languageCountry
	 */
	public String getLanguageCountry() {
		return languageCountry;
	}

	/**
	 * @param languageCountry
	 *            the languageCountry to set
	 */
	public void setLanguageCountry(String languageCountry) {
		this.languageCountry = languageCountry;
	}

	/**
	 * @return the options
	 */
	public String getOptions() {
		return options;
	}

	/**
	 * @param options
	 *            the options to set
	 */
	public void setOptions(String options) {
		this.options = options;
	}

	/**
	 * @return the creationDate
	 */
	public Calendar getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((languageCountry == null) ? 0 : languageCountry.hashCode());
		result = prime * result + ((languageId == null) ? 0 : languageId.hashCode());
		result = prime * result + ((languageName == null) ? 0 : languageName.hashCode());
		result = prime * result + ((options == null) ? 0 : options.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Language other = (Language) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (languageCountry == null) {
			if (other.languageCountry != null)
				return false;
		} else if (!languageCountry.equals(other.languageCountry))
			return false;
		if (languageId == null) {
			if (other.languageId != null)
				return false;
		} else if (!languageId.equals(other.languageId))
			return false;
		if (languageName == null) {
			if (other.languageName != null)
				return false;
		} else if (!languageName.equals(other.languageName))
			return false;
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
			return false;
		return true;
	}

}
