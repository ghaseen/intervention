package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
@SpringBootApplication
@Entity
public class Intervention  implements Serializable{
	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY) 

	private  Long idInt ;
	@NotNull(message="Ne doit pas etre null")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateInt ;
	@NotBlank(message="Ne doit pas etre vide")
	private String localisation ; 
	@NotBlank(message="Ne doit pas etre vide")
	private String detaille;
	
	@ManyToOne
	@JoinColumn(name="tech_id")
	private technicien technicien  ;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "reclamation_id", referencedColumnName = "idR")
	private Reclamation reclamation;
	
	
	
	public Reclamation getReclamation() {
		return reclamation;
	}
	public void setReclamation(Reclamation reclamation) {
		this.reclamation = reclamation;
	}
	public Intervention(Long idInt, Date dateInt, String localisation, technicien technicien,String detaille) {
		super();
		this.idInt = idInt;
		this.dateInt = dateInt;
		this.localisation = localisation;
		this.technicien = technicien;
		this.detaille=detaille;
	}
	public Intervention() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public String getDetaille() {
		return detaille;
	}
	public void setDetaille(String detaille) {
		this.detaille = detaille;
	}
	public Long getIdInt() {
		return idInt;
	}
	public void setIdInt(Long idInt) {
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
	@Override
	public String toString() {
		return "Intervention [idInt=" + this.idInt + ", dateInt=" + this.dateInt + ", localisation=" + this.localisation + "]";
	}
	
	
	
	

}
