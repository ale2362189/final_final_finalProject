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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "percents")
public class Percent {
	

	
	private Long id;
	private int percents;
	
	
	@JsonIgnore
	private Set<Recipe> recipes;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "recipes_percents",
			joinColumns = @JoinColumn(name = "percentId", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "recipeId", referencedColumnName = "id"))
	public Set<Recipe> getRecipes() {
		return recipes;
	}
	
	
	public void setRecipes(Set<Recipe> recipes) {
		this.recipes = recipes;
	}

	public int getPercents() {
		return percents;
	}
	public void setPercents(int percents) {
		this.percents = percents;
	}
	
	
}
