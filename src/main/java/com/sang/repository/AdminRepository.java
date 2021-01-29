package com.sang.repository;




import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sang.model.Admin;



@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	public Admin findByUsername(String username);
	
	
	@Modifying
	@Transactional
	@Query("update Admin a set a.nom = :nom, a.prenom= :prenom where a.username = :username")
	public void updateNameAndLastName(@Param(value = "username") String username, @Param(value = "nom") String nom, @Param(value = "prenom") String prenom);
	
	
	
	@Modifying
	@Transactional
	@Query("update Admin a set a.image = :image where a.username = :username ")
	public void updateImage(@Param(value = "username") String username, @Param(value = "image") String image);
	
	
	
	@Modifying
	@Transactional
	@Query("update Admin a set a.password = :password where a.username = :username")
	public void updatePwd(@Param(value = "username") String username, @Param(value = "password") String password);
	
	
	@Query("select a.image from Admin a where a.username = :username")
	public String selectImage(@Param(value = "username") String username);
	
	
}