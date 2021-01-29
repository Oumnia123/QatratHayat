package com.sang.service;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.print.attribute.standard.PageRanges;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.sang.repository.AnnonceRepository;
import com.sang.repository.CampagneRepository;
import com.sang.model.Annonce;
import com.sang.model.Campagne;


@Service
public class CampagneService {
	
	@Autowired
	private CampagneRepository repo;
	
	public Page<Campagne> listAll(int pageNumber){
		Pageable p = PageRequest.of(pageNumber - 1 ,6);
		return repo.findAllByIdCamp(p);
		
		
		
		
	}
	


	

}
