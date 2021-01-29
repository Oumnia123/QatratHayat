package com.sang.service;


import com.sang.model.Donneur;

public interface DonneurService {

	public void saveUser(Donneur donneur);
	
	public boolean isUserAlreadyPresent(Donneur donneur);
}
