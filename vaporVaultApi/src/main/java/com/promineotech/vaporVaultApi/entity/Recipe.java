package com.promineotech.vaporVaultApi.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name = "recipes")
public class Recipe {

	private Long id;
	private String name;
	private double price;
	
	@JsonIgnore
	private Set<Flavor> flavors;
	
	@JsonIgnore
	private Set<Percent> percents;
	
	@JsonIgnore
	private Set<Order> orders;
	
	@JsonIgnore
	private Customer customer;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "recipes_order",
			joinColumns = @JoinColumn(name = "recipeId", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "orderId", referencedColumnName = "id"))
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "recipes_flavors",
			joinColumns = @JoinColumn(name = "recipeId", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "flavorId", referencedColumnName = "id"))
	public Set<Flavor> getFlavors() {
		return flavors;
	}

	public void setFlavors(Set<Flavor> flavors) {
		this.flavors = flavors;
	}
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "recipes_percents",
			joinColumns = @JoinColumn(name = "recipeId", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "percentId", referencedColumnName = "id"))
	public Set<Percent> getPercents() {
		return percents;
	}

	public void setPercents(Set<Percent> percents) {
		this.percents = percents;
	}
	
}