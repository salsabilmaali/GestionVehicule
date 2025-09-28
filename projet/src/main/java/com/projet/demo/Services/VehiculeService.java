package com.projet.demo.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.projet.demo.Entities.Fournisseur;
import com.projet.demo.Entities.Mecanicien;
import com.projet.demo.Entities.SaleTransaction;
import com.projet.demo.Entities.Vehicule;
import com.projet.demo.Entities.VehiculeStatus;
import com.projet.demo.Exceptions.EntityNotFoundException;
import com.projet.demo.Repositories.FournisseurRepository;
import com.projet.demo.Repositories.SaleTransactionRepository;
import com.projet.demo.Repositories.VehiculeRepository;
@Service
public class VehiculeService implements IVehiculeService{
	private VehiculeRepository vehiculeRepository;
	private SaleTransactionRepository saleTransactionRepository; 
	private FournisseurRepository fournisseurRepository; 
	

	public VehiculeService(VehiculeRepository vehiculeRepository,SaleTransactionRepository saleTransactionRepository,FournisseurRepository fournisseurRepository)
	{
		this.saleTransactionRepository=saleTransactionRepository;
		this.vehiculeRepository=vehiculeRepository;
		this.fournisseurRepository= fournisseurRepository;
		
	}
	@Override
	public void deleteVehicule(int vehiculeId) {
	    Optional<Vehicule> vehiculeOpt=vehiculeRepository.findById(vehiculeId);
	    if (vehiculeOpt.isPresent()) {
	        Vehicule vehicule=vehiculeOpt.get();
	        
	        // Check if the vehicle is sold or in an active transaction
	        if (vehicule.getStatus() == VehiculeStatus.Sold) {
	            throw new IllegalStateException("Cannot delete a sold vehicle.");
	        }
	        
	        if (vehicule.getStatus() == VehiculeStatus.Under_Maintenance) {
	            throw new IllegalStateException("Cannot delete a vehicle under maintenance.");
	        }
	        
	        // If the vehicle is part of an active sale transaction, prevent deletion
	        for (SaleTransaction transaction : vehicule.getSaleTransactions()) {
	            if ("En_Attente".equals(transaction.getStatus())) {
	                throw new IllegalStateException("Cannot delete a vehicle that is part of an ongoing transaction.");
	            }
	        }
	        vehiculeRepository.delete(vehicule);
	    } else {
	        throw new EntityNotFoundException("Vehicle not found with id: " + vehiculeId);
	    }
	}
	
	
	@Override
	public Vehicule getVehiculeById(int vehiculeId) {
	    return vehiculeRepository.findById(vehiculeId)
	            .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id: " + vehiculeId));
	}
	@Override
	public List<Vehicule> getAllVehicules() {
	    List<Vehicule> vehicules = vehiculeRepository.findAll();
	    if (vehicules.isEmpty()) {
	        throw new EntityNotFoundException("No vehicles found.");
	    }
	    return vehicules;
	}
	
	  @Override
	   public List<Vehicule> viewAvailableVehicles() throws EntityNotFoundException{
		   List <Vehicule> vehicules=this.vehiculeRepository.findByStatus(VehiculeStatus.In_Stock);
		   if(vehicules.isEmpty())
			   throw new IllegalStateException("aucun vehicule disponible trouvé");
		   return vehicules;
				   
	   }
		@Override
	  public boolean addVehicule(String model, double price, LocalDate lastMaintenanceDate, LocalDate purchaseDate, 
              LocalDate dateExpiration, String fournisseurName, VehiculeStatus status) {
				try {
					Vehicule newVehicule = new Vehicule();
					newVehicule.setModel(model);
					newVehicule.setPrice(price);
					newVehicule.setLastMaintenanceDate(lastMaintenanceDate);
					newVehicule.setPurchaseDate(purchaseDate);
					newVehicule.setDateExpiration(dateExpiration);
					newVehicule.setStatus(status);
		
					
					Fournisseur fournisseur = fournisseurRepository.findByNom(fournisseurName)
					  .orElseThrow(() -> new EntityNotFoundException("Fournisseur not found with name: " + fournisseurName));
					
					newVehicule.setFournisseur(fournisseur);
					
					vehiculeRepository.save(newVehicule);
					
					return true;
				} catch (Exception e) {
					System.err.println("Error adding vehicle: " + e.getMessage());
					return false;
				}
				}
	  



}
