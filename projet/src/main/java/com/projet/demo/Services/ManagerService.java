package com.projet.demo.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projet.demo.Entities.Fournisseur;
import com.projet.demo.Entities.Mecanicien;
import com.projet.demo.Entities.SaleTransaction;
import com.projet.demo.Entities.Vehicule;
import com.projet.demo.Entities.VehiculeStatus;
import com.projet.demo.Exceptions.EmptyListException;
import com.projet.demo.Exceptions.EntityNotFoundException;
import com.projet.demo.Repositories.MecanicienRepository;
import com.projet.demo.Repositories.SaleTransactionRepository;
import com.projet.demo.Repositories.VehiculeRepository;
import com.projet.demo.Repositories.FournisseurRepository;

@Service
public class ManagerService implements IManagerService {

	private VehiculeRepository vehiculeRepository;
	private SaleTransactionRepository saleTransactionRepository; 
	private MecanicienRepository mecanicienRepository;
	private IVisiteTechniqueService iVisiteTechniqueService;
	private FournisseurRepository fournisseurRepository;
	public ManagerService(VehiculeRepository vehiculeRepository,SaleTransactionRepository saleTransactionRepository,
							MecanicienRepository mecanicienRepository,IVisiteTechniqueService iVisiteTechniqueService,
							FournisseurRepository fournisseurRepository)
	{
		this.saleTransactionRepository=saleTransactionRepository;
		this.vehiculeRepository=vehiculeRepository;
		this.mecanicienRepository=mecanicienRepository;
		this.iVisiteTechniqueService=iVisiteTechniqueService;
		this.fournisseurRepository=fournisseurRepository;
	}
	
