package com.promineotech.vaporVaultApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.vaporVaultApi.entity.Flavor;
import com.promineotech.vaporVaultApi.service.FlavorService;


@RestController
@RequestMapping("/flavors")
public class FlavorController {
	
	@Autowired
	private FlavorService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getFlavors() {
		return new ResponseEntity<Object>(service.getFlavors(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Flavor createFlavors(@RequestBody Flavor flavors) {
		return service.createFlavor(flavors);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateFlavor(@RequestBody Flavor flavors, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateFlavor(flavors, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to update Flavor.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteFlavor(@PathVariable Long id) {
		try {
			service.removeFlavor(id);
			return new ResponseEntity<Object>("Successfully deleted flavor with id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to delete recipe.", HttpStatus.BAD_REQUEST); 
		}
	}

}