package com.projet.demo.Services;

import java.time.LocalDate;
import java.util.List;

import com.projet.demo.Entities.Mecanicien;
import com.projet.demo.Entities.Vehicule;
import com.projet.demo.Entities.VehiculeStatus;
import com.projet.demo.Exceptions.EntityNotFoundException;

public interface IVehiculeService {

	List<Vehicule> getAllVehicules();
	Vehicule getVehiculeById(int idVehicule);
	public void deleteVehicule(int vehiculeId);
	List<Vehicule> viewAvailableVehicles() throws EntityNotFoundException;
	

	boolean addVehicule(String model, double price, LocalDate lastMaintenanceDate, LocalDate purchaseDate,
			LocalDate dateExpiration, String fournisseurName, VehiculeStatus status);
}
