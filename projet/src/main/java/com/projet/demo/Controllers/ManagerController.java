package com.projet.demo.Controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.demo.Entities.SaleTransaction;
import com.projet.demo.Entities.Vehicule;
import com.projet.demo.Services.IManagerService;
import com.projet.demo.Services.IVehiculeService;
@RestController
public class ManagerController {

	private IManagerService iManagerService;
	private IVehiculeService iVehiculeService;

	public ManagerController(IManagerService iManagerService , IVehiculeService iVehiculeService) {
		this.iManagerService=iManagerService;
		this.iVehiculeService=iVehiculeService;
	}
	
	@PutMapping("/approve/{id}")
	 public ResponseEntity<String> approveSale(@PathVariable int id) {
        boolean isApproved=this.iManagerService.approuvedSale(id);
        if (isApproved) {
            return ResponseEntity.ok("Sale approved successfully.");
        } else {
            return ResponseEntity.status(400).body("Failed to approve the sale.");
        }
    }

	@PutMapping("/cancel/{id}")
	public  ResponseEntity<String> cancelSale(@PathVariable int id) {
		 boolean isCancelled=this.iManagerService.canceledSale(id);
	        if (isCancelled) {
	            return ResponseEntity.ok("Sale cancelled successfully.");
	        } else {
	            return ResponseEntity.status(400).body("Failed to cancel the sale.");
	        }	
	   }
	
	
	@GetMapping("/allTransactions")
	  public ResponseEntity<List<SaleTransaction>> getAllTransctions() {
        List<SaleTransaction> transactions=this.iManagerService.getAllSalesTransaction();
        return ResponseEntity.ok(transactions);
    }
	 @GetMapping("/total")
	   public ResponseEntity<Double> getTotalRevenue() {
	        double totalRevenue=this.iManagerService.generateTotalRevenue();
	        return ResponseEntity.ok(totalRevenue);
	    }
	 
	 @GetMapping("/countSales")
	  public ResponseEntity<Long> getCountSales() {
	        long totalSales=this.iManagerService.getTotalSalesCount();
	        return ResponseEntity.ok(totalSales);
	    }

	 
	

	 @PutMapping("/updateStatusMaintenance/{vehicleId}/{mechanicId}")
	 public ResponseEntity<String> updateVehicleStatusToMaintenance(@PathVariable int vehicleId, @PathVariable int mechanicId) {
	        try {
	            String status=this.iManagerService.updateVehicleStatusToMaintenance(vehicleId, mechanicId);
	            return ResponseEntity.ok("Vehicle status updated to maintenance: "+status);
	        } catch (Exception e) {
	            return ResponseEntity.status(400).body("Failed to update vehicle status: " + e.getMessage());
	        }
	    }

	 @PostMapping("/ajouterVisite")
	 public ResponseEntity<String> ajouterVisiteTechnique() {
	        try {
	            this.iManagerService.verifierVisiteTechnique();
	            return ResponseEntity.ok("Technical visit added successfully.");
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Failed to add technical visit: " + e.getMessage());
	        }
	    }

	 @PostMapping("/acheterVehicule/{vehiculeId}/{fournisseurId}")
	 public ResponseEntity<Vehicule> acheterVehicule(@PathVariable int fournisseurId, @PathVariable int vehiculeId) {
	        try {
	            Vehicule purchasedVehicule=this.iManagerService.acheterVehicule(vehiculeId, fournisseurId);
	            return ResponseEntity.ok(purchasedVehicule);
	        } catch (Exception e) {
	            return ResponseEntity.status(400).body(null);
	        }
	    }
	 
	  @DeleteMapping("deleteVehicule/{id}")
	    public ResponseEntity<String> deleteVehicule(@PathVariable("id") int vehiculeId) {
	        try {
	            iVehiculeService.deleteVehicule(vehiculeId);
	            return new ResponseEntity<>("Vehicle deleted successfully", HttpStatus.NO_CONTENT);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Failed to delete vehicle: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	        }
	    }
	  
	 
}
