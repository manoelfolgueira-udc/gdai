package es.udc.fic.manoelfolgueira.gdai.model.group;

import es.udc.fic.manoelfolgueira.gdai.model.util.dao.GenericDao;
import es.udc.fic.manoelfolgueira.gdai.model.util.exceptions.InstanceNotFoundException;

public interface GroupDao extends GenericDao<Group, Long> {

    /**
     * Returns a Group by name
     *
     * @param name the group identifier
     * @return the Group
     */
    public Group findByName(String name) throws InstanceNotFoundException;
}
