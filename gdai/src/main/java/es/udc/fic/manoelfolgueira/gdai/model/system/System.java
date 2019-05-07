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
import es.udc.fic.manoelfolgueira.gdai.model.util.GDAICodificable;

@Entity
@Table(name="gdai_system")
public class System extends GDAICodificable {

	@Column(name = "systemId")
	@SequenceGenerator(name = "systemIdGenerator",sequenceName = "systemSeq")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "systemIdGenerator")
	private Long systemId;
	private String systemName;
	private String systemDescription;
	private Calendar creationDate = Calendar.getInstance();
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupId")
	private Group group;

	/**
	 * Empty constructor
	 */
	public System() {}
	
	/**
	 * Main constructor
	 * @param systemName a name for the system
	 * @param systemDescription a description for the system
	 * @param group the developer group which this system belongs to
	 */
	public System(String systemName, String systemDescription, Group group) {
		super();
		this.systemName = systemName;
		this.systemDescription = systemDescription;
		this.group = group;
	}

	/**
	 * @return the systemId
	 */
	public Long getSystemId() {
		return systemId;
	}

	/**
	 * @param systemId the systemId to set
	 */
	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}

	/**
	 * @return the systemName
	 */
	public String getSystemName() {
		return systemName;
	}

	/**
	 * @param systemName the systemName to set
	 */
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	/**
	 * @return the systemDescription
	 */
	public String getSystemDescription() {
		return systemDescription;
	}

	/**
	 * @param systemDescription the systemDescription to set
	 */
	public void setSystemDescription(String systemDescription) {
		this.systemDescription = systemDescription;
	}

	/**
	 * @return the creationDate
	 */
	public Calendar getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(Group group) {
		this.group = group;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((systemDescription == null) ? 0 : systemDescription.hashCode());
		result = prime * result + ((systemId == null) ? 0 : systemId.hashCode());
		result = prime * result + ((systemName == null) ? 0 : systemName.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		System other = (System) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (systemDescription == null) {
			if (other.systemDescription != null)
				return false;
		} else if (!systemDescription.equals(other.systemDescription))
			return false;
		if (systemId == null) {
			if (other.systemId != null)
				return false;
		} else if (!systemId.equals(other.systemId))
			return false;
		if (systemName == null) {
			if (other.systemName != null)
				return false;
		} else if (!systemName.equals(other.systemName))
			return false;
		return true;
	}

}
