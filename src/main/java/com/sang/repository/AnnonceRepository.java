package com.sang.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sang.model.Annonce;



@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Integer> {
	
	@Query("select a from Annonce a where a.grp_s=:grp_s ")
	Page<Annonce> selectAllAnnonce(@Param(value = "grp_s") String grp_s, Pageable pageable);
	
	@Query("SELECT a FROM Annonce a ORDER BY a.id_annonce DESC")
	public Page<Annonce> findAllByIdAnnonce(Pageable pageable);
}
