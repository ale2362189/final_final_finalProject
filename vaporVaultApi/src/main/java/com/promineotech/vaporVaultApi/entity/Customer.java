package com.promineotech.vaporVaultApi.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.promineotech.vaporVaultApi.util.MilitaryDiscount;


@Entity
public class Customer {

	private Long id;
	private String firstName;
	private String lastName;
	private Address address;
	private MilitaryDiscount discount;
	private Set<Order> orders;
	private Set<Recipe> recipes;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public MilitaryDiscount getDiscount() {
		return discount;
	}
	public void setDiscount(MilitaryDiscount level) {
		this.discount = level;
	}
	
	@OneToMany(mappedBy = "customer"
			+ "")
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	@OneToMany(mappedBy = "customer"
			+ "")
	public Set<Recipe> getRecipe() {
		return recipes;
	}
	public void setRecipe(Set<Recipe> recipes) {
		this.recipes = recipes;
	}
	
	

}
