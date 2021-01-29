package com.sang.service;


import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sang.model.Role;

import com.sang.model.Hopital;
import com.sang.repository.RoleRepository;

import com.sang.repository.HopitalRepository;

@Service
public class HopitalServiceImp implements HopitalService {
	
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	HopitalRepository userRepository;
	

	@Override
	public void saveUser(Hopital hopital) {
		hopital.setPassword(encoder.encode(hopital.getPassword()));
		hopital.setStatus("VERIFIED");
		Role userRole = roleRepository.findByRole("HOPITAL");
		hopital.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(hopital);
		
	}

	@Override
	public boolean isUserAlreadyPresent(Hopital hopital) {
		boolean isUserAlreadyExists = false;
		Hopital existingUser = userRepository.findByEmail(hopital.getEmail());
		// If user is found in database, then then user already exists.
		if(existingUser != null){
			isUserAlreadyExists = true; 
		}
		return isUserAlreadyExists;
	}

}
