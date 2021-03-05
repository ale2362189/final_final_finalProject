package com.promineotech.vaporVaultApi.service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.vaporVaultApi.entity.Customer;
import com.promineotech.vaporVaultApi.entity.Flavor;
import com.promineotech.vaporVaultApi.entity.Percent;
import com.promineotech.vaporVaultApi.entity.Recipe;
import com.promineotech.vaporVaultApi.repository.CustomerRepository;
import com.promineotech.vaporVaultApi.repository.FlavorRepository;
import com.promineotech.vaporVaultApi.repository.PercentRepository;
import com.promineotech.vaporVaultApi.repository.RecipeRepository;

;

@Service
public class RecipeService {

	private static final Logger logger = LogManager.getLogger(RecipeService.class);
	
	@Autowired
	private RecipeRepository repo;
	
	@Autowired
	private FlavorRepository flavorRepo;
	
	@Autowired
	private PercentRepository percentRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	public Iterable<Recipe> getRecipes() {
		return repo.findAll();
	}
	
	public Recipe createNewRecipe(Set<Long> flavorIds, Set<Long> percentIds, Long customerId) throws Exception {
		try {
			Customer customer = customerRepo.findById(customerId)
					.orElseThrow(() -> new NoSuchElementException("Can't find customer with ID: " + customerId));
			Recipe recipe = initializeNewRecipe(flavorIds, percentIds, customer);
			return repo.save(recipe);
		} catch (Exception e) {
			logger.error("Exception occured while trying to create new order for customer: " + customerId, e);
			throw e;		
		}
	}
	
	
	private Recipe initializeNewRecipe(Set<Long> flavorIds, Set<Long> percentIds, Customer customer) {
		Recipe recipe = new Recipe();
		recipe.setFlavors(convertToFlavorSet(flavorRepo.findAllById(flavorIds)));
		recipe.setPercents(convertToPercentSet(percentRepo.findAllById(percentIds)));
		recipe.setCustomer(customer);
		addFlavorsToRecipe(recipe);
		addPercentsToRecipe(recipe);
		return recipe;
	}

	private void addPercentsToRecipe(Recipe recipe) {
		Set<Percent> percents = recipe.getPercents();
		for (Percent percent : percents) {
			percent.getRecipes().add(recipe);
		}		
		
	}

	private void addFlavorsToRecipe(Recipe recipe) {
		Set<Flavor> flavors = recipe.getFlavors();
		for (Flavor flavor : flavors) {
			flavor.getRecipes().add(recipe);
		}		
	}

	private Set<Percent> convertToPercentSet(Iterable<Percent> iterable) {
		Set<Percent> set = new HashSet<Percent>();
		for (Percent percent : iterable) {
			set.add(percent);
		}
		return set;
	}

	private Set<Flavor> convertToFlavorSet(Iterable<Flavor> iterable) {
		Set<Flavor> set = new HashSet<Flavor>();
		for (Flavor flavor : iterable) {
			set.add(flavor);
		}
		return set;
	}

	public Recipe updateRecipe(Recipe recipes, Long id) {
		logger.info("Updating recipe {}" + recipes);
		Recipe oldRecipe = repo.findById(id)
					.orElseThrow(() -> new NoSuchElementException("Can't find recipe with id:" + id));
			oldRecipe.setFlavors(recipes.getFlavors());
			oldRecipe.setName(recipes.getName());
			oldRecipe.setPrice(recipes.getPrice());
			return repo.save(oldRecipe);
		
	}
	
	public void removeRecipe(Long id) throws Exception {
		try {
			repo.deleteById(id);			
		} catch (Exception e) {
			logger.error("Exception occurred while trying to delete product: " + id, e);
			throw new Exception("Unable to delete product.");
		}
	}
	
	
	/*
	 * Need help with how to write this method. It needs to take a Set of flavors
	 * and return a set of recipes that include all of the flavors in Set of flavors 
	 */
	
//	public Set<Long> findRecipeByFlavors(List<Long> flavorIds) {
//	  try {
//		  
//		  Set<Flavor> ingredients = flavorRepo.findAllById(flavorIds);
//		  // create hashset to hold recipe ids loop through all flavors, for each 
		  //	 flavor loop through recipes associated to get set of Long (ids)
//		  return
//		} catch (Exception e) {
//	logger.error("Exception occurred while trying to find recipes")
//	}
		
}
