package com.sang.service;


import com.sang.model.Hopital;

public interface HopitalService {

	public void saveUser(Hopital hopital);
	
	public boolean isUserAlreadyPresent(Hopital hopital);
}
