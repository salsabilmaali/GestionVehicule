package com.projet.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.demo.Entities.VisiteTechnique;
@Repository
public interface VisiteTechniqueRepository extends JpaRepository<VisiteTechnique,Integer>{

}
