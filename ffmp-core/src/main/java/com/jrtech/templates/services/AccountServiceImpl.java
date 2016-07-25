package com.jrtech.templates.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

import com.jrtech.ffmp.data.entities.Account;
import com.jrtech.ffmp.data.entities.GrantedAuthorityImpl;
import com.jrtech.ffmp.data.entities.Organization;
import com.jrtech.ffmp.data.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepository accountRepo;

	@Override
	public Page<Account> findAll(Pageable pageable) {
		return accountRepo.findAll(pageable);
	}

	@Override
	public Account findOneByName(String name) {
		return accountRepo.findOneByName(name);
	}

	@Override
	public Account save(Account account) {
		return accountRepo.save(account);
	}

	@Override
	public Account findOne(String id) {
		return accountRepo.findOne(id);
	}

	@Override
	public void delete(String id) {
		Account account = accountRepo.findOne(id);
		account.getRoles().clear();
		Account saved = accountRepo.save(account);
		accountRepo.delete(saved);
	}

	@Override
	public Collection<GrantedAuthorityImpl> getAuthorities(String accountName) {
		Account account = findOneByName(accountName);

		Collection<GrantedAuthorityImpl> authorities = new TreeSet<>();
		account.getRoles().forEach(role -> {
			role.getAuthorities().forEach(authority -> {
				authorities.add(authority);
			});
		});

		return authorities;
	}

	@Override
	public Collection<Organization> getOrganizations(String accountName) {
		Account account = findOneByName(accountName);

		Collection<Organization> orgs = new ArrayList<>();
		account.getRoles().forEach(role -> {
			orgs.add(role.getOrganization());
		});

		return orgs;
	}

	@Override
	public Page<Account> findByNameContaining(String name, Pageable pageable) {
		return accountRepo.findByNameContaining(name, pageable);
	}

}
