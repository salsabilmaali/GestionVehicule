package com.projet.demo.Entities;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="customers")
public class Customer extends User{
	private String address;
	 	@JsonIgnore
	 @OneToMany(mappedBy = "customer") 
	 private List<SaleTransaction> saleTransactions;

	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
