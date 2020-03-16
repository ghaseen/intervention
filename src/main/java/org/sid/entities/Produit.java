package org.sid.entities;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;
@Entity
public class Produit implements Serializable {
	
	@Id @GeneratedValue (strategy=GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Size (min=5, max=70)
	private String designation ;
	@DecimalMin("100")
	private double prix ;
	private int qunatite ;
	
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Produit() {
		
	}
	public Produit( String designation, double prix, int qunatite) {
		
		this.designation = designation;
		this.prix = prix;
		this.qunatite = qunatite;
	}

	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getQunatite() {
		return qunatite;
	}
	public void setQunatite(int qunatite) {
		this.qunatite = qunatite;
	}

}
