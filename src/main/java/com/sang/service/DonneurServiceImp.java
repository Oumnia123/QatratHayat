package com.sang.service;


import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sang.model.Role;
import com.sang.model.Donneur;
import com.sang.repository.RoleRepository;
import com.sang.repository.DonneurRepository;

@Service
public class DonneurServiceImp implements DonneurService {
	
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	DonneurRepository userRepository;

	@Override
	public void saveUser(Donneur donneur) {
		donneur.setPassword(encoder.encode(donneur.getPassword()));
		donneur.setStatus("VERIFIED");
		Role userRole = roleRepository.findByRole("DONNEUR");
		donneur.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(donneur);
		
	}

	@Override
	public boolean isUserAlreadyPresent(Donneur donneur) {
		boolean isUserAlreadyExists = false;
		Donneur existingUser = userRepository.findByUsername(donneur.getUsername());
		// If user is found in database, then then user already exists.
		if(existingUser != null){
			isUserAlreadyExists = true; 
		}
		return isUserAlreadyExists;
	}

}
