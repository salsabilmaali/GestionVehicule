package com.projet.demo.Services;

import java.util.List;

import com.projet.demo.Entities.Vehicule;
import com.projet.demo.Repositories.CustomerRepository;

public interface ICustomerService{
    boolean buyVehicule(int vehiculeId, int customerId);
    public List<Vehicule> searchVehicles(String model , Double minPrice ,Double maxPrice) ;
}
