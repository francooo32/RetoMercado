package com.mutants.xmen.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mutants.xmen.model.Adn;

@Service
public interface MutantServices {
	
	
	Adn saveSecuence(Adn secuencia);
	
	Optional<Adn> getSecuence(Integer idSecuence);
	
	Adn secuenceMaj(Integer id, Adn secuencia);
	
	boolean deleteSecuence(Integer id);
	
	ArrayList<Adn> getAllSecuences();
	
	ArrayList<Adn> getBySecuence(String secuencia);
	
	boolean findMutants(List<String> secuences);
	

}
