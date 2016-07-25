package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Organization;
import com.jrtech.ffmp.data.entities.Role;
import com.jrtech.ffmp.data.repositories.OrganizationRepository;
import com.jrtech.ffmp.data.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Shawn on 15/12/13.
 */

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private OrganizationRepository orgRepository;

	/* (non-Javadoc)
	 * @see com.jrtech.templates.services.RoleService#findOne(java.lang.String)
	 */
	@Override
	public Role findOne(String id) {
		return roleRepository.findOne(id);
	}

	/* (non-Javadoc)
	 * @see com.jrtech.templates.services.RoleService#save(com.jrtech.templates.domain.Role)
	 */
	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	/* (non-Javadoc)
	 * @see com.jrtech.templates.services.RoleService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		Role role = roleRepository.findOne(id);
		
		Organization org = role.getOrganization();
		if (null != org) {
			org.getRoles().remove(role);
			orgRepository.save(org);
		} else {
			roleRepository.delete(id);
		}
	}

	@Override
	public Role findOneByName(String name) {
		return roleRepository.findOneByName(name);
	}

	/* (non-Javadoc)
	 * @see com.jrtech.templates.services.RoleService#isDuplicateNameOnSameLevel(com.jrtech.templates.domain.Role)
	 */
	@Override
	public boolean isDuplicateNameOnSameLevel(Role role) {
		if (null == role.getOrganization()) {
			return false;
		}
		Organization load = orgRepository.findOne(role.getOrganization().getId());
		boolean hasDupName = false;
		for (Role child : load.getRoles()) {
			if (child.getName().equals(role.getName())) {
				hasDupName = true;
				break;
			}
		}
		return hasDupName;
	}

}
