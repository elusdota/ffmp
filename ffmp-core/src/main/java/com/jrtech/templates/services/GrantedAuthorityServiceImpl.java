package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.GrantedAuthorityImpl;
import com.jrtech.ffmp.data.repositories.GrantedAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;

/**
 * Created by Shawn on 2015/12/5.
 */
@Service
public class GrantedAuthorityServiceImpl implements GrantedAuthorityService {

    @Autowired
    private GrantedAuthorityRepository repository;

    /* (non-Javadoc)
	 * @see com.jrtech.templates.services.GrantedAuthorityService#findRoot()
	 */
    @Override
	public List<GrantedAuthorityImpl> findRoot() {
        List<GrantedAuthorityImpl> roots = repository.findRoot();

        return roots;
    }

    /* (non-Javadoc)
	 * @see com.jrtech.templates.services.GrantedAuthorityService#findOne(java.lang.String)
	 */
    @Override
	public GrantedAuthorityImpl findOne(String id) {
        return repository.findOne(id);
    }

    @Override
    public GrantedAuthorityImpl findOneByName(String name) {
        return repository.findOneByName(name);
    }

    /* (non-Javadoc)
	 * @see com.jrtech.templates.services.GrantedAuthorityService#save(com.jrtech.templates.domain.GrantedAuthorityImpl)
	 */
    @Override
	public GrantedAuthorityImpl save(GrantedAuthorityImpl permission) {
        return repository.save(permission);
    }

    /* (non-Javadoc)
	 * @see com.jrtech.templates.services.GrantedAuthorityService#delete(java.lang.String)
	 */
    @Override
	public void delete(String id) {
    	Assert.notNull(id);
    	
    	GrantedAuthorityImpl permission = repository.findOne(id);
    	GrantedAuthorityImpl parent = permission.getParent();
    	if (null != parent) {
    		parent.getChildren().remove(permission);
    		repository.save(parent);
    	} else {
            repository.delete(id);
        }
    }

    /* (non-Javadoc)
	 * @see com.jrtech.templates.services.GrantedAuthorityService#searchByName(java.lang.String)
	 */
    @Override
	public List<GrantedAuthorityImpl> searchByName(String keyword) {
        return repository.searchByName("%" + keyword + "%");
    }
}
