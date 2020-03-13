package org.sid.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;        
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Entity
@Table(name="Client")
public class Client implements Serializable {

	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long  id ;
	private String sexe  ;
	private String nom ; 
	private String prenom ;
	private Long cin ;
	@Column(name="date_naissance")
	private Date dateN ;
	private String adresse ; 
	private int mobile ; 
	private String mail ;
	
	
	@OneToMany(mappedBy="client" , fetch=FetchType.LAZY)
	private List<Reclamation> rec; 
	@OneToOne
	private users user ;
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Client(String sexe, String nom, String prenom, Long cin, Date dateN, String adresse, int mobile, String mail,
			 users user) {
		super();
		this.sexe = sexe;
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.dateN = dateN;
		this.adresse = adresse;
		this.mobile = mobile;
		this.mail = mail;
		this.user = user;
	}




	public List<Reclamation> getRec() {
		return rec;
	}





	public void setRec(List<Reclamation> rec) {
		this.rec = rec;
	}





	public Date getDateN() {
		return dateN;
	}


	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Long getCin() {
		return cin;
	}
	public void setCin(Long cin) {
		this.cin = cin;
	}
	
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}









	public users getUser() {
		return user;
	}









	public void setUser(users user) {
		this.user = user;
	} 
	  
	
	
	
	

}
