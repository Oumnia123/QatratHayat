package com.sang.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sang.model.Annonce;

@Repository
public interface AnnonceRepository extends CrudRepository<Annonce, Integer> {

	
}
