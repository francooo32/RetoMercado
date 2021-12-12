package com.mutants.xmen.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mutants.xmen.model.Adn;
import com.mutants.xmen.model.AdnDB;
import com.mutants.xmen.services.MutantServices;

@RestController
@RequestMapping("/mutant")
public class MutantApiRestController {
	
	@Autowired
	MutantServices mutantSrv;
	
	@GetMapping()
	public ResponseEntity<ArrayList<Adn>> getAllWeathers(@RequestParam(name="secuencia", required=false) List<String> secuencia) {
		ArrayList<Adn> ListMutant = mutantSrv.getAllSecuences();
		ArrayList<Adn> ListWeatherToRemove = new ArrayList<>();
		
		if(secuencia != null) {
		}
		
		ListMutant.removeAll(ListWeatherToRemove);
		
		return new ResponseEntity<>(ListMutant, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Adn> saveSecuence(@RequestBody Adn secuencia) {
		boolean isMutant = false;
		String response = null;
		
		isMutant = mutantSrv.findMutants(secuencia.getSecuencia());
		
		if(isMutant) {
			response = "es mutante";
			secuencia.setType(response);
			secuencia.setMutantDNA(secuencia.getSecuencia());
			mutantSrv.saveSecuence(secuencia);
			return new ResponseEntity<Adn>(secuencia, HttpStatus.OK);
		}else {
			response = "no es mutante";
			secuencia.setType(response);
			secuencia.setHumanDNA(secuencia.getSecuencia());
			mutantSrv.saveSecuence(secuencia);
			return new ResponseEntity<Adn>(secuencia, HttpStatus.FORBIDDEN);
		}
		
		
	}
	
	@GetMapping( path = "/{id}")
	public ResponseEntity<Optional<Adn>> getSecuence(@PathVariable("id") Integer idSecuence) {
		return new ResponseEntity<>(mutantSrv.getSecuence(idSecuence), HttpStatus.OK);
	}
	
	@GetMapping("/query")
	public ResponseEntity<ArrayList<Adn>> findBySecuence(@RequestParam("secuencia") String secuencia){
		return new ResponseEntity<>(mutantSrv.getBySecuence(secuencia), HttpStatus.OK);
	}
	
	@GetMapping("/stats")
	public ResponseEntity<AdnDB> getStats(@RequestParam(name="secuencia", required=false) List<String> secuencia) {
		ArrayList<Adn> ListAdnFull = mutantSrv.getAllSecuences();
		ArrayList<Adn> ListMutant = new ArrayList<>();
		ArrayList<Adn> ListHuman = new ArrayList<>();
		
		AdnDB adnCount = new AdnDB();
		
		for(Adn AdnForCount : ListAdnFull) {
			
			if(!AdnForCount.getMutantDNA().isEmpty()) {
				ListMutant.add(AdnForCount);
			}else {
				ListHuman.add(AdnForCount);
			}
			
			adnCount.setCount_mutant_dna(ListMutant.size());
			
			adnCount.setCount_human_dna(ListHuman.size());
		}
		
		
		return new ResponseEntity<AdnDB>(adnCount, HttpStatus.OK);
	}
	
	@DeleteMapping( path = "/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
		boolean success = mutantSrv.deleteSecuence(id);
		
		if(success) {
			return new ResponseEntity<String>("Delete complete on id " + id, HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Delete failed on id " + id, HttpStatus.OK);
		}
		
	}
}

