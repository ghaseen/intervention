package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@Entity
public class Intervention  implements Serializable{
	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY) 

	private  int idInt ;
	private Date dateInt ;
	private String localisation ; 
	@ManyToOne
	@JoinColumn(name="tech_id")
	private technicien technicien  ;
	public Intervention(int idInt, Date dateInt, String localisation, technicien technicien) {
		super();
		this.idInt = idInt;
		this.dateInt = dateInt;
		this.localisation = localisation;
		this.technicien = technicien;
	}
	public Intervention() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdInt() {
		return idInt;
	}
	public void setIdInt(int idInt) {
		this.idInt = idInt;
	}
	public Date getDateInt() {
		return dateInt;
	}
	public void setDateInt(Date dateInt) {
		this.dateInt = dateInt;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public technicien getTechnicien() {
		return technicien;
	}
	public void setTechnicien(technicien technicien) {
		this.technicien = technicien;
	}
	
	
	
	

}
