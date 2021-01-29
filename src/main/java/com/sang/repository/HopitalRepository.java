package com.sang.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sang.model.Hopital;

@Repository
public interface HopitalRepository extends JpaRepository<Hopital, Integer> {

	public Hopital findByUsername(String username);
	
	
	
	@Modifying
	@Transactional
	@Query("update Hopital a set a.nom_hopital = :nom_hopital, a.n_tele= :n_tele, a.lieu_hopital= :lieu_hopital where a.username = :username")
	public void updateInoHop(@Param(value = "username") String username, @Param(value = "nom_hopital") String nom_hopital, @Param(value = "n_tele") String n_tele, @Param(value = "lieu_hopital") String lieu_hopital);
	
	
	@Modifying
	@Transactional
	@Query("update Hopital a set a.image = :image where a.username = :username ")
	public void updateImage(@Param(value = "username") String username, @Param(value = "image") String image);
	
	
	@Query("select a.nom_hopital from Hopital a where a.username = :username")
	public String selectnom(@Param(value = "username") String username);
	
	@Query("select a.n_tele from Hopital a where a.username = :username")
	public String selecttele(@Param(value = "username") String username);
	
	@Query("select a.lieu_hopital from Hopital a where a.username = :username")
	public String selectlieu(@Param(value = "username") String username);
	
	
	
	@Modifying
	@Transactional
	@Query("update Hopital a set a.password = :password where a.username = :username")
	public void updateHPwd(@Param(value = "username") String username, @Param(value = "password") String password);
	
	
	@Query("select a.image from Hopital a where a.username = :username")
	public String selectImage(@Param(value = "username") String username);
}
