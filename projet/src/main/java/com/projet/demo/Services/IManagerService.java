package com.projet.demo.Services;

import java.time.LocalDate;
import java.util.List;

import com.projet.demo.Entities.SaleTransaction;
import com.projet.demo.Entities.Vehicule;

public interface IManagerService{

	boolean approuvedSale(int saleTransactionId);
	boolean canceledSale(int saleTransactionId);
	List<SaleTransaction> getAllSalesTransaction();
	double generateTotalRevenue();
	long getTotalSalesCount();
	String updateVehicleStatusToMaintenance(int vehicleId, int mechanicId);
	void verifierVisiteTechnique();
	Vehicule acheterVehicule(int vehiculeId, int idFournisseur);


}
