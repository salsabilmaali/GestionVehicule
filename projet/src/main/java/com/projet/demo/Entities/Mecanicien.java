package com.projet.demo.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Mecanicien extends User{
	
	private int experience; 
	private String specialite;
	 @OneToMany(mappedBy = "mechanic", cascade = CascadeType.ALL)
	    private List<Vehicule> vehicles;
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	
}
