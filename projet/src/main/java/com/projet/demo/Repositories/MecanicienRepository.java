package com.projet.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.demo.Entities.Mecanicien;


@Repository
public interface MecanicienRepository extends JpaRepository<Mecanicien,Integer>{

}
