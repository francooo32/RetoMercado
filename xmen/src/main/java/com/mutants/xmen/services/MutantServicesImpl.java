package com.mutants.xmen.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mutants.xmen.model.Adn;
import com.mutants.xmen.model.AdnCode;
import com.mutants.xmen.repository.MutantRepository;

import lombok.AllArgsConstructor;

	@Service
	@AllArgsConstructor
	public class MutantServicesImpl implements MutantServices{
		
		private final MutantRepository mutantRepo;

		@Override
		public Adn saveSecuence(Adn secuences) {
			return mutantRepo.save(secuences);
		}

		@Override
		public Optional<Adn> getSecuence(Integer idSecuence) {
			
			Optional<Adn> opSecuence = mutantRepo.findById(idSecuence);
			
			return opSecuence;
		}

		@Override
		public Adn secuenceMaj(Integer id, Adn secuenciaMaj) {
			
			Adn secuenceToMaj = mutantRepo.findById(id).get();
			secuenceToMaj.setSecuencia(secuenciaMaj.getSecuencia());
			mutantRepo.save(secuenceToMaj);
			return null;
		}

		@Override
		public boolean deleteSecuence(Integer id) {
			
			mutantRepo.deleteById(id);
				return true;
			
		}

		@Override
		public ArrayList<Adn> getAllSecuences() {
			return (ArrayList<Adn>) mutantRepo.findAll();
		}
		
		public ArrayList<Adn> getBySecuence(String secuencia) {
			return mutantRepo.findBySecuencia(secuencia);
		}
		
		public boolean findMutants(List<String> secuences) {
			
			AdnCode code = new AdnCode();
			
			ArrayList<String> secuenceValidated = new ArrayList<String>();
			
			
			for(String secuencia : secuences){
				
				ArrayList<Long> secuenceList = new ArrayList<Long>();
				
				code.setCodeA(secuencia.chars().filter(x -> x == 'A').count());
				secuenceList.add(code.getCodeA());
				
				code.setCodeT(secuencia.chars().filter(x -> x == 'T').count());
				secuenceList.add(code.getCodeT());
				
				code.setCodeC(secuencia.chars().filter(x -> x == 'C').count());
				secuenceList.add(code.getCodeC());
				
				code.setCodeG(secuencia.chars().filter(x -> x == 'G').count());
				secuenceList.add(code.getCodeG());
				
				if(validateSecuence(secuenceList)) {
					
					secuenceValidated.add(secuencia);
				}
				
				if(secuenceValidated.size() >= 2) {
					
					return true;
				}
			}
			
			return false;
			
			
		}
		
		public boolean validateSecuence(ArrayList<Long> secuenceList) {
			
			for(Long secuenceCount : secuenceList) {
				
				if(secuenceCount >= 4) {
					
					return true;
				}
				
			}
			
			return false;
		}

	}


