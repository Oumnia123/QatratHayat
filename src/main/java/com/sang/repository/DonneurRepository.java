package com.sang.repository;

import java.sql.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sang.model.Donneur;

@Repository
public interface DonneurRepository extends JpaRepository<Donneur, Integer> {

	public Donneur findByUsername(String username);
	
	
	
	@Modifying
	@Transactional
	@Query("update Donneur a set a.first_name = :first_name, a.last_Name= :last_Name, a.grp_s= :grp_s,a.date_der_don= :date_der_don where a.username = :username")
	public void updateinfoD(@Param(value = "username") String username,
			@Param(value = "first_name") String first_name, @Param(value = "last_Name") String last_Name,
			@Param(value = "grp_s") String grp_s,@Param(value = "date_der_don") Date date_der_don);
	
	
	
	
	@Query("select a.first_name from Donneur a where a.username = :username")
	public String selectprenomD(@Param(value = "username") String username);
	
	@Query("select a.last_Name from Donneur a where a.username = :username")
	public String selectnomD(@Param(value = "username") String username);
	
	@Query("select a.grp_s from Donneur a where a.username = :username")
	public String selectGroupeD(@Param(value = "username") String username);
	
	@Query("select a.date_der_don from Donneur a where a.username = :username")
	public Date selectDateD(@Param(value = "username") String username);
	
	
	@Modifying
	@Transactional
	@Query("update Donneur a set a.image = :image where a.username = :username ")
	public void updateImageD(@Param(value = "username") String username, @Param(value = "image") String image);
	
	
	@Modifying
	@Transactional
	@Query("update Donneur a set a.password = :password where a.username = :username")
	public void updateDPwd(@Param(value = "username") String username, @Param(value = "password") String password);
	
	
	@Query("select a.image from Donneur a where a.username = :username")
	public String selectImage(@Param(value = "username") String username);
	
	@Query("select a.grp_s from Donneur a where a.username = :username")
	public String selectFrp(@Param(value = "username") String username);
	

}
