package com.projet.demo.Controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.demo.Entities.Mecanicien;
import com.projet.demo.Entities.Vehicule;
import com.projet.demo.Entities.VehiculeStatus;
import com.projet.demo.Services.IManagerService;
import com.projet.demo.Services.IVehiculeService;
@RestController
public class VehiculeController {

	private IVehiculeService iVehiculeService;
	private IManagerService iManagerService;
	public VehiculeController(IVehiculeService iVehiculeService) {
		this.iVehiculeService=iVehiculeService;
	}
	
	   @GetMapping("/allVehicules")
	    public ResponseEntity<List<Vehicule>> getAllVehicules() {
	        try {
	            List<Vehicule> vehicules=iVehiculeService.getAllVehicules();
	            return new ResponseEntity<>(vehicules, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        }
	    }
	   @GetMapping("getVehiculeId/{id}")
	    public ResponseEntity<Vehicule> getVehiculeById(@PathVariable("id") int vehiculeId) {
	        try {
	            Vehicule vehicule = iVehiculeService.getVehiculeById(vehiculeId);
	            return new ResponseEntity<>(vehicule, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        }
	    }
	   
	 
		@GetMapping("/getAllAvailableVehicles")
		 public ResponseEntity<List<Vehicule>> viewAvailableVehicles() {
	        List<Vehicule> vehicles=this.iVehiculeService.viewAvailableVehicles();
	        if (vehicles.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Returns 204 ken list fergha
	        }
	        return new ResponseEntity<>(vehicles, HttpStatus.OK);
	    }
		
		
}
