package com.promineotech.vaporVaultApi.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.vaporVaultApi.entity.Recipe;
import com.promineotech.vaporVaultApi.service.RecipeService;



@RestController
@RequestMapping("/recipes")
public class RecipeController {
	
	@Autowired
	private RecipeService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getRecipes() {
		return new ResponseEntity<Object>(service.getRecipes(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Object> createNewRecipe(@RequestBody Set<Long> flavorIds, @RequestBody Set<Long> percentIds, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.createNewRecipe(flavorIds, percentIds, id), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateRecipe(@RequestBody Recipe recipes, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateRecipe(recipes, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to update recipe.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteRecipe(@PathVariable Long id) {
		try {
			service.removeRecipe(id);
			return new ResponseEntity<Object>("Successfully deleted recipe with id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to delete recipe.", HttpStatus.BAD_REQUEST); 
		}
	}

}