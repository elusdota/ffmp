package com.jrtech.templates.services;

import java.util.Collection;
import java.util.HashSet;

import com.jrtech.ffmp.data.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jrtech.templates.services.AccountService;

/**
 * spring安全框架登录验证实现spring UserDetailsService接口，elus
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {
		System.out.println(name+"---------------------name");
		Account account = accountService.findOneByName(name);
		if (null == account) {
			throw new UsernameNotFoundException(name + " not found");
		}
		return buildUserFromAccount(account);
	}

	private User buildUserFromAccount(Account account) {
		Collection<GrantedAuthority> authorities = new HashSet<>();

		account.getRoles().forEach(role -> {
			authorities.addAll(role.getAuthorities());
		});
		return new User(account.getName(), account.getPassword(), authorities);
	}
}
