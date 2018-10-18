package es.udc.fic.manoelfolgueira.gdai.model.system;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.fic.manoelfolgueira.gdai.model.group.Group;

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
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupId")
	private Group group;

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
		
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
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

	@Override
	public String toString() {
		return "System [systemId=" + systemId + ", systemName=" + systemName + ", systemDescription="
				+ systemDescription + ", creationDate=" + creationDate + "]";
	}

}
