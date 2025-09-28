package com.projet.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.demo.Entities.Manager;
@Repository
public interface ManagerRepository extends JpaRepository<Manager,Integer>{
	

}
