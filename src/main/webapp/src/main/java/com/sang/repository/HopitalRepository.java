package com.sang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sang.model.Hopital;

@Repository
public interface HopitalRepository extends JpaRepository<Hopital, Integer> {

	public Hopital findByEmail(String email);
}
