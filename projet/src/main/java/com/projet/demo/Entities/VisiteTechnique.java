package com.projet.demo.Entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class VisiteTechnique {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idVisite;
	private LocalDate dateVisite;// wa9tili visite effectué
	private LocalDate dateExpiration;// hata lin la visite  reste valide
	private String resultat;
	private double prix;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="idVehicule", nullable=false)
	private Vehicule vehicule;

	public int getIdVisite() {
		return idVisite;
	}

	public void setIdVisite(int idVisite) {
		this.idVisite = idVisite;
	}

	public LocalDate getDateVisite() {
		return dateVisite;
	}

	public void setDateVisite(LocalDate dateVisite) {
		this.dateVisite = dateVisite;
	}

	public LocalDate getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(LocalDate dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}
	
	
	
	
	
}
