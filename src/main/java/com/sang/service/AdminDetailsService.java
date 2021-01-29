package com.sang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sang.model.Admin;
import com.sang.model.AdminPrincipal;
import com.sang.repository.AdminRepository;

@Service
public class AdminDetailsService implements UserDetailsService {

	@Autowired
	AdminRepository adminrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminrepo.findByUsername(username);
		
		if (admin == null) 
		{
			throw new UsernameNotFoundException("User Not Found");
		}
		return new AdminPrincipal(admin);
	}

}
