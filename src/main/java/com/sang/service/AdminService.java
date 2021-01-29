package com.sang.service;


import java.util.List;

import org.springframework.data.repository.query.Param;

import com.sang.model.Admin;


public interface AdminService {

	public void saveUser(Admin admin);
	
	public boolean isUserAlreadyPresent(Admin admin);
	
	public void updateNameAndLastName(String username, String nom, String prenom);
	
	//public void updatePwd(String username,String password);
}
