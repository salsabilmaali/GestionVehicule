package com.projet.demo.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Fournisseur {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idFournisseur;
	private String nom;
	private String adresse;
	private String contact;
	@JsonIgnore
	@OneToMany(mappedBy="fournisseur")
	private List<Vehicule> vehiculesFournis;

	public int getIdFournisseur() {
		return idFournisseur;
	}

	public void setIdFournisseur(int idFournisseur) {
		this.idFournisseur = idFournisseur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<Vehicule> getVehiculesFournis() {
		return vehiculesFournis;
	}

	public void setVehiculesFournis(List<Vehicule> vehiculesFournis) {
		this.vehiculesFournis = vehiculesFournis;
	}
	
}
