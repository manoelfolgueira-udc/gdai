package es.udc.fic.manoelfolgueira.gdai.model.system;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="gdai_system")
public class System {

	@Column(name = "systemId")
	@SequenceGenerator( // It only takes effect for
		name = "systemIdGenerator", // databases providing identifier
		sequenceName = "systemSeq")
	// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "systemIdGenerator")
	private Long systemId;
	private String systemName;
	private String systemDescription;
	private Calendar creationDate = Calendar.getInstance();	

	public System() {
	}
	
	public System(String systemName, String systemDescription) {
		super();
		this.systemName = systemName;
		this.systemDescription = systemDescription;
	}

	public Long getSystemId() {
		return systemId;
	}


	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}


	public String getSystemName() {
		return systemName;
	}


	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}


	public String getSystemDescription() {
		return systemDescription;
	}


	public void setSystemDescription(String systemDescription) {
		this.systemDescription = systemDescription;
	}


	public Calendar getCreationDate() {
		return creationDate;
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((systemId == null) ? 0 : systemId.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		System other = (System) obj;
		if (systemId == null) {
			if (other.systemId != null)
				return false;
		} else if (!systemId.equals(other.systemId))
			return false;
		return true;
	}

}
