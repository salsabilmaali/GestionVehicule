package com.projet.demo.Entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Vehicule {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private int idVehicule;
	private String model;
	private double price;
	private LocalDate lastMaintenanceDate;
	private LocalDate purchaseDate;
	private LocalDate dateSoldByCustomer; 
    @Enumerated(EnumType.STRING)  // Store as a string in the database
    private VehiculeStatus status;
    
    
	private LocalDate dateExpiration;
	
//	@JsonBackReference
	@ManyToOne
	
	@JsonIgnore
	@JoinColumn(name="id_Fournisseur")
	private Fournisseur fournisseur;
	
    
    @ManyToOne
	@JoinColumn(name = "mechanic_id") 
	 private Mecanicien mechanic;
	 
	@OneToMany(mappedBy = "vehicle")
	private List<SaleTransaction> saleTransactions;
	
	@OneToMany(mappedBy="vehicule", cascade=CascadeType.ALL)
	private List<VisiteTechnique> visiteTechnique;  

	public Fournisseur getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	public LocalDate getDateExpiration() {
		return dateExpiration;
	}
	public void setDateExpiration(LocalDate dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	public List<VisiteTechnique> getVisiteTechnique() {
		return visiteTechnique;
	}
	public void setVisiteTechnique(List<VisiteTechnique> visiteTechnique) {
		this.visiteTechnique = visiteTechnique;
	}
	public LocalDate getDateSoldByCustomer() {
		return dateSoldByCustomer;
	}
	public void setDateSoldByCustomer(LocalDate dateSoldByCustomer) {
		this.dateSoldByCustomer = dateSoldByCustomer;
	}

	public Mecanicien getMechanic() {
		return mechanic;
	}
	public void setMechanic(Mecanicien mechanic) {
		this.mechanic = mechanic;
	}
	public List<SaleTransaction> getSaleTransactions() {
		return saleTransactions;
	}
	public void setSaleTransactions(List<SaleTransaction> saleTransactions) {
		this.saleTransactions = saleTransactions;
	}
	public int getIdVehicule() {
		return idVehicule;
	}
	public void setIdVehicule(int idVehicule) {
		this.idVehicule = idVehicule;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getLastMaintenanceDate() {
		return lastMaintenanceDate;
	}
	public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
		this.lastMaintenanceDate = lastMaintenanceDate;
	}
	public VehiculeStatus getStatus() {
		return status;
	}
	public void setStatus(VehiculeStatus status) {
		this.status = status;
	}
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	
	
}
