package com.projet.demo.Services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.projet.demo.Entities.Vehicule;
import com.projet.demo.Entities.VehiculeStatus;
import com.projet.demo.Entities.VisiteTechnique;
import com.projet.demo.Repositories.VehiculeRepository;
import com.projet.demo.Repositories.VisiteTechniqueRepository;
@Service
public class VisiteTechniqueService implements IVisiteTechniqueService{

	private VehiculeRepository vehiculeRepository;
	private VisiteTechniqueRepository visiteTechniqueRepository;
	public VisiteTechniqueService(VehiculeRepository vehiculeRepository,VisiteTechniqueRepository visiteTechniqueRepository) {
		this.vehiculeRepository=vehiculeRepository;
		this.visiteTechniqueRepository=visiteTechniqueRepository;
	}
	
	@Override
	public void ajouterVisiteTechnique(Vehicule vehicule, boolean estvalide) 
	{
		try {
			VisiteTechnique visite=new VisiteTechnique();
			visite.setVehicule(vehicule);
			visite.setDateVisite(LocalDate.now());
			visite.setDateExpiration(LocalDate.now().plusMonths(6));
			visite.setPrix(120);
			if(!estvalide)
				{visite.setResultat("non validé");
				vehicule.setStatus(VehiculeStatus.Under_Maintenance);//kenha mch validé status mtaa vehicule lezm yetbadel
				}
			else
				visite.setResultat("validé");
			this.visiteTechniqueRepository.save(visite);
		}
		catch(Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}	
		
	}
}
