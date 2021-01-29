package com.sang.service;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sang.model.Role;
import com.sang.model.Admin;

import com.sang.repository.RoleRepository;
import com.sang.repository.AdminRepository;


@Service
public class AdminServiceImp implements AdminService {
	
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	AdminRepository userRepository;

	@Override
	public void saveUser(Admin admin) {
		admin.setPassword(encoder.encode(admin.getPassword()));
		admin.setStatus("VERIFIED");
		Role userRole = roleRepository.findByRole("ADMIN");
		admin.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(admin);
		
	}
	

	@Override
	public boolean isUserAlreadyPresent(Admin admin) {
		boolean isUserAlreadyExists = false;
		Admin existingUser = userRepository.findByUsername(admin.getUsername());
		// If user is found in database, then then user already exists.
		if(existingUser != null){
			isUserAlreadyExists = true; 
		}
		return isUserAlreadyExists;
	}
	@Override
	public void updateNameAndLastName(String username, String nom, String prenom) {
		
	}
	
	/*@Override
	public void updatePwd(String username,String password) {
		
		
	}
	*/
	

}
