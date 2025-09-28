package com.projet.demo.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.demo.Entities.Fournisseur;
import com.projet.demo.Entities.Vehicule;
import com.projet.demo.Entities.VehiculeStatus;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur,Integer>{
    Optional<Fournisseur> findByNom(String fournisseurName);


}
