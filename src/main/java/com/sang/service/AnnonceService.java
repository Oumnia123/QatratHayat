package com.sang.service;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
public class AnnonceService {
	
	@Autowired
	private AnnonceRepository repo;
	
	public Page<Annonce> listAll(int pageNumber){
		Pageable p = PageRequest.of(pageNumber - 1 ,6);
		return repo.findAllByIdAnnonce(p);


	

}
	public Page<Annonce> listAllWIthQuery(int pageNumber, String grp_s){
		Pageable p = PageRequest.of(pageNumber - 1 ,6);
		return repo.selectAllAnnonce(grp_s, p);

}
}
