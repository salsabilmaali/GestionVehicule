package com.projet.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.demo.Entities.Vehicule;
import com.projet.demo.Entities.VehiculeStatus;
@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Integer> {

	List<Vehicule> findByStatus(VehiculeStatus inStock);

}
