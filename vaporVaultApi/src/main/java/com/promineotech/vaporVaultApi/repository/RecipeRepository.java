package com.promineotech.vaporVaultApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promineotech.vaporVaultApi.entity.Recipe;



public interface RecipeRepository extends JpaRepository<Recipe, Long>{

}
