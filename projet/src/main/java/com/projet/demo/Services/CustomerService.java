package com.projet.demo.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projet.demo.Entities.Customer;
import com.projet.demo.Entities.SaleTransaction;
import com.projet.demo.Entities.Vehicule;
import com.projet.demo.Entities.VehiculeStatus;
import com.projet.demo.Exceptions.EntityNotFoundException;
import com.projet.demo.Exceptions.InvalidOperationException;
import com.projet.demo.Repositories.CustomerRepository;
import com.projet.demo.Repositories.SaleTransactionRepository;
import com.projet.demo.Repositories.VehiculeRepository;

@Service
public class CustomerService implements ICustomerService{

	private VehiculeRepository vehiculeRepository;
	private SaleTransactionRepository saleTransactionRepository;
	private CustomerRepository customerRepository;
	
	public CustomerService(VehiculeRepository vehiculeRepository, SaleTransactionRepository saleTransactionRepository,CustomerRepository customerRepository) {
		this.vehiculeRepository=vehiculeRepository;
		this.saleTransactionRepository=saleTransactionRepository;
		this.customerRepository=customerRepository;
	}
	@Override
	public boolean buyVehicule(int vehiculeId, int customerId) throws EntityNotFoundException {
		try {
			Vehicule vehicule=this.vehiculeRepository.findById(vehiculeId)
					.orElseThrow(()->new EntityNotFoundException ("vehicule non trouvable avec id :"+vehiculeId));
			if(vehicule.getStatus()!=VehiculeStatus.In_Stock) {
				System.out.println(vehicule.getStatus());
				throw new InvalidOperationException("ce vehicule nest pas disponible a la vente");
			}
			Customer customer=customerRepository.findById(customerId).orElseThrow(()->new EntityNotFoundException ("Customer not found"));
			//vehicule.setStatus(VehiculeStatus.Sold);
			vehicule.setDateSoldByCustomer(LocalDate.now());
			
			SaleTransaction transaction=new SaleTransaction();
			transaction.setVehicle(vehicule);
			transaction.setSaleDate(LocalDate.now());
			transaction.setCustomer(customer);
			transaction.setStatus("En_Attente");
			transaction.setSalePrice(vehicule.getPrice());
			
			this.vehiculeRepository.save(vehicule);
			this.saleTransactionRepository.save(transaction);
			
			return true;
		}
		catch (EntityNotFoundException|InvalidOperationException e) {
	        System.err.println("Erreur: " + e.getMessage());
	        return false;
	    } catch (Exception e) {
	        System.err.println("Erreur inattendue: " + e.getMessage());
	        return false;
	    }	
	}
	 
	   
	   
	   
	@Override
	public List<Vehicule> searchVehicles(String model, Double minPrice, Double maxPrice) {
	    try {
	        List<Vehicule> vehicules=this.vehiculeRepository.findByStatus(VehiculeStatus.In_Stock);

	        if (vehicules.isEmpty()) {
	            throw new IllegalStateException("Aucun véhicule trouvé en stock");
	        }

	        List<Vehicule> filteredVehicule = new ArrayList<>();

	        for (Vehicule v : vehicules) {
	            boolean isValid = true;

	            if (model != null && !model.isEmpty() && !v.getModel().toLowerCase().contains(model.toLowerCase())) {
	                isValid = false;
	            }

	            if (maxPrice != null && v.getPrice() > maxPrice) {
	                isValid = false;
	            }
	            if (minPrice != null && v.getPrice() < minPrice) {
	                isValid = false;
	            }

	            if (isValid) {
	                filteredVehicule.add(v);
	            }
	        }

	        return filteredVehicule;

	    } catch (Exception e) {
	        System.err.println("Une erreur inattendue s'est produite : " + e.getMessage());
	        throw new RuntimeException("Une erreur est survenue lors de la recherche des véhicules.", e);
	    }
	}

}
