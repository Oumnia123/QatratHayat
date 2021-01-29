package com.sang.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sang.model.Annonce;
import com.sang.model.Campagne;

@Repository
public interface CampagneRepository extends PagingAndSortingRepository<Campagne, Integer> {

	@Query("SELECT a FROM Campagne a ORDER BY a.id_campagne DESC")
	public Page<Campagne> findAllByIdCamp(Pageable pageable);
}
