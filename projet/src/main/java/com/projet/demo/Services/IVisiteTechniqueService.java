package com.projet.demo.Services;

import org.springframework.stereotype.Service;

import com.projet.demo.Entities.Vehicule;
import com.projet.demo.Entities.VisiteTechnique;
@Service
public interface IVisiteTechniqueService {

	void ajouterVisiteTechnique(Vehicule vehicule,boolean estvalide);

}
