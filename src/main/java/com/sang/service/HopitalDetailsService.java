package com.sang.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sang.model.Hopital;
import com.sang.model.HopitalPrincipal;
import com.sang.repository.HopitalRepository;

@Service
public class HopitalDetailsService implements UserDetailsService {

	@Autowired
	HopitalRepository hopitalrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Hopital hopital = hopitalrepo.findByUsername(username);
		
		if (hopital == null) 
		{
			throw new UsernameNotFoundException("User Not Found");
		}
		return new HopitalPrincipal(hopital);
	}

}
