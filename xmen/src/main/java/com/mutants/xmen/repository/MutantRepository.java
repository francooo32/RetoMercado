package com.mutants.xmen.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutants.xmen.model.Adn;

@Repository
public interface MutantRepository extends JpaRepository<Adn, Integer> {
	
	public abstract ArrayList<Adn> findBySecuencia(String secuencia);
	
}
