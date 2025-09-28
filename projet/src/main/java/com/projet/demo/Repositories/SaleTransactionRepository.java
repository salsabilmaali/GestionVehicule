package com.projet.demo.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.demo.Entities.SaleTransaction;

@Repository
public interface SaleTransactionRepository extends JpaRepository<SaleTransaction, Integer> {


}
