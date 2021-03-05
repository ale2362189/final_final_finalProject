package com.promineotech.vaporVaultApi.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.vaporVaultApi.entity.Customer;
import com.promineotech.vaporVaultApi.entity.Order;
import com.promineotech.vaporVaultApi.entity.Recipe;
import com.promineotech.vaporVaultApi.repository.CustomerRepository;
import com.promineotech.vaporVaultApi.repository.OrderRepository;
import com.promineotech.vaporVaultApi.repository.RecipeRepository;
import com.promineotech.vaporVaultApi.util.MilitaryDiscount;
import com.promineotech.vaporVaultApi.util.OrderStatus;


@Service
public class OrderService {
	
	private static final Logger logger = LogManager.getLogger(OrderService.class);
	private final int DELIVERY_DAYS = 7;
	
	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private RecipeRepository recipeRepo;
	
	public Order submitNewOrder(Set<Long> recipeIds, Long customerId) throws Exception {
		try {
			Customer customer = customerRepo.findById(customerId)
					.orElseThrow(() -> new NoSuchElementException("Can't find customer with ID: " + customerId));
	    	Order order = initializeNewOrder(recipeIds, customer);
			return repo.save(order);
		} catch (Exception e) {
			logger.error("Exception occured while trying to create new order for customer: " + customerId, e);
			throw e;		
		}
	}
	
	public Order cancelOrder(Long orderId) {
			Order order = repo.findById(orderId)
					.orElseThrow(() -> new NoSuchElementException("Can't find order with ID: " + orderId));
			order.setStatus(OrderStatus.CANCELED);
			return repo.save(order);
		
	}
	
	public Order completeOrder(Long orderId) {
			Order order = repo.findById(orderId)
					.orElseThrow(() -> new NoSuchElementException("Can't find order with id:" + orderId));
			order.setStatus(OrderStatus.DELIVERED);
			
			return repo.save(order);
	}

	private Order initializeNewOrder(Set<Long> recipeIds, Customer customer) {
		Order order = new Order();
		order.setRecipes(convertToRecipeSet(recipeRepo.findAllById(recipeIds)));
		order.setOrdered(LocalDate.now());
		order.setEstimatedDelivery(LocalDate.now().plusDays(DELIVERY_DAYS));
		order.setCustomer(customer);
		order.setInvoiceAmount(calculateOrderTotal(order.getRecipes(), customer.getDiscount()));
		order.setStatus(OrderStatus.ORDERED);
		addOrderToRecipes(order);
		return order;
	}
	
	private void addOrderToRecipes(Order order) {
		Set<Recipe> recipes = order.getRecipes();
		for (Recipe recipe : recipes) {
			recipe.getOrders().add(order);
		}
	}
//	
	private Set<Recipe> convertToRecipeSet(Iterable<Recipe> iterable) {
		Set<Recipe> set = new HashSet<Recipe>();
		for (Recipe recipe : iterable) {
			set.add(recipe);
		}
		return set;
	}
	
	private double calculateOrderTotal(Set<Recipe> recipes, MilitaryDiscount level) {
		double total = 0;
		for (Recipe recipe : recipes) {
			total += recipe.getPrice();
		}
		return total * level.getDiscount();
	}
}
