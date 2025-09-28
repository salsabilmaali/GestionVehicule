package com.projet.demo.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.demo.Entities.Vehicule;
import com.projet.demo.Exceptions.EntityNotFoundException;
import com.projet.demo.Services.ICustomerService;

@RestController
public class CustomerController {

	private ICustomerService iCustomerService;
	public CustomerController(ICustomerService iCustomerService) {
		this.iCustomerService= iCustomerService;
	}

    @PostMapping("/buy/{vehiculeId}/{customerId}")
    public ResponseEntity<String> AcheterVehicule(@PathVariable int vehiculeId, @PathVariable int customerId) {
        try {
            boolean result=this.iCustomerService.buyVehicule(vehiculeId, customerId);
            if (result) {
                return new ResponseEntity<>("Vehicle purchased successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to purchase vehicle.", HttpStatus.BAD_REQUEST);//400 mochkla f request
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while purchasing the vehicle.", HttpStatus.INTERNAL_SERVER_ERROR);//500 mochkla f server side(backend)
        }
    }

		
	@GetMapping("/searchVehicles")
	  public ResponseEntity<List<Vehicule>> searchVehicles(@RequestParam(value="model", required=false) String model,
              @RequestParam(value="minPrice", required=false) Double minPrice,
              @RequestParam(value="maxPrice", required=false) Double maxPrice) {
					try {
						List<Vehicule> vehicles = this.iCustomerService.searchVehicles(model, minPrice, maxPrice);
						if (vehicles.isEmpty()) {
								return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 ken ma famch vehicules l search eli hajtk bih
					}
					return new ResponseEntity<>(vehicles, HttpStatus.OK);
					} catch (Exception e) {
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
					}
	}//tethat fl vehicule
	
}
