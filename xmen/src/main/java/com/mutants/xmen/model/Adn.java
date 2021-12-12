package com.mutants.xmen.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "adn")
public class Adn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String type;

    @ElementCollection
    @Column(name="secuencia")
    private List<String> secuencia;
    
    @ElementCollection
    @Column(name="mutant_dna")
    private List<String> mutantDNA;
    
    @ElementCollection
    @Column(name="human_dna")
    private List<String> humanDNA;

    public Adn(Integer id, String type, List<String> secuencia, List<String> mutantDNA, List<String> humanDNA) {
        this.id = id;
        this.type = type;
        this.secuencia = secuencia;
        this.mutantDNA = mutantDNA;
        this.humanDNA = humanDNA;
    }
    
    public Adn(String type, List<String> secuencia, List<String> mutantDNA, List<String> humanDNA) {
    	this.type = type;
        this.secuencia = secuencia;
        this.mutantDNA = mutantDNA;
        this.humanDNA = humanDNA;
    }


    public Adn() {
    }


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getSecuencia() {
		return secuencia;
	}


	public void setSecuencia(List<String> secuencia) {
		this.secuencia = secuencia;
	}


	public List<String> getMutantDNA() {
		return mutantDNA;
	}


	public void setMutantDNA(List<String> mutantDNA) {
		this.mutantDNA = mutantDNA;
	}


	public List<String> getHumanDNA() {
		return humanDNA;
	}


	public void setHumanDNA(List<String> humanDNA) {
		this.humanDNA = humanDNA;
	}

    
    
}

