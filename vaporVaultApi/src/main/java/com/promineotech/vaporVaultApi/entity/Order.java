package com.promineotech.vaporVaultApi.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.promineotech.vaporVaultApi.util.OrderStatus;


@Entity(name = "Orders")
public class Order {
	
	private Long id;
	private LocalDate ordered;
	private LocalDate estimatedDelivery;
	private LocalDate delivered;
	private double invoiceAmount;
	private OrderStatus status;
	private Set<Recipe> recipes;
	
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

	public LocalDate getOrdered() {
		return ordered;
	}

	public void setOrdered(LocalDate ordered) {
		this.ordered = ordered;
	}

	public LocalDate getEstimatedDelivery() {
		return estimatedDelivery;
	}

	public void setEstimatedDelivery(LocalDate estimatedDelivery) {
		this.estimatedDelivery = estimatedDelivery;
	}

	public LocalDate getDelivered() {
		return delivered;
	}

	public void setDelivered(LocalDate delivered) {
		this.delivered = delivered;
	}

	public double getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name = "customerId")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@ManyToMany(mappedBy = "orders")
	public Set<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(Set<Recipe> recipes) {
		this.recipes = recipes;
	}

}
