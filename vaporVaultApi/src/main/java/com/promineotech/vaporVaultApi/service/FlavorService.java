package com.promineotech.vaporVaultApi.service;

import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.promineotech.vaporVaultApi.entity.Flavor;
import com.promineotech.vaporVaultApi.repository.FlavorRepository;

@Service
public class FlavorService {

	private static final Logger logger = LogManager.getLogger(FlavorService.class);
	
	@Autowired
	private FlavorRepository repo;
	
	public Iterable<Flavor> getFlavors() {
		return repo.findAll();
	}
	
	public Flavor createFlavor(Flavor flavors) {
		return repo.save(flavors);
	}
	
	public Flavor updateFlavor(Flavor flavors, Long id) {
		logger.info("Updating flavor {}" + flavors);
		Flavor oldFlavor = repo.findById(id)
					.orElseThrow(() -> new NoSuchElementException("Can't find flavor with id:" + id));
			oldFlavor.setName(flavors.getName());
			return repo.save(oldFlavor);
		
	}
	
	public void removeFlavor(Long id) throws Exception {
		try {
			repo.deleteById(id);			
		} catch (Exception e) {
			logger.error("Exception occurred while trying to delete flavor: " + id, e);
			throw new Exception("Unable to delete flavor.");
		}
	}
		
}