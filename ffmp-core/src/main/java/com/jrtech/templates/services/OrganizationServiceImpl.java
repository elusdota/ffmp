package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Organization;
import com.jrtech.ffmp.data.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	@Autowired
	private OrganizationRepository orgRepository;
	
	/* (non-Javadoc)
	 * @see com.jrtech.templates.services.OrganizationService#findOne(java.lang.String)
	 */
	@Override
	public Organization findOne(String id) {
		return orgRepository.findOne(id);
	}

	@Override
	public Organization findOneByName(String name) {
		return orgRepository.findOneByName(name);
	}

	/* (non-Javadoc)
	 * @see com.jrtech.templates.services.OrganizationService#save(com.jrtech.templates.domain.Organization)
	 */
	@Override
	public Organization save(Organization organization) {
		return orgRepository.save(organization);
	}
	
	/* (non-Javadoc)
	 * @see com.jrtech.templates.services.OrganizationService#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		Assert.hasText(id);

		Organization org = orgRepository.findOne(id);
		if (org == null) {
			return;
		}

		if(org.getRoles().size()>0){
			return;
//			System.out.println(org.getRoles().size()+"----------");
//			throw new ServiceException("请先删除该组织机构下属的角色！");
		}
		if (null != org.getParent()) {
			Organization parent = org.getParent();
			parent.getChildren().remove(org);
//			org.getRoles().clear();
//			orgRepository.save(org);
			orgRepository.save(parent);
		} else {
			orgRepository.delete(id);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.jrtech.templates.services.OrganizationService#isDuplicateNameOnSameLevel(com.jrtech.templates.domain.Organization)
	 */
	@Override
	public boolean isDuplicateNameOnSameLevel(Organization org) {
		if (null == org.getParent()) {
			return false;
		}
		Organization load = orgRepository.findOne(org.getParent().getId());
		boolean hasSameName = false;
		for (Organization child : load.getChildren()) {
			if (child.getName().equals(org.getName())) {
				hasSameName = true;
				break;
			}
		}
		
		return hasSameName;
	}

	@Override
	public List<Organization> findByTypeAndNameLike(int type, String name) {
		return orgRepository.findByTypeAndNameLike(type,name);
	}

	@Override
	public Organization findRoot() {
		// TODO Auto-generated method stub
		return orgRepository.findRoot();
	}
}
