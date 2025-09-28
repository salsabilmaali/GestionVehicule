package com.projet.demo.Controllers;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.demo.Entities.Mecanicien;
import com.projet.demo.Entities.VehiculeStatus;
import com.projet.demo.Services.IVehiculeService;

@RestController
public class FournisseurController {

	private IVehiculeService IVehiculeService; 
	
	public FournisseurController(IVehiculeService IvehiculeService) {
		this.IVehiculeService=IvehiculeService;
	}
	
	  @PostMapping("/SaleVehiculeToManager")
	    public ResponseEntity<String> addVehicule(@RequestParam String model, 
	                                              @RequestParam double price, 
	                                              @RequestParam String lastMaintenanceDate,
	                                              @RequestParam String purchaseDate, 
	                                              @RequestParam String dateExpiration,
	                                              @RequestParam String fournisseurName, 
	                                              @RequestParam String status
	                                             ) {
	        try {
	            LocalDate lastMaintenance = LocalDate.parse(lastMaintenanceDate);
	            LocalDate purchase = LocalDate.parse(purchaseDate);
	            LocalDate expiration = LocalDate.parse(dateExpiration);
	            VehiculeStatus stat = VehiculeStatus.Available;

	            boolean isAdded = IVehiculeService.addVehicule(model, price, lastMaintenance, purchase, expiration, fournisseurName, stat);

	            if (isAdded) {
	                return ResponseEntity.ok("Vehicle added successfully.");
	            } else {
	                return ResponseEntity.status(400).body("Failed to add the vehicle.");
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error adding vehicle: " + e.getMessage());
	        }
	    }//nahi l fourniseeur ka acteur  
	  
}
