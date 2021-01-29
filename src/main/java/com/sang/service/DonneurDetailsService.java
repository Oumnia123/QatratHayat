package com.sang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sang.model.Donneur;
import com.sang.model.DonneurPrincipal;
import com.sang.repository.DonneurRepository;

@Service
public class DonneurDetailsService implements UserDetailsService {

	@Autowired
	DonneurRepository donneurrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Donneur donneur = donneurrepo.findByUsername(username);
		
		if (donneur == null) 
		{
			throw new UsernameNotFoundException("User Not Found");
		}
		return new DonneurPrincipal(donneur);
	}

}