	@Override
	public boolean approuvedSale(int saleTransactionId) {
		
		try {
			SaleTransaction  sale=this.saleTransactionRepository.findById(saleTransactionId)
					.orElseThrow(()->new EntityNotFoundException("transaction introuvable avec ce id:"+saleTransactionId));
			
			if(sale.getStatus().equals("En_Attente"))
			{
				Vehicule vehicule = sale.getVehicle();	
				vehicule.setStatus(VehiculeStatus.Sold);
				sale.setStatus("confirme");
				this.saleTransactionRepository.save(sale);
	            this.vehiculeRepository.save(vehicule);

				return true;
			}
			else
				throw new IllegalStateException("La transaction est déjà"+sale.getStatus()+"et ne peut pas être approuvée.");
		
		}
		catch(EntityNotFoundException e) {
			//exception l transaction mch mawjouda
			System.out.println("Erreur : " + e.getMessage());
		}
		catch (IllegalStateException e) {
			//exception transaction eli deja confirmées
			System.out.println("Erreur : " + e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean canceledSale(int saleTransactionId) {
		
		try {
			SaleTransaction sale=this.saleTransactionRepository.findById(saleTransactionId)
					.orElseThrow(()-> new EntityNotFoundException("transaction introuvable"));
			if(sale.getStatus().equals("En_Attente"))
			{
				sale.setStatus("annuler");
				this.saleTransactionRepository.save(sale);
				System.out.println("upsdate avec succes");
				return true;
			}
			else 
				throw new IllegalStateException("La transaction est déjà \" + sale.getStatus() + \" et ne peut pas être approuvée.");
		}

		catch(EntityNotFoundException e) {
			//exception l transaction mch mawjouda
			System.out.println("Erreur : " + e.getMessage());
		}
		catch (IllegalStateException e) {
			//exception transaction eli deja confirmées
			System.out.println("Erreur : " + e.getMessage());
		}
		
		return false;
	}
	@Override
	public List<SaleTransaction> getAllSalesTransaction(){
		try {
			List <SaleTransaction> SaleTransactions=this.saleTransactionRepository.findAll();
			if(SaleTransactions.isEmpty())
				throw new Exception("entity saleTransaction is empty");
			return SaleTransactions;
		}
		catch(Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
		return null;
	}
	
	@Override
	public double generateTotalRevenue() {
		try {
			List<SaleTransaction> transactions=saleTransactionRepository.findAll();
		   
			if(transactions.isEmpty())
				 throw new EmptyListException("empty entity");
			double totalRevenue=0;
		    for (SaleTransaction transaction : transactions) {
		        totalRevenue+=transaction.getSalePrice();
		    }
		    return totalRevenue;
		}
		
		catch(EmptyListException e)
		{
			System.out.println("Erreur : " + e.getMessage());

		}
		catch(Exception e) {
			System.out.println("Erreur : " + e.getMessage());

		}return 0;
	    
	}
	
	
	@Override
	public long getTotalSalesCount() {
	    return saleTransactionRepository.count();
	}
	
	
	 @Override
	 public String updateVehicleStatusToMaintenance(int vehicleId, int mechanicId) {
	     try {
	    	 	Vehicule vehicle=this.vehiculeRepository.findById(vehicleId).orElseThrow(()->new EntityNotFoundException("vehicule introuvabel avec id:"+vehicleId));
		        Mecanicien mechanic=this.mecanicienRepository.findById(mechanicId).orElseThrow(()->new EntityNotFoundException("mecanicien introuvabel avec id:"+vehicleId));
		       if (vehicle.getLastMaintenanceDate().isBefore(LocalDate.now().minusMonths(6)) && vehicle.getStatus()==VehiculeStatus.In_Stock) {
		                vehicle.setStatus(VehiculeStatus.Under_Maintenance);
		                vehicle.setMechanic(mechanic);
		                
		                vehiculeRepository.save(vehicle);
		                return "Vehicle status updated to maintenance and mechanic assigned.";
		        }
		       	else 
		                return "Vehicle maintenance is up to date or vendus.";
		            
		        }
	     catch(EntityNotFoundException e) {
				System.out.println("Erreur : " + e.getMessage());		
	     }
	     
	     catch(Exception e)
	     {
				System.out.println("Erreur : " + e.getMessage());
	     }
	     return "";
		 	
	    }
	 
	 @Override
	 public void verifierVisiteTechnique() {
		 try {
			 List<Vehicule> vehicules=this.vehiculeRepository.findAll();
			 if(vehicules.isEmpty())
				 throw new EmptyListException("empty entity");
			 for(Vehicule vehicule : vehicules) {
				 if(!(vehicule.getStatus()==VehiculeStatus.Sold) && vehicule.getDateExpiration().isBefore(LocalDate.now())) {
					 boolean estvalide=ControleTechnique(vehicule);
					 this.iVisiteTechniqueService.ajouterVisiteTechnique(vehicule, estvalide);
				 }
			 }
		 }
		 catch(Exception e) {
				System.out.println("Erreur : " + e.getMessage());
		 }
	 }
	 
	 public boolean ControleTechnique(Vehicule vehicule) {
		 return Math.random()>0.3;//70% chance bch tji validé
	 }

	 @Override
	 public Vehicule acheterVehicule(int vehiculeId, int idFournisseur) {
		try {
			 Fournisseur fournisseur= this.fournisseurRepository.findById(idFournisseur)
					 .orElseThrow(()->new EntityNotFoundException("Fournisseur introuvable!"));
			 Vehicule vehicule=this.vehiculeRepository.findById(vehiculeId) .orElseThrow(()->new EntityNotFoundException("vehicule introuvable!"));
			 if(vehicule.getStatus()==VehiculeStatus.Available)
			 { 
				 vehicule.setFournisseur(fournisseur);
				 vehicule.setStatus(VehiculeStatus.In_Stock);
				 vehicule.setPurchaseDate(LocalDate.now());
			 }
			 else 
				 throw new IllegalStateException("vehicule not available to buy");
			 return this.vehiculeRepository.save(vehicule);
		}
		catch(EntityNotFoundException e) {
			System.out.println("Erreur : " + e.getMessage());
		}
		catch(IllegalStateException e) {
			System.out.println("Erreur : " + e.getMessage());
		}
		catch(Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		} 
		return null;	 
	 }
	 
	 

}
