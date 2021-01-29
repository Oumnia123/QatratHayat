package com.sang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sang.model.Donneur;

@Repository
public interface DonneurRepository extends JpaRepository<Donneur, Integer> {

	public Donneur findByEmail(String email);
}
